package org.queeg.ringmaster;

import org.queeg.ringmaster.model.Task;

public class WorkerThread extends Thread {
  private static int index = 0;

  public WorkerThread(Task task) {
    super(task, "Worker-" + getNextNumber());
    // TODO Auto-generated constructor stub
  }

  private static synchronized int getNextNumber() {
    return ++index;
  }

}
