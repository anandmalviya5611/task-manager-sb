package com.admalv.taskmanagersb.controllers;

import com.admalv.taskmanagersb.DTOs.CreateNotesDTO;
import com.admalv.taskmanagersb.DTOs.CreateNotesResponseDTO;
import com.admalv.taskmanagersb.DTOs.TaskResponseDTO;
import com.admalv.taskmanagersb.entities.NotesEntity;
import com.admalv.taskmanagersb.services.NotesService;
import com.admalv.taskmanagersb.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    private NotesService notesService;
    private TaskService taskService;

    private ModelMapper modelMapper = new ModelMapper();

    public NotesController(NotesService notesService, TaskService taskService){

        this.notesService = notesService;
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<NotesEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes = notesService.getNotesForTask(taskId);
        System.out.println("3. i have finally reached this part!");
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNotesResponseDTO> addNotes(@PathVariable("taskId") Integer taskId, @RequestBody CreateNotesDTO body){
        var note = notesService.addNotesForTask(taskId, body.getTitle(), body.getBody());

        return ResponseEntity.ok(new CreateNotesResponseDTO(taskId, note));

    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<TaskResponseDTO> deleteNotes(@PathVariable("taskId") Integer taskId, @PathVariable("noteId") Integer noteId){
        notesService.removeNotesById(taskId, noteId);
        var task = taskService.getTaskById(taskId);
        var notes = notesService.getNotesForTask(taskId);

        var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<CreateNotesDTO> getANote(@PathVariable("taskId") Integer taskId, @PathVariable("noteId") Integer noteId){
        var noteResponse = notesService.getANoteById(taskId, noteId);
        var note = modelMapper.map(noteResponse, CreateNotesDTO.class);
        return ResponseEntity.ok(note);
    }


}



