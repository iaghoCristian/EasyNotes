package com.example.easynotes.adapter

import Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easynotes.R
import com.example.easynotes.repository.NoteRepository
import java.text.SimpleDateFormat

class CardAdapter(noteRepository: NoteRepository, onNoteListener: CardViewHolder.OnNoteListener): RecyclerView.Adapter<CardAdapter.CardViewHolder>(){

    var onNoteListener : CardViewHolder.OnNoteListener
    var noteRepository : NoteRepository

    init{
        this.noteRepository = noteRepository
        this.onNoteListener = onNoteListener
    }

    class CardViewHolder(view: View, onNoteListener: OnNoteListener) : RecyclerView.ViewHolder(view),View.OnClickListener{


        var title : TextView
        var alertDate : TextView
        var resume: TextView
        var id: Long
        var onNoteListener: OnNoteListener

        init {
            title = view.findViewById(R.id.title);
            alertDate = view.findViewById(R.id.alertDate)
            resume = view.findViewById(R.id.resume)
            id = 0
            this.onNoteListener = onNoteListener
            view.setOnClickListener(this)
        }

        interface OnNoteListener{
            fun onNoteClick(position:Int)
        }

        override fun onClick(v: View?) {
            onNoteListener.onNoteClick(adapterPosition)
        }

    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout,parent,false) as LinearLayout
        var viewHolder = CardViewHolder(view, this.onNoteListener)

        return viewHolder
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        var note : Note = noteRepository.getByIndex(position) as Note

        holder.id = note.id
        holder.title.setText(note.title)
        var format: SimpleDateFormat = SimpleDateFormat("dd/MM/yyy HH:mm")
        holder.alertDate.setText(format.format(note.alert))
        holder.resume.setText(note.resume)
    }

    override fun getItemCount(): Int {
        return this.noteRepository.returnSize();
    }


}