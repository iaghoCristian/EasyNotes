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

    var recyclerView : RecyclerView? = null
    var notes : NoteRepository

    init{
        this.notes = NoteRepository()
    }

    fun callRecycler(){
        var layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        var adapter  = CardAdapter(notes,this)

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView?
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter= adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callRecycler()

    }

    override fun onResume(){
        super.onResume()
        callRecycler()
    }

    override fun onNoteClick(position: Int) {
        var bundle = Bundle()
        bundle.putSerializable("notes",notes.getByIndex(position)?.id)
        bundle.putSerializable("repository", notes)

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
                bundle.putSerializable("repository", notes)

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