package org.queeg.ringmaster;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.queeg.ringmaster.model.ExecutionLog;
import org.queeg.ringmaster.model.ExecutionState;
import org.queeg.ringmaster.model.Resource;
import org.queeg.ringmaster.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class RingMasterTest {
  private Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void testExecution() throws Exception {
    Ringmaster ringmaster = new Ringmaster();
    Task task = new Task() {
      private boolean running = false;

      @Override
      public void run() {
        log.info("Task.run()");
      }

      @Override
      public Resource setInput(String name, Resource input) {
        log.info("Task.setInput(name:{}, input:{})", name, input);
        return null;
      }

      @Override
      public boolean isRunning() {
        log.info("Task.isRunning()");
        return running;
      }

      @Override
      public boolean isReadyToExecute() {
        log.info("Task.isReadyToExecute()");
        return !running;
      }

      @Override
      public Map<String, Class<? extends Resource>> getRequiredInputs() {
        log.info("Task.getRequiredInputs()");

        Map<String, Class<? extends Resource>> result = new HashMap<String, Class<? extends Resource>>();
        return result;
      }

      @Override
      public Collection<Resource> getOutputs() {
        log.info("Task.getOutputs()");
        List<Resource> result = Lists.newArrayList();
        return result;
      }

      @Override
      public String getName() {
        log.info("Task.getName()");
        return "UselessTask";
      }

      @Override
      public ExecutionLog getLatestExecutionLog() {
        log.info("Task.getLatestExecutionLog()");
        ExecutionLog log = new ExecutionLog() {

          @Override
          public ExecutionState getState() {
            return ExecutionState.SUCCESS;
          }

          @Override
          public Reader getOutput() {
            Reader result = new StringReader("Log output");
            return result;
          }
        };
        return log;
      }

      @Override
      public String getDescription() {
        log.info("Task.getLatestExecutionLog()");
        return "Task description";
      }

      @Override
      public Map<String, Resource> getConfiguredInputs() {
        log.info("Task.getConfiguredInputs()");
        Map<String, Resource> result = Maps.newHashMap();
        return result;
      }

      @Override
      public ExecutionLog execute() throws IllegalStateException {
        log.info("Task.execute()");
        running = true;
        try {
          Thread.sleep(15000);
        } catch (InterruptedException e) {
          log.error("Got interrupted pretending to work", e);
        } finally {
          running = false;
        }
        log.info("Finished executing");
        return getLatestExecutionLog();
      }
    };
    
    ringmaster.addTask(task);
    
    Thread ringmasterThread = new Thread(ringmaster, "Ringmaster");
    ringmasterThread.start();
    //ringmasterThread.join();
  }
}
