package org.queeg.ringmaster;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.queeg.ringmaster.model.Task;

public class Ringmaster extends Thread {
  private final Logger log = Logger.getLogger(getClass());

  private boolean stopping = false;

  private List<Task> workers;

  private int maxWorkers = 3;

  private List<WorkerThread> pool;

  public Ringmaster() {
    super("Ringmaster Executor");

    pool = new LinkedList<WorkerThread>();
  }

  @Override
  public void run() {
    int index = 0;
    while (!stopping) {
      synchronized (workers) {
        if (index < workers.size()) {
          Task task = workers.get(index);
          if (task.isReadyToExecute()) {
            log.info("Task " + task.getName() + " is ready to execute");
            executeTask(task);
          }
        }
      }
    }
  }

  private boolean executeTask(Task task) {
    synchronized (pool) {
      if (pool.size() >= maxWorkers) {
        return false;
      }
      WorkerThread worker = new WorkerThread(task);
      worker.start();
      pool.add(worker);
      return true;
    }
  }
}
