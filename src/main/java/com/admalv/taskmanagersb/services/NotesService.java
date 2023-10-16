package com.admalv.taskmanagersb.services;

import com.admalv.taskmanagersb.DTOs.TaskResponseDTO;
import com.admalv.taskmanagersb.entities.NotesEntity;
import com.admalv.taskmanagersb.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class NotesService {
    private TaskService taskService;
    private HashMap<Integer, TaskNotesHolder> taskNotesHolderHashMap = new HashMap<>();

    public NotesService(TaskService taskService){
        this.taskService = taskService;
    }

    //encapsulation of two related fields
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
            System.out.println("1. im trying to get the notes where task is null");
            return null;
        }
        System.out.println("1. im trying to get the notes where task is not null");
        //if the taskId is a new entry in the hashmap
        if(taskNotesHolderHashMap.get(taskId) == null){
            System.out.println("did i just enter here?");
            taskNotesHolderHashMap.put(taskId, new TaskNotesHolder());
        }
        System.out.println("2. going to return the notes" + taskNotesHolderHashMap.get(taskId).noteId);
        //finally return the notes for the taskId
        return taskNotesHolderHashMap.get(taskId).notes;
    }

    //service method used to add notes for the task
    public NotesEntity addNotesForTask(int taskId, String title, String body){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            System.out.println("no taskId like that!");
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
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;
        return note;

    }

    public void removeNotesById(int taskId, int noteId){
        var taskNotesHolder = taskNotesHolderHashMap.get(taskId);

        Iterator<NotesEntity> it = taskNotesHolder.notes.iterator();

        while(it.hasNext()){
            NotesEntity note = it.next();
            if(note.getId() == noteId){
                it.remove();
            }
        }

    }

    public NotesEntity getANoteById(int taskId, int noteId){
        var taskNotesHolder = taskNotesHolderHashMap.get(taskId);
        for(NotesEntity note: taskNotesHolder.notes){
            if(note.getId() == noteId){
                return note;
            }
        }
        return null;
    }

}
