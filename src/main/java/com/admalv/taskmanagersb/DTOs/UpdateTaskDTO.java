package com.admalv.taskmanagersb.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDTO {
    String description;
    String deadline;
    Boolean completed;


}
