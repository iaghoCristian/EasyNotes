package com.example.easynotes.tasks

import android.os.AsyncTask
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatEditText
import com.example.easynotes.model.Frequency
import com.example.easynotes.model.Note
import com.example.easynotes.repository.NoteRepository
import java.text.SimpleDateFormat

class FillEditScreenTask (
    repository: NoteRepository,
    title: AppCompatEditText,
    date: EditText,
    frequency: Spinner,
    description: EditText,
    resume: EditText,
    id:Int
) : AsyncTask<Void,Void,Note>(){

    var repository: NoteRepository
    var title: AppCompatEditText
    var date: EditText
    var frequency: Spinner
    var description: EditText
    var resume: EditText
    var id:Int

    init{
        this.repository = repository
        this.title = title
        this.date = date
        this.frequency = frequency
        this.description = description
        this.resume = resume
        this.id = id
    }

    override fun doInBackground(vararg params: Void?): Note {
        return repository.getByIndex(id) as Note
    }

    override fun onPostExecute(result: Note) {
        super.onPostExecute(result)

        title.setText(result.title)

        var format = SimpleDateFormat("dd/MM/yyy hh:mm")
        date.setText(format.format(result.alert))

        description.setText(result.description)
        resume.setText(result.resume)

        when(result.frequency){
            Frequency.UNIQUE->{
                frequency.setSelection(0)
                true
            }

            Frequency.HOUR->{
                frequency.setSelection(1)
                true
            }

            Frequency.DIARY->{
                frequency.setSelection(2)
                true
            }
            Frequency.WEEK->{
                frequency.setSelection(3)
                true
            }
            Frequency.MONTH->{
                frequency.setSelection(4)
                true
            }
        }
    }
}