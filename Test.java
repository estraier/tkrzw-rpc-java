/*************************************************************************************************
 * Test cases
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

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Test cases.
 */
public class Test {
  /**
   * Main routine of the test command.
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    if (args.length < 1) usage();
    int rv = 0;
    if (args[0].equals("status")) {
      rv = runStatus();
    } else if (args[0].equals("basic")) {
      rv = runBasic();
    } else if (args[0].equals("iter")) {
      rv = runIter();
    } else if (args[0].equals("thread")) {
      rv = runThread();
    } else if (args[0].equals("perf")) {
      String address = "localhost:1978";
      String auth_config = "";
      int num_iterations = 10000;
      int num_threads = 1;
      boolean is_random = false;
      for (int i = 1; i < args.length; i++) {
        String arg = args[i];
        if (arg.equals("--address")) {
          i++;
          address = args[i];
        } else if (arg.equals("--auth")) {
          i++;
          auth_config = args[i];
        } else if (arg.equals("--iter")) {
          i++;
          num_iterations = atoi(args[i]);
        } else if (arg.equals("--threads")) {
          i++;
          num_threads = atoi(args[i]);
        } else if (arg.equals("--random")) {
          is_random = true;
        } else {
          usage();
        }
      }
      rv = runPerf(address, auth_config, num_iterations, num_threads, is_random);
    } else if (args[0].equals("wicked")) {
      String address = "localhost:1978";
      String auth_config = "";
      int num_iterations = 10000;
      int num_threads = 1;
      for (int i = 1; i < args.length; i++) {
        String arg = args[i];
        if (arg.equals("--address")) {
          i++;
          address = args[i];
        } else if (arg.equals("--auth")) {
          i++;
          auth_config = args[i];
        } else if (arg.equals("--iter")) {
          i++;
          num_iterations = atoi(args[i]);
        } else if (arg.equals("--threads")) {
          i++;
          num_threads = atoi(args[i]);
        } else {
          usage();
        }
      }
      rv = runWicked(address, auth_config, num_iterations, num_threads);
    } else {
      usage();
    }
    System.gc();
    System.exit(rv);
  }

  /**
   * Prints the usage and exit.
   */
  private static void usage() {
    STDERR.printf("test cases of the Java binding\n");
    STDERR.printf("\n");
    STDERR.printf("synopsis:\n");
    STDERR.printf("  java -ea %s command arguments...\n", Test.class.getName());
    STDERR.printf("\n");
    STDERR.printf("command and arguments:\n");
    STDERR.printf("  status\n");
    STDERR.printf("  basic\n");
    STDERR.printf("  iter\n");
    STDERR.printf("  thread\n");
    STDERR.printf("  perf [--address str] [--auth str] [--iter num] [--threads num] [--random]\n");
    STDERR.printf("  wicked [--address str] [--auth str] [--iter num] [--threads num]");
    STDERR.printf("\n");
    System.exit(1);
  }

  /**
   * Creates a temporary directory which is to be removed on exit.
   */
  private static String createTempDir() {
    try {
      String tmp_dir_path = Files.createTempDirectory("tkrzw-java-").toString();
      return tmp_dir_path;
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Removes a temporary directory recursively.
   */
  private static void removeDirectory(String path) {
    try {
      Files.walk(Paths.get(path)).sorted(Comparator.reverseOrder())
          .map(Path::toFile).forEach(java.io.File::delete);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Converts a string into an integer.
   */
  private static int atoi(String str) {
    try {
      return Integer.parseInt(str);
    } catch(NumberFormatException e) {
      return 0;
    }
  }

  /**
   * Throws an error status if an expression is false.
   */
  private static void check(boolean expr) {
    if (!expr) {
      throw new AssertionError("check failed");
    }
  }

  /**
   * Gets the current timestamp.
   */
  private static double getTime() {
    Instant instant = Instant.now();
    return instant.getEpochSecond() + instant.getNano() / 1000000000.0;
  }

  /**
   * Makes a string map of the given key/value pairs.
   */
  private static Map<String, String> makeStrMap(String... elems) {
    Map<String, String> map = new HashMap<String, String>(elems.length);
    for (int i = 0; i < elems.length - 1; i += 2) {
      map.put(elems[i], elems[i + 1]);
    }
    return map;
  }

  /**
   * Runs the status test.
   */
  private static int runStatus() {
    STDOUT.printf("Running status tests:\n");
    check(Status.Code.valueOf(0).equals(Status.SUCCESS));
    check(Status.Code.valueOf(1).equals(Status.UNKNOWN_ERROR));
    check(Status.Code.valueOf(2).equals(Status.SYSTEM_ERROR));
    check(Status.Code.valueOf(256).equals(Status.UNKNOWN_ERROR));
    Status status = new Status();
    check(status.equals(new Status()));
    check(!status.equals(new Status(Status.NOT_FOUND_ERROR)));
    check(status.getCode() == Status.SUCCESS);
    check(status.getMessage().length() == 0);
    check(status.toString().equals("SUCCESS"));
    check(status.isOK());
    status.set(Status.UNKNOWN_ERROR, "foobar");
    check(status.getCode() == Status.UNKNOWN_ERROR);
    check(status.getMessage().equals("foobar"));
    check(status.toString().equals("UNKNOWN_ERROR: foobar"));
    check(status.equals(new Status(Status.UNKNOWN_ERROR, "")));
    check(status.equals(Status.UNKNOWN_ERROR));
    check(!status.isOK());
    Status s2 = new Status(Status.NOT_IMPLEMENTED_ERROR, "void");
    status.join(s2);
    check(status.toString().equals("UNKNOWN_ERROR: foobar"));
    status.set(Status.SUCCESS, "OK");
    status.join(s2);
    check(status.toString().equals("NOT_IMPLEMENTED_ERROR: void"));
    try {
      status.orDie();
      check(false);
    } catch (StatusException e) {
      check(e.getStatus().equals(status));
    }
    status.set(Status.SUCCESS, "OK");
    status.orDie();
    STDOUT.printf("  ... OK\n");
    return 0;
  }

  /**
   * Runs the basic test.
   */
  private static int runBasic() {
    STDOUT.printf("Running basic tests:\n");
    RemoteDBM dbm = new RemoteDBM();
    check(dbm.toString().indexOf("tkrzw_rpc.RemoteDBM(not connected:") == 0);
    check(dbm.connect("localhost:1978", -1).equals(Status.SUCCESS));
    check(dbm.setDBMIndex(-1).equals(Status.SUCCESS));
    Map<String, String> attrs = dbm.inspect();
    check(attrs.get("version").length() > 3);
    check(attrs.get("num_dbms").length() > 0);
    check(dbm.setDBMIndex(0).equals(Status.SUCCESS));
    attrs = dbm.inspect();
    check(attrs.get("class").length() > 0);
    check(attrs.get("num_records").length() > 0);
    Status status = new Status(Status.UNKNOWN_ERROR);
    check(dbm.echo("hello", status).equals("hello"));
    check(status.equals(Status.SUCCESS));
    check(dbm.clear().equals(Status.SUCCESS));
    check(dbm.set("one", "ichi").equals(Status.SUCCESS));
    check(dbm.set("one", "first", false).equals(Status.DUPLICATION_ERROR));
    check(dbm.set("one", "first", true).equals(Status.SUCCESS));
    check(Arrays.equals(dbm.get("one".getBytes()), "first".getBytes()));
    status.set(Status.UNKNOWN_ERROR, "");
    check(dbm.get("one", status).equals("first"));
    check(status.equals(Status.SUCCESS));
    check(dbm.get("two", status) == null);
    check(status.equals(Status.NOT_FOUND_ERROR));
    check(dbm.append("two", "second", ":").equals(Status.SUCCESS));
    check(dbm.get("two").equals("second"));
    check(dbm.append("two".getBytes(), "second".getBytes(), ":".getBytes())
          .equals(Status.SUCCESS));
    check(dbm.get("two").equals("second:second"));
    check(dbm.remove("two").equals(Status.SUCCESS));
    check(dbm.remove("two").equals(Status.NOT_FOUND_ERROR));
    check(dbm.set("日本", "東京").equals(Status.SUCCESS));
    check(dbm.get("日本").equals("東京"));
    check(dbm.remove("日本").equals(Status.SUCCESS));
    check(dbm.setMulti(Map.of("one".getBytes(), "FIRST".getBytes(),
                              "two".getBytes(), "SECOND".getBytes()), true)
          .equals(Status.SUCCESS));
    Map<byte[], byte[]> records = dbm.getMulti(new byte[][]{
        "one".getBytes(), "two".getBytes(), "three".getBytes()});
    check(records.size() == 2);
    for (Map.Entry<byte[], byte[]> record : records.entrySet()) {
      if (Arrays.equals(record.getKey(), "one".getBytes())) {
        check(Arrays.equals(record.getValue(), "FIRST".getBytes()));
      } else if (Arrays.equals(record.getKey(), "two".getBytes())) {
        check(Arrays.equals(record.getValue(), "SECOND".getBytes()));
      } else {
        check(false);
      }
    }
    check(dbm.setMultiString(Map.of("one", "[FIRST]", "two", "[SECOND]"), true)
          .equals(Status.SUCCESS));
    Map<String, String> strRecords = dbm.getMulti(new String[]{"one", "two", "three"});
    check(strRecords.size() == 2);
    check(strRecords.get("one").equals("[FIRST]"));
    check(strRecords.get("two").equals("[SECOND]"));
    check(dbm.removeMulti(new byte[][]{"one".getBytes(), "two".getBytes()})
          .equals(Status.SUCCESS));
    check(dbm.removeMulti(new String[]{"one"}).equals(Status.NOT_FOUND_ERROR));
    check(dbm.appendMulti(Map.of("one".getBytes(), "first".getBytes(),
                                 "two".getBytes(), "second".getBytes()), ":".getBytes())
          .equals(Status.SUCCESS));
    check(dbm.appendMulti(Map.of("one", "1", "two", "2"), ":").equals(Status.SUCCESS));
    strRecords = dbm.getMulti(new String[]{"one", "two"});
    check(strRecords.get("one").equals("first:1"));
    check(strRecords.get("two").equals("second:2"));
    check(dbm.compareExchange("one", "first:1", null).equals(Status.SUCCESS));
    check(dbm.get("one") == null);
    check(dbm.compareExchange("one", null, "hello").equals(Status.SUCCESS));
    check(dbm.get("one").equals("hello"));
    check(dbm.compareExchange("one", null, "hello").equals(Status.INFEASIBLE_ERROR));
    Map<String, String> expected = new HashMap<String, String>();
    expected.put("one", "hello");
    expected.put("two", "second:2");
    Map<String, String> desired = new HashMap<String, String>();
    desired.put("one", null);
    desired.put("two", null);
    check(dbm.compareExchangeMultiString(expected, desired).equals(Status.SUCCESS));
    check(dbm.get("one") == null);
    check(dbm.get("two") == null);
    expected = new HashMap<String, String>();
    expected.put("one", null);
    expected.put("two", null);
    desired = new HashMap<String, String>();
    desired.put("one", "first");
    desired.put("two", "second");
    check(dbm.compareExchangeMultiString(expected, desired).equals(Status.SUCCESS));
    check(dbm.get("one").equals("first"));
    check(dbm.get("two").equals("second"));
    status.set(Status.UNKNOWN_ERROR, "");
    check(dbm.increment("num", 5, 100, status) == 105);
    check(status.equals(Status.SUCCESS));
    check(dbm.increment("num", 5, 100, status) == 110);
    check(dbm.count() == 3);
    check(dbm.getFileSize() >= 0);
    check(dbm.rebuild().equals(Status.SUCCESS));
    check(!dbm.shouldBeRebuilt());
    check(dbm.synchronize(false).equals(Status.SUCCESS));
    for (int i = 0; i < 10; i++) {
      String expr = Integer.toString(i);
      check(dbm.set(expr, expr).equals(Status.SUCCESS));
    }
    String[] keys = dbm.search("regex", "[23]$", 5);
    check(keys.length == 2);
    for (String key : keys) {
      check(key.equals("2") || key.equals("3"));
    }
    check(dbm.disconnect().equals(Status.SUCCESS));
    dbm.destruct();
    STDOUT.printf("  ... OK\n");
    return 0;
  }

  /**
   * Runs the iterator test.
   */
  private static int runIter() {
    STDOUT.printf("Running iterator tests:\n");
    RemoteDBM dbm = new RemoteDBM();
    check(dbm.connect("localhost:1978", -1).equals(Status.SUCCESS));
    check(dbm.clear().equals(Status.SUCCESS));
    for (int i = 0; i < 10; i++) {
      String key = Integer.toString(i);
      String value = Integer.toString(i * i);
      check(dbm.set(key, value).equals(Status.SUCCESS));
    }
    Iterator iter = dbm.makeIterator();
    check(iter.toString().indexOf("tkrzw_rpc.Iterator(connected:") == 0);
    check(iter.first().equals(Status.SUCCESS));
    int count = 0;
    while (true) {
      Status status = new Status(Status.UNKNOWN_ERROR);
      byte[][] record = iter.get(status);
      if (record == null) {
        check(status.equals(Status.NOT_FOUND_ERROR));
        break;
      }
      check(status.equals(Status.SUCCESS));
      int keyNum = Integer.parseInt(new String(record[0]));
      check(Integer.parseInt(new String(record[1])) == keyNum * keyNum);
      status.set(Status.UNKNOWN_ERROR, "");
      String[] strRecord = iter.getString(status);
      check(status.equals(Status.SUCCESS));
      check(Integer.parseInt(strRecord[0]) == keyNum);
      check(Integer.parseInt(strRecord[1]) == keyNum * keyNum);
      check(iter.getKeyString().equals(Integer.toString(keyNum)));
      check(iter.getValueString().equals(Integer.toString(keyNum * keyNum)));
      check(iter.next().equals(Status.SUCCESS));
      count++;
    }
    check(count == dbm.count());
    for (int i = 0; i < count; i++) {
      String key = Integer.toString(i);
      check(iter.jump(key).equals(Status.SUCCESS));
      check(iter.getKeyString().equals(key));
    }
    Status status = iter.last();
    if (status.equals(Status.SUCCESS)) {
      count = 0;
      while (true) {
        String[] record = iter.getString(status);
        if (record == null) {
          check(status.equals(Status.NOT_FOUND_ERROR));
          break;
        } else {
          check(status.equals(Status.SUCCESS));
        }
        int keyNum = Integer.parseInt(record[0]);
        check(record[1].equals(Integer.toString(keyNum * keyNum)));
        check(iter.previous().equals(Status.SUCCESS));
        count++;
      }
      check(count == dbm.count());
      check(iter.jumpLower("0", false).equals(Status.SUCCESS));
      check(iter.getKeyString() == null);
      check(iter.jumpLower("0", true).equals(Status.SUCCESS));
      check(iter.getKeyString().equals("0"));
      check(iter.next().equals(Status.SUCCESS));
      check(iter.getKeyString().equals("1"));
      check(iter.jumpUpper("9", false).equals(Status.SUCCESS));
      check(iter.getKeyString() == null);
      check(iter.jumpUpper("9", true).equals(Status.SUCCESS));
      check(iter.getKeyString().equals("9"));
      check(iter.previous().equals(Status.SUCCESS));
      check(iter.getKeyString().equals("8"));
      check(iter.set("eight").equals(Status.SUCCESS));
      check(iter.getValueString().equals("eight"));
      check(iter.remove().equals(Status.SUCCESS));
      check(iter.getKeyString().equals("9"));
      check(iter.remove().equals(Status.SUCCESS));
      check(iter.getKeyString() == null);
      check(dbm.count() == 8);
    } else {
      STDOUT.println(status);
      check(status.equals(Status.NOT_IMPLEMENTED_ERROR));
    }
    check(dbm.clear().equals(Status.SUCCESS));
    check(dbm.pushLast("one", 0).equals(Status.SUCCESS));
    check(dbm.pushLast("two", 0).equals(Status.SUCCESS));
    check(iter.first().equals(Status.SUCCESS));
    status.set(Status.UNKNOWN_ERROR, "");
    byte[][] record = iter.step(status);
    check(status.equals(Status.SUCCESS));
    check(Arrays.equals(record[0], new byte[]{0,0,0,0,0,0,0,0}));
    check(Arrays.equals(record[1], "one".getBytes()));
    String[] strRecord = iter.stepString(status);
    check(status.equals(Status.SUCCESS));
    check(strRecord[0].equals("\0\0\0\0\0\0\0\1"));
    check(strRecord[1].equals("two"));
    strRecord = iter.stepString(status);
    check(status.equals(Status.NOT_FOUND_ERROR));
    check(strRecord == null);
    record = dbm.popFirst(status);
    check(status.equals(Status.SUCCESS));
    check(Arrays.equals(record[0], new byte[]{0,0,0,0,0,0,0,0}));
    check(Arrays.equals(record[1], "one".getBytes()));
    strRecord = dbm.popFirstString(status);
    check(status.equals(Status.SUCCESS));
    check(strRecord[0].equals("\0\0\0\0\0\0\0\1"));
    check(strRecord[1].equals("two"));
    strRecord = dbm.popFirstString(status);
    check(status.equals(Status.NOT_FOUND_ERROR));
    check(strRecord == null);
    check(dbm.count() == 0);
    iter.destruct();
    check(dbm.disconnect().equals(Status.SUCCESS));
    dbm.destruct();
    STDOUT.printf("  ... OK\n");
    return 0;
  }

  /**
   * Runs the threadc test.
   */
  private static int runThread() {
    STDOUT.printf("Running thread tests:\n");
    RemoteDBM dbm = new RemoteDBM();
    check(dbm.connect("localhost:1978", -1).equals(Status.SUCCESS));
    check(dbm.clear().equals(Status.SUCCESS));
    Map<String, String> attrs = dbm.inspect();
    String className = attrs.get("class");
    boolean is_ordered = className != null &&
        Arrays.asList("TreeDBM", "SkipDBM", "BabyDBM", "StdTreeDBM").contains(className);
    int num_records = 1000;
    int num_threads = 5;
    class Task extends Thread {
      public Task(int thid) {
        thid_ = thid;
        records_ = new HashMap<String, String>();
      }
      public void run() {
        Random rnd = new Random(thid_);
        for (int i = 0; i < num_records; i++) {
          int key_num = rnd.nextInt(num_records);
          key_num = key_num - key_num % num_threads + thid_;
          String key = String.format("%d", key_num);
          String value = String.format("%d", key_num * key_num);
          if (rnd.nextInt(num_records) == 0) {
            check(dbm.rebuild().equals(Status.SUCCESS));
          } else if (rnd.nextInt(10) == 0) {
            Iterator iter = dbm.makeIterator();
            iter.jump(key);
            Status status = new Status();
            String[] record = iter.getString(status);
            if (status.equals(Status.SUCCESS)) {
              check(record.length == 2);
              if (!is_ordered) {
                check(record[0].equals(key));
                check(record[1].equals(value));
              }
              status = iter.next();
              check(status.equals(Status.SUCCESS) || status.equals(Status.NOT_FOUND_ERROR));
            }
            iter.destruct();
          } else if (rnd.nextInt(4) == 0) {
            Status status = new Status();
            String rec_value = dbm.get(key, status);
            if (status.equals(Status.SUCCESS)) {
              check(rec_value.equals(value));
            } else {
              status.equals(Status.NOT_FOUND_ERROR);
            }
          } else if (rnd.nextInt(4) == 0) {
            Status status = dbm.remove(key);
            if (status.equals(Status.SUCCESS)) {
              records_.remove(key);
            } else {
              status.equals(Status.NOT_FOUND_ERROR);
            }
          } else {
            boolean overwrite = rnd.nextInt(2) == 0;
            Status status = dbm.set(key, value, overwrite);
            if (status.equals(Status.SUCCESS)) {
              records_.put(key, value);
            } else {
              status.equals(Status.DUPLICATION_ERROR);
            }
          }
        }
      }
      private int thid_;
      public HashMap<String, String> records_;
    }
    Task[] tasks = new Task[num_threads];
    for (int thid = 0; thid < num_threads; thid++) {
      tasks[thid] = new Task(thid);
      tasks[thid].start();
    }
    HashMap<String, String> records = new HashMap<String, String>();
    for (int thid = 0; thid < num_threads; thid++) {
      try {
        tasks[thid].join();
      } catch (java.lang.InterruptedException e) {
        throw new RuntimeException(e);
      }
      for (Map.Entry<String, String> entry : tasks[thid].records_.entrySet()) {
        records.put(entry.getKey(), entry.getValue());
      }
    }
    check(records.size() == dbm.count());
    for (Map.Entry<String, String> entry : records.entrySet()) {
      Status status = new Status();
      String value = dbm.get(entry.getKey(), status);
      check(status.equals(Status.SUCCESS));
      check(value.equals(entry.getValue()));
    }
    check(dbm.disconnect().equals(Status.SUCCESS));
    dbm.destruct();
    STDOUT.printf("  ... OK\n");
    return 0;
  }

  /**
   * Runs the perf test.
   */
  private static int runPerf(String address, String auth_config,
                             int num_iterations, int num_threads, boolean is_random) {
    STDOUT.printf("address: %s\n", address);
    STDOUT.printf("num_iterations: %d\n", num_iterations);
    STDOUT.printf("num_threads: %d\n", num_threads);
    STDOUT.printf("is_random: %s\n", is_random);
    STDOUT.printf("\n");
    System.gc();
    RemoteDBM dbm = new RemoteDBM();
    dbm.connect(address, -1, auth_config).orDie();
    check(dbm.clear().equals(Status.SUCCESS));
    class Echoer extends Thread {
      public Echoer(int thid) {
        thid_ = thid;
      }
      public void run() {
        Random rnd = new Random(thid_);
        for (int i = 0; i < num_iterations; i++) {
          int key_num = 0;
          if (is_random) {
            key_num = rnd.nextInt(num_threads * num_iterations);
          } else {
            key_num = thid_ * num_iterations + i;
          }
          String key = String.format("%08d", key_num);
          Status status = new Status();
          dbm.echo(key, status);
          status.orDie();
          int seq = i + 1;
          if (thid_ == 0 && seq % (num_iterations / 500) == 0) {
            STDOUT.printf(".");
            if (seq % (num_iterations / 10) == 0) {
              STDOUT.printf(" (%08d)\n", seq);
            }
            STDOUT.flush();
          }
        }
      }
      private int thid_;
    }
    STDOUT.printf("Echoing:\n");
    double start_time = getTime();
    Echoer[] echoers = new Echoer[num_threads];
    for (int thid = 0; thid < num_threads; thid++) {
      echoers[thid] = new Echoer(thid);
      echoers[thid].start();
    }
    for (int thid = 0; thid < num_threads; thid++) {
      try {
        echoers[thid].join();
      } catch (java.lang.InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    double end_time = getTime();
    double elapsed = end_time - start_time;
    System.gc();
    STDOUT.printf("Echoing done: time=%.3f qps=%.0f\n",
                  elapsed, num_iterations * num_threads / elapsed);
    STDOUT.print("\n");




    STDOUT.printf("Echoing2:\n");
    start_time = getTime();
    echoers = new Echoer[num_threads];
    for (int thid = 0; thid < num_threads; thid++) {
      echoers[thid] = new Echoer(thid);
      echoers[thid].start();
    }
    for (int thid = 0; thid < num_threads; thid++) {
      try {
        echoers[thid].join();
      } catch (java.lang.InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    end_time = getTime();
    elapsed = end_time - start_time;
    System.gc();
    STDOUT.printf("Echoing done: time=%.3f qps=%.0f\n",
                  elapsed, num_iterations * num_threads / elapsed);
    STDOUT.print("\n");




    class Setter extends Thread {
      public Setter(int thid) {
        thid_ = thid;
      }
      public void run() {
        Random rnd = new Random(thid_);
        for (int i = 0; i < num_iterations; i++) {
          int key_num = 0;
          if (is_random) {
            key_num = rnd.nextInt(num_threads * num_iterations);
          } else {
            key_num = thid_ * num_iterations + i;
          }
          String key = String.format("%08d", key_num);
          dbm.set(key, key).orDie();
          int seq = i + 1;
          if (thid_ == 0 && seq % (num_iterations / 500) == 0) {
            STDOUT.printf(".");
            if (seq % (num_iterations / 10) == 0) {
              STDOUT.printf(" (%08d)\n", seq);
            }
            STDOUT.flush();
          }
        }
      }
      private int thid_;
    }
    STDOUT.printf("Setting:\n");
    start_time = getTime();
    Setter[] setters = new Setter[num_threads];
    for (int thid = 0; thid < num_threads; thid++) {
      setters[thid] = new Setter(thid);
      setters[thid].start();
    }
    for (int thid = 0; thid < num_threads; thid++) {
      try {
        setters[thid].join();
      } catch (java.lang.InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    dbm.synchronize(false).orDie();
    end_time = getTime();
    elapsed = end_time - start_time;
    System.gc();
    STDOUT.printf("Setting done: num_records=%d file_size=%d time=%.3f qps=%.0f\n",
                  dbm.count(), dbm.getFileSize(), elapsed,
                  num_iterations * num_threads / elapsed);
    STDOUT.print("\n");
    class Getter extends Thread {
      public Getter(int thid) {
        thid_ = thid;
      }
      public void run() {
        Random rnd = new Random(thid_);
        for (int i = 0; i < num_iterations; i++) {
          int key_num = 0;
          if (is_random) {
            key_num = rnd.nextInt(num_threads * num_iterations);
          } else {
            key_num = thid_ * num_iterations + i;
          }
          String key = String.format("%08d", key_num);
          Status status = new Status();
          dbm.get(key, status);
          if (!status.equals(Status.NOT_FOUND_ERROR)) {
            status.orDie();
          }
          int seq = i + 1;
          if (thid_ == 0 && seq % (num_iterations / 500) == 0) {
            STDOUT.printf(".");
            if (seq % (num_iterations / 10) == 0) {
              STDOUT.printf(" (%08d)\n", seq);
            }
            STDOUT.flush();
          }
        }
      }
      private int thid_;
    }
    STDOUT.printf("Getting:\n");
    start_time = getTime();
    Getter[] getters = new Getter[num_threads];
    for (int thid = 0; thid < num_threads; thid++) {
      getters[thid] = new Getter(thid);
      getters[thid].start();
    }
    for (int thid = 0; thid < num_threads; thid++) {
      try {
        getters[thid].join();
      } catch (java.lang.InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    end_time = getTime();
    elapsed = end_time - start_time;
    System.gc();
    STDOUT.printf("Getting done: num_records=%d file_size=%d time=%.3f qps=%.0f\n",
                  dbm.count(), dbm.getFileSize(), elapsed,
                  num_iterations * num_threads / elapsed);
    STDOUT.print("\n");
    class Remover extends Thread {
      public Remover(int thid) {
        thid_ = thid;
      }
      public void run() {
        Random rnd = new Random(thid_);
        for (int i = 0; i < num_iterations; i++) {
          int key_num = 0;
          if (is_random) {
            key_num = rnd.nextInt(num_threads * num_iterations);
          } else {
            key_num = thid_ * num_iterations + i;
          }
          String key = String.format("%08d", key_num);
          Status status = dbm.remove(key);
          if (!status.equals(Status.NOT_FOUND_ERROR)) {
            status.orDie();
          }
          int seq = i + 1;
          if (thid_ == 0 && seq % (num_iterations / 500) == 0) {
            STDOUT.printf(".");
            if (seq % (num_iterations / 10) == 0) {
              STDOUT.printf(" (%08d)\n", seq);
            }
            STDOUT.flush();
          }
        }
      }
      private int thid_;
    }
    STDOUT.printf("Removing:\n");
    start_time = getTime();
    Remover[] removers = new Remover[num_threads];
    for (int thid = 0; thid < num_threads; thid++) {
      removers[thid] = new Remover(thid);
      removers[thid].start();
    }
    for (int thid = 0; thid < num_threads; thid++) {
      try {
        removers[thid].join();
      } catch (java.lang.InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    dbm.synchronize(false).orDie();
    end_time = getTime();
    elapsed = end_time - start_time;
    System.gc();
    STDOUT.printf("Removing done: num_records=%d file_size=%d time=%.3f qps=%.0f\n",
                  dbm.count(), dbm.getFileSize(), elapsed,
                  num_iterations * num_threads / elapsed);
    STDOUT.print("\n");
    dbm.disconnect().orDie();
    dbm.destruct();
    return 0;
  }

  /**
   * Runs the wicked test.
   */
  private static int runWicked(String address, String auth_config,
                               int num_iterations, int num_threads) {
    STDOUT.printf("address: %s\n", address);
    STDOUT.printf("num_iterations: %d\n", num_iterations);
    STDOUT.printf("num_threads: %d\n", num_threads);
    STDOUT.printf("\n");
    System.gc();
    RemoteDBM dbm = new RemoteDBM();
    dbm.connect(address, -1, auth_config).orDie();
    dbm.clear().orDie();
    Map<String, String> attrs = dbm.inspect();
    String className = attrs.get("class");
    boolean is_ordered = className != null &&
        Arrays.asList("TreeDBM", "SkipDBM", "BabyDBM", "StdTreeDBM").contains(className);
    class Task extends Thread {
      public Task(int thid) {
        thid_ = thid;
      }
      public void run() {
        Random rnd = new Random(thid_);
        for (int i = 0; i < num_iterations; i++) {
          int key_num = rnd.nextInt(num_threads * num_iterations);
          String key = String.format("%d", key_num);
          String value = String.format("%d", i);
          if (rnd.nextInt(num_iterations / 2) == 0) {
            dbm.rebuild().orDie();
          } else if (rnd.nextInt(num_iterations / 2) == 0) {
            dbm.clear().orDie();
          } else if (rnd.nextInt(num_iterations / 2) == 0) {
            dbm.synchronize(false).orDie();
          } else if (rnd.nextInt(10) == 0) {
            Iterator iter = dbm.makeIterator();
            if (is_ordered && rnd.nextInt(3) == 0) {
              if (rnd.nextInt(3) == 0) {
                iter.jump(key);
              } else {
                iter.last();
              }
              while (rnd.nextInt(10) != 0) {
                Status status = new Status();
                iter.getString(status);
                if (!status.equals(Status.SUCCESS)) {
                  if (!status.equals(Status.NOT_FOUND_ERROR)) {
                    status.orDie();
                  }
                  break;
                }
                iter.previous();
              }
            } else {
              if (rnd.nextInt(3) == 0) {
                iter.jump(key);
              } else {
                iter.first();
              }
              while (rnd.nextInt(10) != 0) {
                Status status = new Status();
                iter.getString(status);
                if (!status.equals(Status.SUCCESS)) {
                  if (!status.equals(Status.NOT_FOUND_ERROR)) {
                    status.orDie();
                  }
                  break;
                }
                iter.next();
              }
            }
            iter.destruct();
          } else if (rnd.nextInt(3) == 0) {
            Status status = new Status();
            dbm.get(key, status);
            if (!status.equals(Status.NOT_FOUND_ERROR)) {
              status.orDie();
            }
          } else if (rnd.nextInt(3) == 0) {
            Status status = dbm.remove(key);
            if (!status.equals(Status.NOT_FOUND_ERROR)) {
              status.orDie();
            }
          } else if (rnd.nextInt(3) == 0) {
            Status status = dbm.set(key, value, false);
            if (!status.equals(Status.DUPLICATION_ERROR)) {
              status.orDie();
            }
          } else {
            dbm.set(key, value).orDie();
          }
          int seq = i + 1;
          if (thid_ == 0 && seq % (num_iterations / 500) == 0) {
            STDOUT.printf(".");
            if (seq % (num_iterations / 10) == 0) {
              STDOUT.printf(" (%08d)\n", seq);
            }
            STDOUT.flush();
          }
        }
      }
      private int thid_;
    }
    STDOUT.printf("Doing:\n");
    double start_time = getTime();
    Task[] tasks = new Task[num_threads];
    for (int thid = 0; thid < num_threads; thid++) {
      tasks[thid] = new Task(thid);
      tasks[thid].start();
    }
    for (int thid = 0; thid < num_threads; thid++) {
      try {
        tasks[thid].join();
      } catch (java.lang.InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    dbm.synchronize(false).orDie();
    double end_time = getTime();
    double elapsed = end_time - start_time;
    System.gc();
    STDOUT.printf("Done: num_records=%d file_size=%d time=%.3f qps=%.0f\n",
                  dbm.count(), dbm.getFileSize(), elapsed,
                  num_iterations * num_threads / elapsed);
    STDOUT.print("\n");
    dbm.disconnect().orDie();
    dbm.destruct();
    return 0;
  }

  /** The standard output stream. */
  private static final PrintStream STDOUT = System.out;
  /** The standard error stream. */
  private static final PrintStream STDERR = System.err;
  /** The rondom generator. */
  private static final Random RND = new Random();
}

// END OF FILE
