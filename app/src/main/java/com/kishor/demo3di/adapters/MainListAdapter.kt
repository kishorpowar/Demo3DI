package com.kishor.demo3di.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kishor.demo3di.R
import com.kishor.demo3di.data.Data
import com.kishor.demo3di.fragments.ListFragment
import kotlinx.android.synthetic.main.list_item.view.*

class MainListAdapter(var context: Context) : PagingDataAdapter<Data, MainListAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewName.text =
            "${getItem(position)?.firstName} ${getItem(position)?.lastName}"
        holder.itemView.textViewEmail.text = getItem(position)?.email
        Glide.with(context)
            .load(getItem(position)?.avatar)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.itemView.imgview)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    object DataDifferntiator : DiffUtil.ItemCallback<Data>() {

        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

}
