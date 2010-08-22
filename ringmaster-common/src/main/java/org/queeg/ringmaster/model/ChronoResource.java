package org.queeg.ringmaster.model;

import java.io.FileNotFoundException;
import java.util.Collection;

import org.apache.hadoop.fs.Path;

public interface ChronoResource extends Resource {
  /**
   * Get a list of the instance names that are available after the one given.
   * 
   * @param instanceName
   *          the instance name before the earliest one required.
   * @return a list of instance names
   */
  Collection<String> findInstancesSince(String instanceName);

  /**
   * Discover the latest instance name.
   * 
   * @return the latest instance name
   */
  String getLatestInstanceName();

  /**
   * Return the {@link Path} to a named instance
   * 
   * @param instanceName
   *          the instance
   * @return the {@link Path} to the instance
   * @throws FileNotFoundException
   *           If the instanceName is not valid/available
   */
  Path getInstancePath(String instanceName) throws FileNotFoundException;
}
