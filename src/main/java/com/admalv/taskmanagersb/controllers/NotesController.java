package com.admalv.taskmanagersb.controllers;

import com.admalv.taskmanagersb.DTOs.CreateNotesDTO;
import com.admalv.taskmanagersb.entities.NotesEntity;
import com.admalv.taskmanagersb.services.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    private NotesService notesService;

    public NotesController(NotesService notesService){
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<NotesEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes = notesService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<NotesEntity> addNotes(@PathVariable("taskId") Integer taskId, @RequestBody CreateNotesDTO body){
        var notes = notesService.addNotesForTask(taskId, body.getTitle(), body.getBody());

        return ResponseEntity.ok(notes);


    }

}
