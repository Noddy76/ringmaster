package org.queeg.ringmaster.model;

import java.io.FileNotFoundException;

import org.apache.hadoop.fs.Path;

public interface SingleResource extends Resource {
  /**
   * Check if this resource is available
   * 
   * @return
   */
  boolean isAvailable();

  /**
   * Get the {@link Path} to the resource
   * 
   * @return the {@link Path} to the resource
   * @throws FileNotFoundException
   *           If the resource is not available
   */
  Path getPath() throws FileNotFoundException;
}
