package com.admalv.taskmanagersb.entities;

import lombok.Data;

import java.util.Date;

@Data
public class TaskEntity {

    private int Id;
    private String name;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;

}
