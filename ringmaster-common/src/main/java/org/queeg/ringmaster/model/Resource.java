package org.queeg.ringmaster.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public interface Resource {
  /**
   * The name of this resource. For example "Country Codes".
   * 
   * @return the name of this resource
   */
  String getName();

  /**
   * The human readable description of this resource.
   * 
   * @return the human readable description of this resource
   */
  String getDescription();

  /**
   * Get the time this resource was last updated
   * 
   * @return The time this resource was last updated
   * @throws FileNotFoundException
   *           If the resource does not exist
   * @throws IOException 
   */
  Date getLastChanged() throws IOException;

  /**
   * Get a reference back to the task that generates this resource
   * 
   * @return the task that produces this resource
   */
  Task getTask();
}
