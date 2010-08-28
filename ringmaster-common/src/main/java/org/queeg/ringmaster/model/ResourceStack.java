package org.queeg.ringmaster.model;

import java.util.Collection;

public interface ResourceStack extends Resource {
  /**
   * Get a list of the instance names that are available after the one given.
   * 
   * @param instanceName
   *          the instance name before the earliest one required.
   * @return a list of {@link Resource} objects
   */
  Collection<Resource> findInstancesSince(String instanceName);

  /**
   * Discover the latest instance.
   * 
   * @return the latest instance
   */
  Resource getLatestInstance();
}
