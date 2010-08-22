package org.queeg.ringmaster.model;

import java.io.Reader;

public interface ExecutionLog {
  /**
   * The current state of this execution
   * 
   * @return the current state of this run
   */
  ExecutionState getState();

  /**
   * Get the log output from this execution. This reader that will deliver all
   * the log output and for a running task continue to provide the live output.
   * 
   * @return a reader to the log out of the execution
   */
  Reader getOutput();
}
