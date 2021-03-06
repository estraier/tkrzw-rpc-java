/*************************************************************************************************
 * Status exception
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

/**
 * Exception to convey the status of operations.
 */
public class StatusException extends RuntimeException {
  /**
   * Constructor.
   * @param status The status object to convey.
   */
  public StatusException(Status status) {
    super(status.toString());
    status_ = status;
  }

  /**
   * Get the status object.
   * @return The status object.
   */
  public Status getStatus() {
    return status_;
  }

  /** The status object. */
  private Status status_;
}

// END OF FILE
