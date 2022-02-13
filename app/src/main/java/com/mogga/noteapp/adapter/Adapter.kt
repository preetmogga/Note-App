package com.mogga.noteapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mogga.noteapp.R
import com.mogga.noteapp.model.Note

class Adapter(private val listener:DeleteNote):RecyclerView.Adapter<Holder>() {

    private val itemList:ArrayList<Note> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentPosition = itemList[position]
       holder.deleteImg.setOnClickListener {
           listener.onItemClick(currentPosition)


       }
        holder.text.text = currentPosition.task
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>){
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }


}
interface DeleteNote{
    fun onItemClick(note: Note)
}
class Holder(itemView:View):RecyclerView.ViewHolder(itemView){
    val text:TextView = itemView.findViewById(R.id.taskView)
    val deleteImg:ImageView = itemView.findViewById(R.id.deleteImg)


}