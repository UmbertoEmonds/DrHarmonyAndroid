package com.umbertoemonds.dharmonie.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.umbertoemonds.dharmonie.R

class GrilleTagAdapter(private val tags: List<String>) : RecyclerView.Adapter<GrilleTagAdapter.GrilleTagViewHolder>(){

    class GrilleTagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tagTitle : TextView = view.findViewById(R.id.tagTitle)
        val background : ConstraintLayout = view.findViewById(R.id.background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrilleTagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grille_tag_line, parent, false)

        return GrilleTagViewHolder(view)
    }

    override fun onBindViewHolder(holder: GrilleTagViewHolder, position: Int) {
        val tag = tags[position]

        holder.tagTitle.text = tag
    }

    override fun getItemCount(): Int {
        return tags.size
    }

}