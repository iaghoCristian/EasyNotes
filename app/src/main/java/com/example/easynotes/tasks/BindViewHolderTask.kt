package com.example.easynotes.tasks

import com.example.easynotes.model.Note
import android.os.AsyncTask
import com.example.easynotes.adapter.CardAdapter
import com.example.easynotes.repository.NoteRepository
import java.text.SimpleDateFormat

class BindViewHolderTask (repository: NoteRepository, holder: CardAdapter.CardViewHolder, position: Int): AsyncTask<Void,Void,Note>(){
    var repository: NoteRepository
    var holder: CardAdapter.CardViewHolder
    var position: Int

    init{
        this.repository = repository
        this.holder = holder
        this.position = position
    }

    override fun doInBackground(vararg params: Void?): Note {
        return repository.getByIndex(position) as Note
    }

    override fun onPostExecute(result: Note) {
        super.onPostExecute(result)

        holder.id = result.id
        holder.title.setText(result.title)
        var format = SimpleDateFormat("dd/MM/yyyy HH:mm")
        holder.alertDate.setText(format.format(result.alert))
        holder.resume.setText(result.resume)
    }
}