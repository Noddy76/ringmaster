package org.queeg.ringmaster.model.impl;

import java.net.URI;

import org.queeg.ringmaster.model.Resource;
import org.queeg.ringmaster.model.Task;

public abstract class UriResource implements Resource {
  private final Task task;
  
  private String description;
  
  private String name;
  
  public UriResource(Task task) {
    this.task = task;
  }
  
  @Override
  public String getDescription() {
    if (getUri() == null) {
      if(description == null) {
        return getClass().getName();
      } else {
        return description;
      }
    } else {
      return getUri().toString();
    }
  }

  @Override
  public String getName() {
    if (getUri()== null) {
      if(name == null) {
        return getClass().getName();
      } else {
        return name;
      }
    } else {
      return getUri().getPath();
    }
  }

  @Override
  public Task getTask() {
    return task;
  }
  
  public abstract URI getUri();
}
