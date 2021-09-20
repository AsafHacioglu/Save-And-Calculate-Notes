package com.asafallahverdiyev.averages.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asafallahverdiyev.averages.R
import com.asafallahverdiyev.averages.models.NoteModel
import kotlinx.android.synthetic.main.card_view.view.*

class RecyclerAdapter(val list: List<NoteModel>, val listener: Listener) : RecyclerView.Adapter<RecyclerAdapter.Widgets>() {

    interface Listener {

        fun onCardViewClickListener(model: NoteModel) {}
    }


    class Widgets(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(listener: Listener,list: List<NoteModel>, position: Int) {


            val listPosition = list[position]
            val subjectId = listPosition.subjectId
            val subjectName = listPosition.subjectName
            val subjectScore = listPosition.score
            val subjectCredit = listPosition.credit

            itemView.subject.text = subjectName
            itemView.score.text = subjectScore.toString()
            itemView.credit.text = subjectCredit.toString()


            val model = NoteModel(subjectId, subjectName, subjectScore, subjectCredit)
            itemView.cardView.setOnClickListener {
                listener.onCardViewClickListener(model)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Widgets {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return Widgets(view)
    }

    override fun onBindViewHolder(holder: Widgets, position: Int) {
        holder.bind(listener,list,position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}