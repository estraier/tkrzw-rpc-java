/*************************************************************************************************
 * Remote database manager
 *
 * Copyright 2020 Google LLC
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a copy of the License at
 *     https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.  See the License for the specific language governing permissions
 * and limitations under the License.
 *************************************************************************************************/

package tkrzw_rpc;

import com.google.protobuf.ByteString;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.channel.ChannelOption;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * Remote database manager.
 * @note All operations are thread-safe; Multiple threads can access the same database
 * concurrently.  The setDBMIndex affects all threads so it should be called before the object is
 * shared.
 */
public class RemoteDBM {

  /**
   * Constructor.
   */
  public RemoteDBM() {}

  /**
   * Destructs the object and releases resources.
   * @note The database is closed implicitly if it has not been closed.
   */
  public void destruct() {
    if (stub_ != null) {
      disconnect();
    }
    channel_ = null;
    stub_ = null;
    asyncStub_ = null;
  }

  /**
   * Connects to the server.
   * @param address The address or the host name of the server and its port number.  For IPv4
   * address, it's like "127.0.0.1:1978".  For IPv6, it's like "[::1]:1978".  For UNIX domain
   * sockets, it's like "unix:/path/to/file".
   * @param timeout The timeout in seconds for connection and each operation.  Negative means
   * unlimited.
   * @return The result status.
   */
  public Status connect(String address, double timeout) {
    if (stub_ != null) {
      return new Status(Status.PRECONDITION_ERROR, "opened connection");
    }
    if (timeout < 0) {
      timeout = 1 << 24;
    }
    channel_ = NettyChannelBuilder.forTarget(address)
        .withOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int)(timeout * 1000))
        .usePlaintext().build();
    try {
      stub_ = DBMServiceGrpc.newBlockingStub(channel_);
      asyncStub_ = DBMServiceGrpc.newStub(channel_);
      dbmIndex_ = 0;
      timeout_ = timeout;
      return new Status();
    } catch (Exception e) {
      try {
        channel_.shutdownNow().awaitTermination(1, TimeUnit.SECONDS);
      } catch (Exception e2) {
      }
      channel_ = null;
      stub_ = null;
      asyncStub_ = null;
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
  }

  /**
   * Disconnects the connection to the server.
   * @return The result status.
   */
  public Status disconnect() {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    stub_ = null;
    asyncStub_ = null;
    try {
      channel_.shutdownNow().awaitTermination(1, TimeUnit.SECONDS);
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status();
  }

  /**
   * Sets the index of the DBM to access.
   * @param dbmIndex The index of the DBM to access.
   * @return The result status.
   */
  public Status setDBMIndex(int dbmIndex) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    dbmIndex_ = dbmIndex;
    return new Status();
  }

  /**
   * Sends a message and gets back the echo message.
   * @param message The message to send.
   * @param status A status object to which the result status is assigned.  If it is null, it is
   * not used.
   * @return The echo message or null on failure.
   */
  public String echo(String message, Status status) {
    if (stub_ == null) {
      if (status != null) {
        status.set(Status.PRECONDITION_ERROR, "not opened connection");
      }
      return null;
    }
    TkrzwRpc.EchoRequest.Builder request = TkrzwRpc.EchoRequest.newBuilder();
    request.setMessage(message);
    TkrzwRpc.EchoResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .echo(request.build());
    } catch (Exception e) {
      if (status != null) {
        status.set(Status.NETWORK_ERROR, e.toString());
      }
      return null;
    }
    if (status != null) {
      status.set(response.getStatus());
    }
    return response.getEcho();
  }

  /**
   * Sends a message and gets back the echo message, without status assignment.
   * @param message The message to send.
   * @return The echo message or null on failure.
   */
  public String echo(String message) {
    return echo(message, null);
  }

  /**
   * Inspects the database.
   * @return A map of property names and their values.
   */
  public Map<String, String> inspect() {
    if (stub_ == null) {
      return null;
    }
    TkrzwRpc.InspectRequest.Builder request = TkrzwRpc.InspectRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    TkrzwRpc.InspectResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .inspect(request.build());
    } catch (Exception e) {
      return null;
    }
    Map records = new HashMap();
    for (TkrzwRpc.StringPair record : response.getRecordsList()) {
      records.put(record.getFirst(), record.getSecond());
    }
    return records;
  }

  /**
   * Gets the value of a record of a key.
   * @param key The key of the record.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return The value data of the record or null on failure.
   */
  public byte[] get(byte[] key, Status status) {
    if (stub_ == null) {
      if (status != null) {
        status.set(Status.PRECONDITION_ERROR, "not opened connection");
      }
      return null;
    }
    TkrzwRpc.GetRequest.Builder request = TkrzwRpc.GetRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setKey(ByteString.copyFrom(key));
    TkrzwRpc.GetResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .get(request.build());
    } catch (Exception e) {
      if (status != null) {
        status.set(Status.NETWORK_ERROR, e.toString());
      }
      return null;
    }
    if (status != null) {
      status.set(response.getStatus());
    }
    if (response.getStatus().getCode() == 0) {
      return response.getValue().toByteArray();
    }
    return null;
  }

  /**
   * Gets the value of a record of a key, without status assignment.
   * @param key The key of the record.
   * @return The value data of the record or null on failure.
   */
  public byte[] get(byte[] key) {
    return get(key, null);
  }

  /**
   * Gets the value of a record of a key, with string data.
   * @param key The key of the record.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return The value data of the record or null on failure.
   */
  public String get(String key, Status status) {
    byte[] value = get(key.getBytes(StandardCharsets.UTF_8), status);
    if (value == null) {
      return null;
    }
    return new String(value, StandardCharsets.UTF_8);
  }

  /**
   * Gets the value of a record of a key, with string data, without status assignment.
   * @param key The key of the record.
   * @return The value data of the record or null on failure.
   */
  public String get(String key) {
    return get(key, null);
  }

  /**
   * Gets the values of multiple records of keys.
   * @param keys The keys of records to retrieve.
   * @return A map of retrieved records.  Keys which don't match existing records are ignored.
   */
  public Map<byte[], byte[]> getMulti(byte[][] keys) {
    if (stub_ == null) {
      return null;
    }
    TkrzwRpc.GetMultiRequest.Builder request = TkrzwRpc.GetMultiRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    for (byte[] key : keys) {
      request.addKeys(ByteString.copyFrom(key));
    }
    TkrzwRpc.GetMultiResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .getMulti(request.build());
    } catch (Exception e) {
      return null;
    }
    HashMap<byte[], byte[]> records = new HashMap<byte[], byte[]>(response.getRecordsCount());
    for (tkrzw_rpc.TkrzwRpc.BytesPair record : response.getRecordsList()) {
      records.put(record.getFirst().toByteArray(), record.getSecond().toByteArray());
    }
    return records;
  }

  /**
   * Gets the values of multiple records of keys, with string data.
   * @param keys The keys of records to retrieve.
   * @return A map of retrieved records.  Keys which don't match existing records are ignored.
   */
  public Map<String, String> getMulti(String[] keys) {
    if (stub_ == null) {
      return null;
    }
    TkrzwRpc.GetMultiRequest.Builder request = TkrzwRpc.GetMultiRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    for (String key : keys) {
      request.addKeys(ByteString.copyFromUtf8(key));
    }
    TkrzwRpc.GetMultiResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .getMulti(request.build());
    } catch (Exception e) {
      return null;
    }
    HashMap<String, String> records = new HashMap<String, String>(response.getRecordsCount());
    for (tkrzw_rpc.TkrzwRpc.BytesPair record : response.getRecordsList()) {
      records.put(record.getFirst().toStringUtf8(), record.getSecond().toStringUtf8());
    }
    return records;
  }

  /**
   * Sets a record of a key and a value.
   * @param key The key of the record.
   * @param value The value of the record.
   * @param overwrite Whether to overwrite the existing value if there's a record with the same
   * key.  If true, the existing value is overwritten by the new value.  If false, the operation
   * is given up and an error status is returned.
   * @return The result status.  If overwriting is abandoned, DUPLICATION_ERROR is returned.
   */
  public Status set(byte[] key, byte[] value, boolean overwrite) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.SetRequest.Builder request = TkrzwRpc.SetRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setKey(ByteString.copyFrom(key));
    request.setValue(ByteString.copyFrom(value));
    request.setOverwrite(overwrite);
    TkrzwRpc.SetResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .set(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Sets a record of a key and a value, with overwriting.
   * @param key The key of the record.
   * @param value The value of the record.
   * @return The result status.
   */
  public Status set(byte[] key, byte[] value) {
    return set(key, value, true);
  }

  /**
   * Sets a record of a key and a value, with string data.
   * @param key The key of the record.
   * @param value The value of the record.
   * @param overwrite Whether to overwrite the existing value if there's a record with the same
   * key.  If true, the existing value is overwritten by the new value.  If false, the operation
   * is given up and an error status is returned.
   * @return The result status.  If overwriting is abandoned, DUPLICATION_ERROR is returned.
   */
  public Status set(String key, String value, boolean overwrite) {
    return set(key.getBytes(StandardCharsets.UTF_8),
               value.getBytes(StandardCharsets.UTF_8), overwrite);
  }

  /**
   * Sets a record of a key and a value, with string data, with overwriting.
   * @param key The key of the record.
   * @param value The value of the record.
   * @return The result status.
   */
  public Status set(String key, String value) {
    return set(key, value, true);
  }

  /**
   * Sets multiple records.
   * @param records The records to store.
   * @param overwrite Whether to overwrite the existing value if there's a record with the same
   * key.  If true, the existing value is overwritten by the new value.  If false, the operation
   * is given up and an error status is returned.
   * @return The result status.  If there are records avoiding overwriting, DUPLICATION_ERROR
   * is returned.
   */
  public Status setMulti(Map<byte[], byte[]> records, boolean overwrite) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.SetMultiRequest.Builder request = TkrzwRpc.SetMultiRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    for (Map.Entry<byte[], byte[]> record : records.entrySet()) {
      TkrzwRpc.BytesPair.Builder req_record = request.addRecordsBuilder();
      req_record.setFirst(ByteString.copyFrom(record.getKey()));
      req_record.setSecond(ByteString.copyFrom(record.getValue()));
    }
    request.setOverwrite(overwrite);
    TkrzwRpc.SetMultiResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .setMulti(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Sets multiple records, with string data.
   * @param records The records to store.
   * @param overwrite Whether to overwrite the existing value if there's a record with the same
   * key.  If true, the existing value is overwritten by the new value.  If false, the operation
   * is given up and an error status is returned.
   * @return The result status.  If there are records avoiding overwriting, DUPLICATION_ERROR
   * is returned.
   */
  public Status setMultiString(Map<String, String> records, boolean overwrite) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.SetMultiRequest.Builder request = TkrzwRpc.SetMultiRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    for (Map.Entry<String, String> record : records.entrySet()) {
      TkrzwRpc.BytesPair.Builder req_record = request.addRecordsBuilder();
      req_record.setFirst(ByteString.copyFromUtf8(record.getKey()));
      req_record.setSecond(ByteString.copyFromUtf8(record.getValue()));
    }
    request.setOverwrite(overwrite);
    TkrzwRpc.SetMultiResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .setMulti(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Removes a record of a key.
   * @param key The key of the record.
   * @return The result status.  If there's no matching record, NOT_FOUND_ERROR is returned.
   */
  public Status remove(byte[] key) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.RemoveRequest.Builder request = TkrzwRpc.RemoveRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setKey(ByteString.copyFrom(key));
    TkrzwRpc.RemoveResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .remove(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Removes a record of a key, with string data.
   * @param key The key of the record.
   * @return The result status.  If there's no matching record, NOT_FOUND_ERROR is returned.
   */
  public Status remove(String key) {
    return remove(key.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Removes records of keys.
   * @param keys The keys of records to remove.
   * @return The result status.  If there are missing records, NOT_FOUND_ERROR is returned.
   */
  public Status removeMulti(byte[][] keys) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.RemoveMultiRequest.Builder request = TkrzwRpc.RemoveMultiRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    for (byte[] key : keys) {
      request.addKeys(ByteString.copyFrom(key));
    }
    TkrzwRpc.RemoveMultiResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .removeMulti(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Removes records of keys, with string data.
   * @param keys The keys of records to remove.
   * @return The result status.  If there are missing records, NOT_FOUND_ERROR is returned.
   */
  public Status removeMulti(String[] keys) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.RemoveMultiRequest.Builder request = TkrzwRpc.RemoveMultiRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    for (String key : keys) {
      request.addKeys(ByteString.copyFromUtf8(key));
    }
    TkrzwRpc.RemoveMultiResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .removeMulti(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Appends data at the end of a record of a key.
   * @param key The key of the record.
   * @param value The value to append.
   * @param delim The delimiter to put after the existing record.
   * @return The result status.
   * @note If there's no existing record, the value is set without the delimiter.
   */
  public Status append(byte[] key, byte[] value, byte[] delim) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.AppendRequest.Builder request = TkrzwRpc.AppendRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setKey(ByteString.copyFrom(key));
    request.setValue(ByteString.copyFrom(value));
    request.setDelim(ByteString.copyFrom(delim));
    TkrzwRpc.AppendResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .append(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Appends data at the end of a record of a key, with string data.
   * @param key The key of the record.
   * @param value The value to append.
   * @param delim The delimiter to put after the existing record.
   * @return The result status.
   * @note If there's no existing record, the value is set without the delimiter.
   */
  public Status append(String key, String value, String delim) {
    return append(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8),
                  delim.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Appends data to multiple records
   * @param records The records to append.
   * @param delim The delimiter to put after the existing record.
   * @return The result status.
   * @note If there's no existing record, the value is set without the delimiter.
   */
  public Status appendMulti(Map<byte[], byte[]> records, byte[] delim) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.AppendMultiRequest.Builder request = TkrzwRpc.AppendMultiRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    for (Map.Entry<byte[], byte[]> record : records.entrySet()) {
      TkrzwRpc.BytesPair.Builder req_record = request.addRecordsBuilder();
      req_record.setFirst(ByteString.copyFrom(record.getKey()));
      req_record.setSecond(ByteString.copyFrom(record.getValue()));
    }
    request.setDelim(ByteString.copyFrom(delim));
    TkrzwRpc.AppendMultiResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .appendMulti(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Appends data to multiple records, with string data.
   * @param records The records to append.
   * @param delim The delimiter to put after the existing record.
   * @return The result status.
   * @note If there's no existing record, the value is set without the delimiter.
   */
  public Status appendMulti(Map<String, String> records, String delim) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.AppendMultiRequest.Builder request = TkrzwRpc.AppendMultiRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    for (Map.Entry<String, String> record : records.entrySet()) {
      TkrzwRpc.BytesPair.Builder req_record = request.addRecordsBuilder();
      req_record.setFirst(ByteString.copyFromUtf8(record.getKey()));
      req_record.setSecond(ByteString.copyFromUtf8(record.getValue()));
    }
    request.setDelim(ByteString.copyFromUtf8(delim));
    TkrzwRpc.AppendMultiResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .appendMulti(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Compares the value of a record and exchanges if the condition meets.
   * @param key The key of the record.
   * @param expected The expected value.  If it is null, no existing record is expected.
   * @param desired The desired value.  If it is null, the record is to be removed.
   * @return The result status.  If the condition doesn't meet, INFEASIBLE_ERROR is returned.
   */
  public Status compareExchange(byte[] key, byte[] expected, byte[] desired) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.CompareExchangeRequest.Builder request =
        TkrzwRpc.CompareExchangeRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setKey(ByteString.copyFrom(key));
    if (expected != null) {
      request.setExpectedExistence(true);
      request.setExpectedValue(ByteString.copyFrom(expected));
    }
    if (desired != null) {
      request.setDesiredExistence(true);
      request.setDesiredValue(ByteString.copyFrom(desired));
    }
    TkrzwRpc.CompareExchangeResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .compareExchange(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Compares the value of a record and exchanges if the condition meets, with string data.
   * @param key The key of the record.
   * @param expected The expected value.  If it is null, no existing record is expected.
   * @param desired The desired value.  If it is null, the record is to be removed.
   * @return The result status.  If the condition doesn't meet, INFEASIBLE_ERROR is returned.
   */
  public Status compareExchange(String key, String expected, String desired) {
    return compareExchange(key.getBytes(StandardCharsets.UTF_8),
                           expected == null ? null : expected.getBytes(StandardCharsets.UTF_8),
                           desired == null ? null : desired.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Increments the numeric value of a record.
   * @param key The key of the record.
   * @param inc The incremental value.  If it is Long.MIN_VALUE, the current value is not changed
   * and a new record is not created.
   * @param init The initial value.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return The current value, or Long.MIN_VALUE on vailure
   * @note The record value is stored as an 8-byte big-endian integer.  Negative is also supported.
   */
  public long increment(byte[] key, long inc, long init, Status status) {
    if (stub_ == null) {
      if (status != null) {
        status.set(Status.PRECONDITION_ERROR, "not opened connection");
      }
      return Long.MIN_VALUE;
    }
    TkrzwRpc.IncrementRequest.Builder request = TkrzwRpc.IncrementRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setKey(ByteString.copyFrom(key));
    request.setIncrement(inc);
    request.setInitial(init);
    TkrzwRpc.IncrementResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .increment(request.build());
    } catch (Exception e) {
      if (status != null) {
        status.set(Status.NETWORK_ERROR, e.toString());
      }
      return Long.MIN_VALUE;
    }
    if (status != null) {
      status.set(response.getStatus());
    }
    if (response.getStatus().getCode() == 0) {
      return response.getCurrent();
    }
    return Long.MIN_VALUE;
  }

  /**
   * Increments the numeric value of a record, with string data.
   * @param key The key of the record.
   * @param inc The incremental value.
   * @param init The initial value.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return The current value, or Long.MIN_VALUE on vailure
   * @note The record value is stored as an 8-byte big-endian integer.  Negative is also supported.
   */
  public long increment(String key, long inc, long init, Status status) {
    return increment(key.getBytes(StandardCharsets.UTF_8), inc, init, status);
  }

  /**
   * Compares the values of records and exchanges if the condition meets.
   * @param expected The record keys and their expected values.  If the value is null, no existing
   * record is expected.
   * @param desired The record keys and their desired values.  If the value is null, the record
   * is to be removed.
   * @return The result status.  If the condition doesn't meet, INFEASIBLE_ERROR is returned.
   */
  public Status compareExchangeMulti(Map<byte[], byte[]> expected, Map<byte[], byte[]> desired) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.CompareExchangeMultiRequest.Builder request =
        TkrzwRpc.CompareExchangeMultiRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    for (Map.Entry<byte[], byte[]> record : expected.entrySet()) {
      TkrzwRpc.RecordState.Builder req_record = request.addExpectedBuilder();
      req_record.setKey(ByteString.copyFrom(record.getKey()));
      if (record.getValue() != null) {
        req_record.setExistence(true);
        req_record.setValue(ByteString.copyFrom(record.getValue()));
      }
    }
    for (Map.Entry<byte[], byte[]> record : desired.entrySet()) {
      TkrzwRpc.RecordState.Builder req_record = request.addDesiredBuilder();
      req_record.setKey(ByteString.copyFrom(record.getKey()));
      if (record.getValue() != null) {
        req_record.setExistence(true);
        req_record.setValue(ByteString.copyFrom(record.getValue()));
      }
    }
    TkrzwRpc.CompareExchangeMultiResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .compareExchangeMulti(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Compares the values of records and exchanges if the condition meets, with string data.
   * @param expected The record keys and their expected values.  If the data is null, no existing
   * record is expected.
   * @param desired The record keys and their desired values.  If the data is null, the record
   * is to be removed.
   * @return The result status.  If the condition doesn't meet, INFEASIBLE_ERROR is returned.
   */
  public Status compareExchangeMultiString(
      Map<String, String> expected, Map<String, String> desired) {
    Map<byte[], byte[]> rawExpected = new HashMap<byte[], byte[]>();
    for (Map.Entry<String, String> record : expected.entrySet()) {
      byte[] rawKey = record.getKey().getBytes(StandardCharsets.UTF_8);
      String value = record.getValue();
      byte[] rawValue = value == null ? null : value.getBytes(StandardCharsets.UTF_8);
      rawExpected.put(rawKey, rawValue);
    }
    Map<byte[], byte[]> rawDesired = new HashMap<byte[], byte[]>();
    for (Map.Entry<String, String> record : desired.entrySet()) {
      byte[] rawKey = record.getKey().getBytes(StandardCharsets.UTF_8);
      String value = record.getValue();
      byte[] rawValue = value == null ? null : value.getBytes(StandardCharsets.UTF_8);
      rawDesired.put(rawKey, rawValue);
    }
    return compareExchangeMulti(rawExpected, rawDesired);
  }

  /**
   * Changes the key of a record.
   * @param oldKey The old key of the record.
   * @param newKey The new key of the record.
   * @param overwrite Whether to overwrite the existing record of the new key.
   * @param copying Whether to retain the record of the old key.
   * @return The result status.  If there's no matching record to the old key, NOT_FOUND_ERROR
   * is returned.  If the overwrite flag is false and there is an existing record of the new key,
   * DUPLICATION ERROR is returned.
   * @note This method is done atomically.  The other threads observe that the record has either
   * the old key or the new key.  No intermediate states are observed.
   */
  public Status rekey(byte[] oldKey, byte[] newKey, boolean overwrite, boolean copying) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.RekeyRequest.Builder request = TkrzwRpc.RekeyRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setOldKey(ByteString.copyFrom(oldKey));
    request.setNewKey(ByteString.copyFrom(newKey));
    request.setOverwrite(overwrite);
    request.setCopying(copying);
    TkrzwRpc.RekeyResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .rekey(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Changes the key of a record, with string data.
   * @param oldKey The old key of the record.
   * @param newKey The new key of the record.
   * @param overwrite Whether to overwrite the existing record of the new key.
   * @param copying Whether to retain the record of the old key.
   * @return The result status.
   */
  public Status rekey(String oldKey, String newKey, boolean overwrite, boolean copying) {
    return rekey(oldKey.getBytes(StandardCharsets.UTF_8),
                 newKey.getBytes(StandardCharsets.UTF_8), overwrite, copying);
  }

  /**
   * Gets the first record and removes it.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return A pair of the key and the value of the first record, or null on failure.
   */
  public byte[][] popFirst(Status status) {
    if (stub_ == null) {
      if (status != null) {
        status.set(Status.PRECONDITION_ERROR, "not opened connection");
      }
      return null;
    }
    TkrzwRpc.PopFirstRequest.Builder request = TkrzwRpc.PopFirstRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    TkrzwRpc.PopFirstResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .popFirst(request.build());
    } catch (Exception e) {
      if (status != null) {
        status.set(Status.NETWORK_ERROR, e.toString());
      }
      return null;
    }
    if (status != null) {
      status.set(response.getStatus());
    }
    if (response.getStatus().getCode() == 0) {
      byte[][] record = new byte[2][];
      record[0] = response.getKey().toByteArray();
      record[1] = response.getValue().toByteArray();
      return record;
    }
    return null;
  }

  /**
   * Gets the first record and removes it, without status assingment.
   * @return A pair of the key and the value of the first record, or null on failure.
   */
  public byte[][] popFirst() {
    return popFirst(null);
  }

  /**
   * Gets the first record as strings and removes it.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return A pair of the key and the value of the first record, or null on failure.
   */
  public String[] popFirstString(Status status) {
    byte[][] record = popFirst(status);
    if (record == null) {
      return null;
    }
    String[] str_record = new String[2];
    str_record[0] = new String(record[0], StandardCharsets.UTF_8);
    str_record[1] = new String(record[1], StandardCharsets.UTF_8);
    return str_record;
  }

  /**
   * Gets the first record as strings and removes it, without status assingment.
   * @return A pair of the key and the value of the first record, or null on failure.
   */
  public String[] popFirstString() {
    return popFirstString(null);
  }

  /**
   * Adds a record with a key of the current timestamp.
   * @param value The value of the record.
   * @param wtime The current wall time used to generate the key.  If it is negative, the system
   * clock is used.
   * @return The result status.
   * @note The key is generated as an 8-bite big-endian binary string of the timestamp.  If
   * there is an existing record matching the generated key, the key is regenerated and the
   * attempt is repeated until it succeeds.
   */
  public Status pushLast(byte[] value, double wtime) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.PushLastRequest.Builder request = TkrzwRpc.PushLastRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setValue(ByteString.copyFrom(value));
    request.setWtime(wtime);
    TkrzwRpc.PushLastResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .pushLast(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Adds a record with a key of the current timestamp.
   * @param value The value of the record.
   * @param wtime The current wall time used to generate the key.  If it is negative, the system
   * clock is used.
   * @return The result status.
   */
  public Status pushLast(String value, double wtime) {
    return pushLast(value.getBytes(StandardCharsets.UTF_8), wtime);
  }

  /**
   * Gets the number of records.
   * @return The number of records on success, or -1 on failure.
   */
  public long count() {
    if (stub_ == null) {
      return -1;
    }
    TkrzwRpc.CountRequest.Builder request = TkrzwRpc.CountRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    TkrzwRpc.CountResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .count(request.build());
    } catch (Exception e) {
      return -1;
    }
    return response.getCount();
  }

  /**
   * Gets the current file size of the database.
   * @return The current file size of the database, or -1 on failure.
   */
  public long getFileSize() {
    if (stub_ == null) {
      return -1;
    }
    TkrzwRpc.GetFileSizeRequest.Builder request = TkrzwRpc.GetFileSizeRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    TkrzwRpc.GetFileSizeResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .getFileSize(request.build());
    } catch (Exception e) {
      return -1;
    }
    return response.getFileSize();
  }

  /**
   * Removes all records.
   * @return The result status.
   */
  public Status clear() {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.ClearRequest.Builder request = TkrzwRpc.ClearRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    TkrzwRpc.ClearResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .clear(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Rebuilds the entire database.
   * @param params Optional parameters.  If it is null, it is ignored.
   * @return The result status.
   * @note The optional parameters are the same as the Open method.  Omitted tuning parameters
   * are kept the same or implicitly optimized.  If it is null, it is ignored.
   * <p>In addition, HashDBM, TreeDBM, and SkipDBM supports the following parameters.
   * <ul>
   * <li>skip_broken_records (bool): If true, the operation continues even if there are broken
   * records which can be skipped.
   * <li>sync_hard (bool): If true, physical synchronization with the hardware is done before
   * finishing the rebuilt file.
   * </ul>
   */
  public Status rebuild(Map<String, String> params) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.RebuildRequest.Builder request = TkrzwRpc.RebuildRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    if (params != null) {
      for (Map.Entry<String, String> param : params.entrySet()) {
        TkrzwRpc.StringPair.Builder req_param = request.addParamsBuilder();
        req_param.setFirst(param.getKey());
        req_param.setSecond(param.getValue());
      }
    }
    TkrzwRpc.RebuildResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .rebuild(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Rebuilds the entire database, without optional parameters.
   * @return The result status.
   */
  public Status rebuild() {
    return rebuild(null);
  }

  /**
   * Checks whether the database should be rebuilt.
   * @return True to be optimized or false with no necessity.
   */
  public boolean shouldBeRebuilt() {
    if (stub_ == null) {
      return false;
    }
    TkrzwRpc.ShouldBeRebuiltRequest.Builder request =
        TkrzwRpc.ShouldBeRebuiltRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    TkrzwRpc.ShouldBeRebuiltResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .shouldBeRebuilt(request.build());
    } catch (Exception e) {
      return false;
    }
    return response.getTobe();
  }

  /**
   * Synchronizes the content of the database to the file system.
   * @param hard True to do physical synchronization with the hardware or false to do only
   * logical synchronization with the file system.
   * @param params Optional parameters.  If it is null, it is ignored.
   * @return The result status.
   * @note Only SkipDBM uses the optional parameters.  The "merge" parameter specifies paths
   * of databases to merge, separated by colon.  The "reducer" parameter specifies the reducer
   * to apply to records of the same key.  "ReduceToFirst", "ReduceToSecond", "ReduceToLast",
   * etc are supported.
   */
  public Status synchronize(boolean hard, Map<String, String> params) {
    if (stub_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "not opened connection");
    }
    TkrzwRpc.SynchronizeRequest.Builder request = TkrzwRpc.SynchronizeRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setHard(hard);
    if (params != null) {
      for (Map.Entry<String, String> param : params.entrySet()) {
        TkrzwRpc.StringPair.Builder req_param = request.addParamsBuilder();
        req_param.setFirst(param.getKey());
        req_param.setSecond(param.getValue());
      }
    }
    TkrzwRpc.SynchronizeResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .synchronize(request.build());
    } catch (Exception e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Synchronizes the content of the database to the file system.
   * @param hard True to do physical synchronization with the hardware or false to do only
   * logical synchronization with the file system.
   * @return The result status.
   */
  public Status synchronize(boolean hard) {
    return synchronize(hard, null);
  }

  /**
   * Searches the database and get keys which match a pattern.
   * @param mode The search mode.  "contain" extracts keys containing the pattern.  "begin"
   * extracts keys beginning with the pattern.  "end" extracts keys ending with the pattern.
   * "regex" extracts keys partially matches the pattern of a regular expression.  "edit"
   * extracts keys whose edit distance to the pattern is the least.  "editbin" extracts
   * keys whose edit distance to the binary pattern is the least.
   * @param pattern The pattern for matching.
   * @param capacity The maximum records to obtain.  0 means unlimited.
   * @return An array of keys matching the condition.
   */
  public byte[][] search(String mode, byte[] pattern, int capacity) {
    if (stub_ == null) {
      return new byte[0][];
    }
    TkrzwRpc.SearchRequest.Builder request = TkrzwRpc.SearchRequest.newBuilder();
    request.setDbmIndex(dbmIndex_);
    request.setMode(mode);
    request.setPattern(ByteString.copyFrom(pattern));
    request.setCapacity(capacity);
    TkrzwRpc.SearchResponse response;
    try {
      response = stub_.withDeadlineAfter((long)(timeout_ * 1000), TimeUnit.SECONDS)
          .search(request.build());
    } catch (Exception e) {
      return new byte[0][];
    }
    byte[][] keys = new byte[response.getMatchedCount()][];
    int num_keys = 0;
    for (ByteString key : response.getMatchedList()) {
      keys[num_keys++] = key.toByteArray();
    }
    return keys;
  }

  /**
   * Searches the database and get keys which match a pattern, with string data.
   * @param mode The search mode.  "contain" extracts keys containing the pattern.  "begin"
   * extracts keys beginning with the pattern.  "end" extracts keys ending with the pattern.
   * "regex" extracts keys partially matches the pattern of a regular expression.  "edit"
   * extracts keys whose edit distance to the UTF-8 pattern is the least.  "editbin" extracts
   * keys whose edit distance to the binary pattern is the least.
   * @param pattern The pattern for matching.
   * @param capacity The maximum records to obtain.  0 means unlimited.
   * @return An array of keys matching the condition.
   */
  public String[] search(String mode, String pattern, int capacity) {
    byte[][] keys = search(mode, pattern.getBytes(StandardCharsets.UTF_8), capacity);
    String[] strKeys = new String[keys.length];
    for (int i = 0; i < keys.length; i++) {
      strKeys[i] = new String(keys[i], StandardCharsets.UTF_8);
    }
    return strKeys;
  }

  /**
   * Makes an iterator for each record.
   * @return The iterator for each record.
   * @note Every iterator should be destructed explicitly by the "destruct" method.
   */
  public Iterator makeIterator() {
    return new Iterator(this);
  }

  /**
   * Gets a string representation of the database.
   */
  public String toString() {
    String expr = "tkrzw_rpc.RemoteDBM(";
    if (channel_ == null) {
      expr += "not connected";
    } else {
      expr += "connected";
    }
    expr += ":0x" + Integer.toHexString(System.identityHashCode(this)) + ")";
    return expr;
  }

  /** The channel to the server. */
  ManagedChannel channel_;
  /** The blocking stub. */
  DBMServiceGrpc.DBMServiceBlockingStub stub_;
  /** The non-blocking stub. */
  DBMServiceGrpc.DBMServiceStub asyncStub_;
  /** The timeout for each RPC call. */
  double timeout_;
  /** The index of the database to operate. */
  int dbmIndex_;
}

// END OF FILE
