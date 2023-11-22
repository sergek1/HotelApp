package com.example.resources

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextViewAdapter(private val texts: List<String>) :
    RecyclerView.Adapter<TextViewAdapter.TextViewHolder>() {

    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_textview, parent, false)
        return TextViewHolder(view)
    }

    override fun getItemCount(): Int {
        return texts.size
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val text = texts[position]
        holder.textView.text = text
    }
}