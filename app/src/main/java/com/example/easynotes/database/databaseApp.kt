package com.example.easynotes.database

import com.example.easynotes.model.Note
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.easynotes.converter.Converter

@Database(entities = arrayOf(Note::class), version = 1)
@TypeConverters(Converter::class)
abstract class databaseApp :RoomDatabase(){
    abstract fun noteDao(): NoteDAO
}