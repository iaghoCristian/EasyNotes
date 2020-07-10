package com.example.easynotes.converter

import androidx.room.TypeConverter
import com.example.easynotes.model.Frequency
import java.util.*

class Converter {
    @TypeConverter
    fun fromTimestamp(value:Long?) : Date?{
        return value?.let{Date(it)}
    }

    @TypeConverter
    fun dateToTimestamp(date:Date?): Long?{
        return date?.time?.toLong()
    }

    @TypeConverter
    fun frequencyToString(frequency: Frequency?): String?{
        return frequency?.name
    }

    @TypeConverter
    fun stringToFrequency(frequency: String): Frequency?{
        return Frequency.valueOf(frequency)
    }
}