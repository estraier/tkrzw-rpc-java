/*************************************************************************************************
 * Iterator interface
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
import io.grpc.stub.StreamObserver;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * Response handler.
 */
class ResponseHandler implements StreamObserver<TkrzwRpc.IterateResponse> {
  /**
   * Start the operation.
   */
  void Start() throws Throwable {
    if (error_ != null) {
      throw error_;
    }
    callLatch_ = new CountDownLatch(1);
    response_ = null;
  }

  /**
   * Gets the response of an operation.
   * @return The response object.
   */
  TkrzwRpc.IterateResponse Get() {
    while (true) {
      try {
        callLatch_.await();
      } catch (InterruptedException e) {
        continue;
      }
      break;
    }
    if (response_ == null) {
      throw new RuntimeException("closed session");
    }
    return response_;
  }

  /**
   * The action for each iteration.
   * @param response The response object.
   */
  @Override
  public void onNext(TkrzwRpc.IterateResponse response) {
    response_ = response;
    callLatch_.countDown();
  }

  /**
   * The action on error.
   * @param error The error object.
   */
  @Override
  public void onError(Throwable error) {
    error_ = error;
    callLatch_.countDown();
  }

  /**
   * The action on completion.
   */
  @Override
  public void onCompleted() {
    callLatch_.countDown();
  }

  /** The current reponse object. */
  TkrzwRpc.IterateResponse response_;
  /** The thrown error. */
  Throwable error_;
  /** The latch to await the current call. */
  CountDownLatch callLatch_;
}

/**
 * Iterator for each record.
 * @note An iterator is made by the "makeIterator" method of RemoteDBM.  Every unused iterator
 * object should be destructed explicitly by the "destruct" method to free resources.
 */
public class Iterator {
  /** The database object. */
  RemoteDBM dbm_;
  /** The response handler. */
  ResponseHandler response_handler_;
  /** The request handler. */
  StreamObserver<TkrzwRpc.IterateRequest> request_handler_;

  /**
   * Constructor.
   * @param dbm The database to scan.
   */
  Iterator(RemoteDBM dbm) {
    dbm_ = dbm;
    try {
      response_handler_ = new ResponseHandler();
      request_handler_ = dbm.asyncStub_.iterate(response_handler_);
    } catch (Exception e) {
      if (response_handler_ != null) {
        response_handler_.error_ = e;
      }
    }
  }

  /**
   * Destructs the object and releases resources.
   */
  public void destruct() {
    if (response_handler_ != null) {
      try {
        request_handler_.onCompleted();
      } catch (Exception e) {
      }
    }
    dbm_ = null;
    response_handler_ = null;
    request_handler_ = null;
  }

  /**
   * Initializes the iterator to indicate the first record.
   * @return The result status.
   * @note Even if there's no record, the operation doesn't fail.
   */
  public Status first() {
    if (response_handler_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "destructed iterator");
    }
    if (response_handler_.error_ != null) {
      return new Status(Status.NETWORK_ERROR, response_handler_.error_.toString());
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_FIRST);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Initializes the iterator to indicate the last record.
   * @return The result status.
   * @note Even if there's no record, the operation doesn't fail.  This method is suppoerted
   * only by ordered databases.
   */
  public Status last(){
    if (response_handler_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "destructed iterator");
    }
    if (response_handler_.error_ != null) {
      return new Status(Status.NETWORK_ERROR, response_handler_.error_.toString());
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_LAST);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Initializes the iterator to indicate a specific record.
   * @param key The key of the record to look for.
   * @return The result status.
   * @note Ordered databases can support "lower bound" jump; If there's no record with the
   * same key, the iterator refers to the first record whose key is greater than the given key.
   * The operation fails with unordered databases if there's no record with the same key.
   */
  public Status jump(byte[] key) {
    if (response_handler_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "destructed iterator");
    }
    if (response_handler_.error_ != null) {
      return new Status(Status.NETWORK_ERROR, response_handler_.error_.toString());
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_JUMP);
    request.setKey(ByteString.copyFrom(key));
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Initializes the iterator to indicate a specific record, with string data.
   * @param key The key of the record to look for.
   * @return The result status.
   * @note Ordered databases can support "lower bound" jump; If there's no record with the
   * same key, the iterator refers to the first record whose key is greater than the given key.
   * The operation fails with unordered databases if there's no record with the same key.
   */
  public Status jump(String key) {
    return jump(key.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Initializes the iterator to indicate the last record whose key is lower than a given key.
   * @param key The key to compare with.
   * @param inclusive If true, the considtion is inclusive: equal to or lower than the key.
   * @return The result status.
   * @note Even if there's no matching record, the operation doesn't fail.  This method is
   * suppoerted only by ordered databases.
   */
  public Status jumpLower(byte[] key, boolean inclusive) {
    if (response_handler_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "destructed iterator");
    }
    if (response_handler_.error_ != null) {
      return new Status(Status.NETWORK_ERROR, response_handler_.error_.toString());
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_JUMP_LOWER);
    request.setKey(ByteString.copyFrom(key));
    request.setJumpInclusive(inclusive);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Initializes the iterator to indicate the last record whose key is lower, with string data.
   * @param key The key to compare with.
   * @param inclusive If true, the considtion is inclusive: equal to or lower than the key.
   * @return The result status.
   * @note Even if there's no matching record, the operation doesn't fail.  This method is
   * suppoerted only by ordered databases.
   */
  public Status jumpLower(String key, boolean inclusive) {
    return jumpLower(key.getBytes(StandardCharsets.UTF_8), inclusive);
  }

  /**
   * Initializes the iterator to indicate the first record whose key is upper than a given key.
   * @param key The key to compare with.
   * @param inclusive If true, the considtion is inclusive: equal to or upper than the key.
   * @return The result status.
   * @note Even if there's no matching record, the operation doesn't fail.  This method is
   * suppoerted only by ordered databases.
   */
  public Status jumpUpper(byte[] key, boolean inclusive) {
    if (response_handler_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "destructed iterator");
    }
    if (response_handler_.error_ != null) {
      return new Status(Status.NETWORK_ERROR, response_handler_.error_.toString());
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_JUMP_UPPER);
    request.setKey(ByteString.copyFrom(key));
    request.setJumpInclusive(inclusive);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Initializes the iterator to indicate the first record whose key is upper, with string data.
   * @param key The key to compare with.
   * @param inclusive If true, the considtion is inclusive: equal to or upper than the key.
   * @return The result status.
   * @note Even if there's no matching record, the operation doesn't fail.  This method is
   * suppoerted only by ordered databases.
   */
  public Status jumpUpper(String key, boolean inclusive) {
    return jumpUpper(key.getBytes(StandardCharsets.UTF_8), inclusive);
  }

  /**
   * Moves the iterator to the next record.
   * @return The result status.
   * @note If the current record is missing, the operation fails.  Even if there's no next
   * record, the operation doesn't fail.
   */
  public Status next() {
    if (response_handler_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "destructed iterator");
    }
    if (response_handler_.error_ != null) {
      return new Status(Status.NETWORK_ERROR, response_handler_.error_.toString());
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_NEXT);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Moves the iterator to the previous record.
   * @return The result status.
   * @note If the current record is missing, the operation fails.  Even if there's no previous
   * record, the operation doesn't fail.  This method is suppoerted only by ordered databases.
   */
  public Status previous() {
    if (response_handler_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "destructed iterator");
    }
    if (response_handler_.error_ != null) {
      return new Status(Status.NETWORK_ERROR, response_handler_.error_.toString());
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_PREVIOUS);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Gets the key and the value of the current record of the iterator.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return A pair of the key and the value of the current record, or null on failure.
   */
  public byte[][] get(Status status) {
    if (response_handler_ == null) {
      if (status != null) {
        status.set(Status.PRECONDITION_ERROR, "destructed iterator");
      }
      return null;
    }
    if (response_handler_.error_ != null) {
      if (status != null) {
        status.set(Status.NETWORK_ERROR, response_handler_.error_.toString());
      }
      return null;
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_GET);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
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
   * Gets the key and the value of the current record of the iterator, witout status assingment.
   * @return A pair of the key and the value of the current record, or null on failure.
   */
  public byte[][] get() {
    return get(null);
  }

  /**
   * Gets the key and the value of the current record, as string data.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return A pair of the key and the value of the current record, or null on failure.
   */
  public String[] getString(Status status) {
    byte[][] record = get(status);
    if (record == null) {
      return null;
    }
    String[] str_record = new String[2];
    str_record[0] = new String(record[0], StandardCharsets.UTF_8);
    str_record[1] = new String(record[1], StandardCharsets.UTF_8);
    return str_record;
  }

  /**
   * Gets the key and the value of the current record, as string data, without status assignemnt.
   * @return A pair of the key and the value of the current record, or null on failure.
   */
  public String[] getString() {
    return getString(null);
  }

  /**
   * Gets the key of the current record.
   * @return The key of the current record, or null on failure.
   */
  public byte[] getKey() {
    if (response_handler_ == null) {
      return null;
    }
    if (response_handler_.error_ != null) {
      return null;
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_GET);
    request.setOmitValue(true);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return null;
    }
    if (response.getStatus().getCode() == 0) {
      return response.getKey().toByteArray();
    }
    return null;
  }

  /**
   * Gets the key of the current record, as string data.
   * @return The key of the current record, or null on failure.
   */
  public String getKeyString() {
    byte[] key = getKey();
    if (key == null) {
      return null;
    }
    return new String(key, StandardCharsets.UTF_8);
  }

  /**
   * Gets the key of the current record.
   * @return The key of the current record, or null on failure.
   */
  public byte[] getValue() {
    if (response_handler_ == null) {
      return null;
    }
    if (response_handler_.error_ != null) {
      return null;
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_GET);
    request.setOmitKey(true);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return null;
    }
    if (response.getStatus().getCode() == 0) {
      return response.getValue().toByteArray();
    }
    return null;
  }

  /**
   * Gets the key of the current record, as string data.
   * @return The key of the current record, or null on failure.
   */
  public String getValueString() {
    byte[] value = getValue();
    if (value == null) {
      return null;
    }
    return new String(value, StandardCharsets.UTF_8);
  }

  /**
   * Sets the value of the current record.
   * @param value The value of the record.
   * @return The result status.
   */
  public Status set(byte[] value) {
    if (response_handler_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "destructed iterator");
    }
    if (response_handler_.error_ != null) {
      return new Status(Status.NETWORK_ERROR, response_handler_.error_.toString());
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_SET);
    request.setValue(ByteString.copyFrom(value));
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Sets the value of the current record, with string data.
   * @param value The value of the record.
   * @return The result status.
   */
  public Status set(String value) {
    return set(value.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Removes the current record.
   * @return The result status.
   * @note If possible, the iterator moves to the next record.
   */
  public Status remove() {
    if (response_handler_ == null) {
      return new Status(Status.PRECONDITION_ERROR, "destructed iterator");
    }
    if (response_handler_.error_ != null) {
      return new Status(Status.NETWORK_ERROR, response_handler_.error_.toString());
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_REMOVE);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
      return new Status(Status.NETWORK_ERROR, e.toString());
    }
    return new Status(response.getStatus());
  }

  /**
   * Gets the current record and moves the iterator to the next record.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return A pair of the key and the value of the current record, or null on failure.
   */
  public byte[][] step(Status status) {
    if (response_handler_ == null) {
      if (status != null) {
        status.set(Status.PRECONDITION_ERROR, "destructed iterator");
      }
      return null;
    }
    if (response_handler_.error_ != null) {
      if (status != null) {
        status.set(Status.NETWORK_ERROR, response_handler_.error_.toString());
      }
      return null;
    }
    TkrzwRpc.IterateRequest.Builder request = TkrzwRpc.IterateRequest.newBuilder();
    request.setDbmIndex(dbm_.dbmIndex_);
    request.setOperation(TkrzwRpc.IterateRequest.OpType.OP_STEP);
    TkrzwRpc.IterateResponse response;
    try {
      response_handler_.Start();
      request_handler_.onNext(request.build());
      response = response_handler_.Get();
    } catch (Throwable e) {
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
   * Gets the current record and moves the iterator to the next record, witout status assingment.
   * @return A pair of the key and the value of the current record, or null on failure.
   */
  public byte[][] step() {
    return step(null);
  }

  /**
   * Gets the current record and moves the iterator to the next record, as string data.
   * @param status The status object to store the result status.  If it is null, it is ignored.
   * @return A pair of the key and the value of the current record, or null on failure.
   */
  public String[] stepString(Status status) {
    byte[][] record = step(status);
    if (record == null) {
      return null;
    }
    String[] str_record = new String[2];
    str_record[0] = new String(record[0], StandardCharsets.UTF_8);
    str_record[1] = new String(record[1], StandardCharsets.UTF_8);
    return str_record;
  }

  /**
   * Gets the current record and moves the iterator to the next record, as string data.
   * @return A pair of the key and the value of the current record, or null on failure.
   */
  public String[] stepString() {
    return stepString(null);
  }

  /**
   * Gets a string representation of the iterator.
   */
  public String toString() {
    String expr = "tkrzw_rpc.Iterator(";
    if (response_handler_ == null) {
      expr += "destructed";
    } else if (dbm_.channel_ == null) {
      expr += "not connected";
    } else {
      expr += "connected";
    }
    expr += ":0x" + Integer.toHexString(System.identityHashCode(this)) + ")";
    return expr;
  }

  /** The pointer to the native object */
  private long ptr_ = 0;
}

// END OF FILE
