package com.chlwhdtn.voit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text

class DebateAdapter(val list: ArrayList<Debate>, val context: Context) : RecyclerView.Adapter<DebateAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        = DebateAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.debate_item, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title_tv: TextView = itemView.findViewById(R.id.debate_title)
        val agree_tv: MaterialButton = itemView.findViewById(R.id.debate_agree)
        val disagree_tv: MaterialButton = itemView.findViewById(R.id.debate_disagree)

        fun bind(item: Debate) {
            title_tv.text = item.title
            agree_tv.text = "찬성 " + item.ticket_agree.toString()
            disagree_tv.text = "반대 " + item.ticket_disagree.toString()
        }
    }
}