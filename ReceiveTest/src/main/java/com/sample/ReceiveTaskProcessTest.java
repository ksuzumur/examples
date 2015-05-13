package com.sample;

import org.jbpm.bpmn2.handler.ReceiveTaskHandler;
import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;

/**
 * This is a sample file to test a process.
 */
public class ReceiveTaskProcessTest extends JbpmJUnitBaseTestCase {

    @Test
    public void testProcess() {

        RuntimeManager manager = createRuntimeManager("BPMN2-ReceiveTask.bpmn2");
        RuntimeEngine engine = getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        ReceiveTaskHandler receiveTaskHandler = new ReceiveTaskHandler(ksession);
        ksession.getWorkItemManager().registerWorkItemHandler("Receive Task",
                receiveTaskHandler);
        ProcessInstance processInstance = ksession.startProcess("ReceiveTask");

        receiveTaskHandler.setKnowledgeRuntime(ksession);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        receiveTaskHandler.messageReceived("HelloMessage", "Hello john!");

        // check whether the process instance has completed successfully
        assertNodeTriggered(processInstance.getId(), "Hello");
        manager.disposeRuntimeEngine(engine);
        manager.close();
    }

}