package com.umbertoemonds.dharmonie.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umbertoemonds.dharmonie.R
import com.umbertoemonds.dharmonie.data.models.Grille
import com.umbertoemonds.dharmonie.utils.injection.Mapping

class GrilleAdapter(private val grilles: List<Grille>) : RecyclerView.Adapter<GrilleAdapter.GrilleViewHolder>(){

    class GrilleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val grilleTitle : TextView = view.findViewById(R.id.grilleTitle)
        val grilleDescription : TextView = view.findViewById(R.id.grilleDescription)
        val tagRv : RecyclerView = view.findViewById(R.id.tagRv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrilleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grille_line, parent, false)

        return GrilleViewHolder(view)
    }

    override fun onBindViewHolder(holder: GrilleViewHolder, position: Int) {
        val grille = grilles[position]
        val tags = mutableListOf<String>()
        val mapping = Mapping(holder.itemView.context)

        val grilleAdapter = GrilleTagAdapter(tags)
        holder.tagRv.adapter = grilleAdapter
        holder.tagRv.layoutManager = LinearLayoutManager(holder.itemView.context,
                                                        LinearLayoutManager.HORIZONTAL,
                                            false)

        tags.add("${grille.tempo} BPM")

        mapping.notes[grille.noteId]?.let {
            tags.add("$it ${mapping.modes[grille.modeId]}")
        }

        holder.grilleTitle.text = grille.nom
        holder.grilleDescription.text = grille.description
    }

    override fun getItemCount(): Int {
        return grilles.size
    }

}