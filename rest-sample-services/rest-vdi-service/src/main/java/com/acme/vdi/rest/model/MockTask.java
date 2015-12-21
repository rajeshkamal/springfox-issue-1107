package com.acme.vdi.rest.model;


public class MockTask {

    public static Task getOne(String id, String action) {
        Task task = new Task();
        task.setId("TASK-" + id);
        task.setName(action);
        task.setProgress(90);
        task.setStatus("Running");
        return task;
    }
}
