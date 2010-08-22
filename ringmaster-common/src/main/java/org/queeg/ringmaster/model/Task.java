package org.queeg.ringmaster.model;

import java.util.Collection;
import java.util.Map;

public interface Task {
  /**
   * 
   * @return the name of this task
   */
  String getName();

  /**
   * 
   * @return the human readable description of this task
   */
  String getDescription();

  /**
   * List the required {@link Resource}s that this task requires against the
   * class or resource types that they must provide. The type would typlically
   * be either {@link SingleResource} or {@link ChronoResource}.
   * 
   * @return a {@link Map} of {@link Resource} names to required classes
   */
  Map<String, Class<? extends Resource>> getRequiredInputs();

  /**
   * Get the {@link Resource}s that have already been configured as inputs
   * 
   * @return the configured inputs
   */
  Map<String, Resource> getConfiguredInputs();

  /**
   * Assign a resource as a named input
   * 
   * @param name
   *          the input to assign
   * @param input
   *          the resource to attach
   * @return the previously assigned resource or null if first assignment
   */
  Resource setInput(String name, Resource input);

  /**
   * Gets the output or outputs produced by this task
   * 
   * @return a collection of {@link Resource}s
   */
  Collection<Resource> getOutputs();

  /**
   * Does this task have all the prerequisites to execute?
   * 
   * @return true if this task is ready to execute
   */
  boolean isReadyToExecute();

  /**
   * Is this task currently running
   * 
   * @return true if this task is currently running
   */
  boolean isRunning();

  /**
   * Get the last or current execution log
   * 
   * @return the current or last {@link ExecutionLog} or null if this task has
   *         never been executed
   */
  ExecutionLog getLastExecutionLog();

  /**
   * Execute this task
   * 
   * @return the {@link ExecutionLog} so that the progress can be tracked
   * @throws IllegalStateException
   *           If this task is still running or it is not ready to execute
   */
  ExecutionLog execute() throws IllegalStateException;
}
