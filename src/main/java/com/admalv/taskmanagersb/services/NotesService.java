package com.admalv.taskmanagersb.services;

import com.admalv.taskmanagersb.entities.NotesEntity;
import com.admalv.taskmanagersb.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NotesService {
    private TaskService taskService;
    private HashMap<Integer, TaskNotesHolder> taskNotesHolderHashMap = new HashMap<>();

    public NotesService(TaskService taskService){
        this.taskService = taskService;
    }

    class TaskNotesHolder{
        protected int noteId = 1;
        protected ArrayList<NotesEntity> notes = new ArrayList<>();

    }

    //service method used to get the notes for the tasks
    public List<NotesEntity> getNotesForTask(int taskId){
        //finding whether the tasks exist or not
        TaskEntity task = taskService.getTaskById(taskId);
        //if the task does not exist return null
        if(task == null){
            return null;
        }
        //if the taskId is a new entry in the hashmap
        if(taskNotesHolderHashMap.get(taskId) == null){
            taskNotesHolderHashMap.put(taskId, new TaskNotesHolder());
        }
        //finally return the notes for the taskId
        return taskNotesHolderHashMap.get(taskId).notes;
    }

    //service method used to add notes for the task
    public NotesEntity addNotesForTask(int taskId, String title, String body){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }
        if(taskNotesHolderHashMap.get(taskId) == null){
            taskNotesHolderHashMap.put(taskId, new TaskNotesHolder());
        }
        /*
        * one thing to note here is that the taskNotesHolderHashmap returns the value for a specific taskId which is a TaskNotesHolder object containing noteId, notes.
        * */
        TaskNotesHolder taskNotesHolder = taskNotesHolderHashMap.get(taskId);
        NotesEntity note = new NotesEntity();
        note.setId(taskNotesHolder.noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.noteId++;
        return note;

    }








}
