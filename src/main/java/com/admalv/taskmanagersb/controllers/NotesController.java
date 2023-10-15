package com.admalv.taskmanagersb.controllers;

import com.admalv.taskmanagersb.DTOs.CreateNotesDTO;
import com.admalv.taskmanagersb.DTOs.CreateNotesResponseDTO;
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
        System.out.println("3. i have finally reached this part!");
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNotesResponseDTO> addNotes(@PathVariable("taskId") Integer taskId, @RequestBody CreateNotesDTO body){
        var note = notesService.addNotesForTask(taskId, body.getTitle(), body.getBody());

        return ResponseEntity.ok(new CreateNotesResponseDTO(taskId, note));

    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<List<NotesEntity>> deleteNotes(@PathVariable("taskId") Integer taskId, @PathVariable("noteId") Integer noteId){
        var noteList = notesService.removeNotesById(taskId, noteId);

        return ResponseEntity.ok(noteList);
    }






}



