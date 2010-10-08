package org.queeg.ringmaster.model.impl;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.queeg.ringmaster.model.ExecutionLog;
import org.queeg.ringmaster.model.Resource;
import org.queeg.ringmaster.model.Task;

import com.google.common.collect.Maps;

public class IntervalNumberGeneratingTask implements Task {
  private long interval = 1;
  private TimeUnit intervalUnits = TimeUnit.MINUTES;
  private long intervalMs = intervalUnits.toMillis(interval);

  private long lastExecution = -1;

  private AtomicLong lastValue = new AtomicLong();

  public void setInterval(long interval) {
    this.interval =interval;
    intervalMs = intervalUnits.toMillis(interval);
  }
  
  public void setIntervalUnits(TimeUnit intervalUnits) {
    this.intervalUnits = intervalUnits;
    intervalMs = intervalUnits.toMillis(interval);
  }
  
  @Override
  public ExecutionLog execute() throws IllegalStateException {
    long now = System.currentTimeMillis();

    if (lastExecution == -1 || (now - lastExecution) >= intervalMs) {

    }

    return null;
  }

  @Override
  public Map<String, Resource> getConfiguredInputs() {
    return Maps.newHashMap();
  }

  @Override
  public String getDescription() {
    return "A task that outputs a resource at regular intervals";
  }

  @Override
  public ExecutionLog getLatestExecutionLog() {
    return null;
  }

  @Override
  public String getName() {
    return getClass().getName();
  }

  @Override
  public Collection<Resource> getOutputs() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, Class<? extends Resource>> getRequiredInputs() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isReadyToExecute() {
    return lastExecution == -1 || (System.currentTimeMillis() - lastExecution) >= intervalMs;
  }

  @Override
  public boolean isRunning() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Resource setInput(String name, Resource input) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }
}
