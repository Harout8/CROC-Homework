package ru.croc.javaschool.tasktracker;


import java.io.Serializable;

/**
 * Задача
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String name;
    private String description;
    private Executor executor;
    private String status;


    public Task(int code, String name, String description, Executor executor, String status) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.executor = executor;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", executor=" + executor.toString() +
                ", status='" + status + '\'' +
                '}';
    }
}
