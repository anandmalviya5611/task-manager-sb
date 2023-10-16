package com.admalv.taskmanagersb.controllers;

import com.admalv.taskmanagersb.DTOs.CreateTaskDTO;
import com.admalv.taskmanagersb.DTOs.ErrorResponseDTO;
import com.admalv.taskmanagersb.DTOs.TaskResponseDTO;
import com.admalv.taskmanagersb.DTOs.UpdateTaskDTO;
import com.admalv.taskmanagersb.entities.TaskEntity;
import com.admalv.taskmanagersb.services.NotesService;
import com.admalv.taskmanagersb.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final NotesService notesService;

    private ModelMapper modelMapper = new ModelMapper();

    public TaskController(TaskService taskService, NotesService notesService) {
        this.taskService = taskService;
        this.notesService = notesService;
    }
    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){

        var tasks = taskService.getTasks();

        return ResponseEntity.ok(tasks);

    };

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO>getTaskByID(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        var notes = notesService.getNotesForTask(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }

        var taskResponse = modelMapper.map(task, TaskResponseDTO.class);

        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);

    };

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);

    };

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable("id") String id, @RequestBody UpdateTaskDTO body) throws ParseException {
            int taskId = Integer.parseInt(id);
            var task = taskService.updateTask(taskId, body.getDescription(), body.getDeadline(), body.getCompleted());

            if (task == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(task);

    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Integer id){

        taskService.deleteTaskById(id);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleError(Exception e) {
        if (e instanceof ParseException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }


}
