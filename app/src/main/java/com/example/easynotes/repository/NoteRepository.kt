package com.example.easynotes.repository

import Note
import com.example.easynotes.model.Frequency
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

public class NoteRepository : Serializable {

    companion object {
        var notes: ArrayList<Note> = ArrayList()
        var index: Long = 1
    }

    constructor(){
        notes.add(Note(1, "Example", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. ",
        Date(),
        Frequency.UNIQUE,
        Date()
        ))

    }

    fun addNote(note: Note){
        note.id = index
        index+=1
        notes.add(note)
    }

    fun deleteNote(note: Note){
        notes.remove(note)
    }

    fun listAll(): ArrayList<Note>{
        return notes
    }

    fun getById(noteID: Long):Note?{
        for (item : Note in notes){
            if (noteID == item.id){
                return item
            }
        }
        return null
    }

    fun getByIndex(index: Int): Note?{
        if(notes.size <= index || index < 0){
            return null;
        }
        else return notes[index];
    }

    fun returnSize(): Int{
        return notes.size
    }

    fun noteUpdate(note:Note){
        for ((index,value) in notes.withIndex()){
            notes.set(index, note)
            return
        }
    }
}