package org.queeg.ringmaster.model.impl;

import java.io.IOException;
import java.net.URI;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.queeg.ringmaster.model.Task;

public class PathResource extends UriResource {
  private Configuration conf;
  
  private final Path resource;
  
  public PathResource(Task task, Path resource) {
    super(task);
    this.resource = resource;
    this.conf = new Configuration();
  }
  
  @Override
  public Date getLastChanged() throws IOException {
    FileSystem fs = resource.getFileSystem(conf);
    FileStatus status = fs.getFileStatus(resource);
    long modificationTime = status.getModificationTime();
    
    return new Date(modificationTime);
  }

  @Override
  public URI getUri() {
    return resource.toUri();
  }
  
  public void setConfiguration(Configuration conf) {
    this.conf = conf;
  }
}
