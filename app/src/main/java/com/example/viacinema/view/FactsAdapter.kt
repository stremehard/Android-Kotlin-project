package com.example.viacinema.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viacinema.R
import com.example.viacinema.usecase.CatFact
import kotlinx.android.synthetic.main.cat_fact.view.*

class FactsAdapter: RecyclerView.Adapter<FactsAdapter.FactViewHolder>() {

    private val items = mutableListOf<CatFact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        return FactViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cat_fact, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(items[position])
    }


    fun updateData(newItems: List<CatFact>?) {
        newItems?.let {
            items.clear()
            items.addAll(it)

            notifyDataSetChanged()
        }
    }

    inner class FactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(fact: CatFact) {
            itemView.factText.text = fact.fact
        }
    }
}