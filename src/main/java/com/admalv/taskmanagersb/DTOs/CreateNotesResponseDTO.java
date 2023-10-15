package com.admalv.taskmanagersb.DTOs;

import com.admalv.taskmanagersb.entities.NotesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotesResponseDTO {
    public Integer taskId;
    public NotesEntity notes;
}
