package org.queeg.ringmaster;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.queeg.ringmaster.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class Ringmaster implements Runnable {
  private final Logger log = LoggerFactory.getLogger(getClass());

  private boolean stopping = false;

  private List<Task> tasks;

  private int maxWorkers = 3;

  private long loopDelay = 10000;

  private List<WorkerThread> pool;

  public Ringmaster() {
    pool = Collections.synchronizedList(new LinkedList<WorkerThread>());
    tasks = Lists.newArrayList();
  }

  @Override
  public void run() {
    log.info("Ringmaster control thread starting");
    int index = 0;
    while (!stopping) {
      synchronized (tasks) {
        if (index < tasks.size()) {
          Task task = tasks.get(index);
          if (task.isReadyToExecute()) {
            log.info("Task {} is ready to execute", task.getName());
            executeTask(task);
          }
          index++;
        } else {
          try {
            Thread.sleep(loopDelay);
          } catch (InterruptedException e) {
            log.error("Interrupted whilst waiting to scan the tasks again");
          }
          index = 0;
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
  
  public void addTask(Task task) {
    tasks.add(task);
  }

  public int getMaxWorkers() {
    return maxWorkers;
  }

  public void setMaxWorkers(int maxWorkers) {
    this.maxWorkers = maxWorkers;
  }

  public long getLoopDelay() {
    return loopDelay;
  }

  public void setLoopDelay(long loopDelay) {
    this.loopDelay = loopDelay;
  }

  public boolean isStopping() {
    return stopping;
  }
  
  @Override
  public String toString() {
    return String.format("Ringmaster, %d tasks, %d workers", tasks.size(), pool.size());
  }
}
