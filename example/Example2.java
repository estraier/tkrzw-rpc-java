/*************************************************************************************************
 * Example for serious use cases of the remote database
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

import tkrzw_rpc.*;

public class Example2 {
  public static void main(String[] args) {
    RemoteDBM dbm = new RemoteDBM();
    try {
      // Prepares the database.  The timeout is in seconds.
      Status status = dbm.connect("localhost:1978", 10);
      // Checks the status explicitly.
      if (!status.isOK()) {
        throw new StatusException(status);
      }
    
      // Sets the index of the database to operate.
      // The default value 0 means the first database on the server.
      // 1 means the second one and 2 means the third one, if any.
      dbm.setDBMIndex(0).orDie();
          
      // Sets records.
      // Throws an exception on failure.
      dbm.set("first", "hop").orDie();
      dbm.set("second", "step").orDie();
      dbm.set("third", "jump").orDie();

      // Retrieves record values.
      String[] keys = {"first", "second", "third", "fourth"};
      for (String key : keys) {
        // Gives a status object to check.
        String value = dbm.get(key, status);
        if (status.isOK()) {
          System.out.println(value);
        } else {
          System.err.println(status);
          if (!status.equals(Status.NOT_FOUND_ERROR)) {
            throw new StatusException(status);
          }
        }
      }

      // Traverses records.
      Iterator iter = dbm.makeIterator();
      try {
        iter.first();
        while (true) {
          String[] record = iter.getString(status);
          if (!status.isOK()) {
            if (!status.equals(Status.NOT_FOUND_ERROR)) {
              throw new StatusException(status);
            }
            break;
          }
          System.out.println(record[0] + ": " + record[1]);
          iter.next();
        }
      } finally {
        // Release the resources.
        iter.destruct();
      }

      // Closes the connection.
      dbm.disconnect().orDie();
    } finally {
      // Release the resources.
      dbm.destruct();
    }
  }
}

// END OF FILE
