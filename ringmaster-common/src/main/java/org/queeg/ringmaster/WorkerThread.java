package org.queeg.ringmaster;

import java.util.concurrent.atomic.AtomicInteger;

import org.queeg.ringmaster.model.Task;

public class WorkerThread extends Thread {
  private static AtomicInteger index = new AtomicInteger();

  private final Task task;
  
  public WorkerThread(Task task) {
    super(task, "Worker-" + index.incrementAndGet());

    this.task = task;
  }
  
  @Override
  public void run() {
    task.execute();
  }
}
