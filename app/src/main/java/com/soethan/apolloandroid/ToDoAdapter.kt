package com.soethan.apolloandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soethan.apolloandroid.delegate.ToDoClick
import kotlinx.android.synthetic.main.main_content.view.*

class ToDoAdapter(

    private val todosList: List<GetToDosByIdQuery.Todo>,
    private val todoDelegate: ToDoClick
) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_content, parent, false)

        return ToDoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return todosList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        holder.itemView.txt_title.text = todosList[position].title()
        holder.itemView.txt_desc.text = todosList[position].description()
        holder.itemView.main_card.setOnClickListener {
            todoDelegate.onToDoClick(todosList[position].id())
        }

        holder.itemView.btn_remove.setOnClickListener {
            todoDelegate.onDeleteClick(todosList[position].id())
        }


    }


    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}