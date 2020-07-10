package com.example.easynotes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easynotes.adapter.CardAdapter
import com.example.easynotes.repository.NoteRepository

class MainActivity : AppCompatActivity(), CardAdapter.CardViewHolder.OnNoteListener {

    lateinit var recyclerView : RecyclerView
    lateinit var notes : NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notes = NoteRepository(this)
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView

    }

    override fun onResume(){
        super.onResume()
        var layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        var adapter  = CardAdapter(notes,this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }

    override fun onNoteClick(position: Int) {
        var bundle = Bundle()
        bundle.putSerializable("notes", position)

        var it = Intent(this, NoteActivity::class.java).apply{
            putExtras(bundle)
        }
        startActivity(it)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.getItemId()){
            R.id.add->{
                var bundle = Bundle()

                var it = Intent(this, NoteActivity::class.java).apply{
                    putExtras(bundle)
                }
                startActivity(it)
                true
            }
            R.id.sair->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}