package com.admalv.taskmanagersb.DTOs;

import com.admalv.taskmanagersb.entities.NotesEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class TaskResponseDTO {
    private int Id;
    private String name;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<NotesEntity> notes;
}
