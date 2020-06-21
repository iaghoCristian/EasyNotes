package com.example.easynotes

import Note
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.easynotes.model.Frequency
import com.example.easynotes.repository.NoteRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class NoteActivity : AppCompatActivity() {

    var format = SimpleDateFormat("dd/MM/yyyy HH:mm")

    lateinit var date: EditText
    lateinit var title: AppCompatEditText
    lateinit var frequency: Spinner
    lateinit var resume : EditText
    lateinit var description: EditText
    var editNote: Boolean = false
    lateinit var note: Note
    lateinit var noteRepository: NoteRepository
    lateinit var dayFormatted : String
    lateinit var hourFormatted : String

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        var id = intent.extras?.getSerializable("notes") as Long?
        this.noteRepository = intent.extras?.getSerializable("repository") as NoteRepository



        date = findViewById(R.id.date)
        title = findViewById(R.id.titlenote)
        frequency = findViewById(R.id.frequency)
        resume = findViewById(R.id.resume)
        description = findViewById(R.id.description)


        ArrayAdapter.createFromResource(
            this,
            R.array.frequency_option,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            frequency.adapter = adapter

        }

        if(id!=null) {
            editNote = true
            this.note = noteRepository.getById(id) as Note


            date.setText(format.format(note.alert))
            title.setText(note.title)


            resume.setText(note.resume)
            description.setText(note.description)

            date.isEnabled = false
            title.isEnabled = false
            frequency.isEnabled = false
            resume.isEnabled = false
            description.isEnabled = false

        }

        date.setOnFocusChangeListener(View.OnFocusChangeListener{view,hasFocus->
            if(hasFocus){
                onFocusOnDate(view)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(editNote) {
            menuInflater.inflate(R.menu.menu_note, menu)
        }
        return true
    }

    fun OnClickCancel(view: View){
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.getItemId()){
            R.id.delete ->{
                MaterialAlertDialogBuilder(this)
                    .setTitle(resources.getString(R.string.deleteConfirm))
                    .setNeutralButton(resources.getString(R.string.cancel)){ dialog, which ->

                    }
                    .setPositiveButton(resources.getString(R.string.yes)){ dialog, which ->
                        if(editNote){
                            this.noteRepository.deleteNote(this.note)
                            finish()
                        }
                    }
                    .show()
                true
            }
            R.id.edit->{
                date.isEnabled = true
                title.isEnabled = true
                frequency.isEnabled = true
                resume.isEnabled = true
                description.isEnabled = true
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onClickSave(view : View){
        var format : SimpleDateFormat= SimpleDateFormat("dd/MM/yyyy HH:mm")
        if(this.editNote){

            lateinit var date : Date
            note.title = title.text.toString()
            var frequency = frequency.selectedItem.toString()
            note.resume = resume.text.toString()
            note.description = description.text.toString()

            val list = resources.getStringArray(R.array.frequency_option) as kotlin.Array<String>

            when(frequency){
                list.get(0)->{
                    note.frequency = Frequency.UNIQUE
                    true
                }
                list.get(1)->{
                    note.frequency = Frequency.HOUR
                    true
                }
                list.get(2)->{
                    note.frequency = Frequency.DIARY
                    true
                }
                list.get(3)->{
                    note.frequency = Frequency.WEEK
                    true
                }
                list.get(4)->{
                    note.frequency = Frequency.MONTH
                    true
                }
                else->{
                    note.frequency = Frequency.UNIQUE
                }

            }


            try {
                date = format.parse(this.date.text.toString())
            }
            catch (ex: ParseException){
                date = Date()
            }

            note.alert = date
            this.noteRepository.noteUpdate(note)

        }
        else{
            var ParamFrequency: Frequency? = null
            var frequency = frequency.selectedItem.toString()

            val list = resources.getStringArray(R.array.frequency_option) as kotlin.Array<String>

            when(frequency){
                list.get(0)->{
                    ParamFrequency = Frequency.UNIQUE
                    true
                }
                list.get(1)->{
                    ParamFrequency = Frequency.HOUR
                    true
                }
                list.get(2)->{
                    ParamFrequency = Frequency.DIARY
                    true
                }
                list.get(3)->{
                    ParamFrequency = Frequency.WEEK
                    true
                }
                list.get(4)->{
                    ParamFrequency = Frequency.MONTH
                    true
                }
                else->{
                    ParamFrequency = Frequency.UNIQUE
                }

            }

            lateinit var date: Date
            try {
                date = format.parse(this.date.text.toString())
            }
            catch (ex: ParseException){
                date = Date()
            }

            note = Note(
                0L,
                title.text.toString(),
                resume.text.toString(),
                description.text.toString(),
                Date(),
                ParamFrequency,
                date
            )
            this.noteRepository.addNote(note)
        }
        finish()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onFocusOnDate(view: View){
        lateinit var date: Date
        if(editNote){
            date = this.note.alert
        }
        else{
            date = Date()
        }

        var calendar = Calendar.getInstance()
        calendar.time = date


        val datePickerDialog = DatePickerDialog(
            this@NoteActivity,
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                dayFormatted = (day.toString() + "/" + (month + 1) + "/" + year)

                val timePickerDialog = TimePickerDialog(
                    this@NoteActivity,
                    TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                        hourFormatted = (hour.toString() + ":" + minute.toString())
                        this.date.setText(dayFormatted + " " + hourFormatted)
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}