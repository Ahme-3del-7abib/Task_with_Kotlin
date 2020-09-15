package com.ibtikar.apps.task.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibtikar.apps.task.R
import com.ibtikar.apps.task.pojo.Actors
import com.ibtikar.apps.task.utils.ConstantsUtils
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class MyAdapter(

    private var onActorClickListener: OnActorClickListener

) : RecyclerView.Adapter<MyAdapter.ActorsViewHolders>() {


    private var actorList: List<Actors.Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolders {
        return ActorsViewHolders(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.actors_list_items, parent, false), onActorClickListener
        )
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    override fun onBindViewHolder(holder: ActorsViewHolders, position: Int) {

        Picasso.get()
            .load(ConstantsUtils.BASE_IMAGE_SOURCE + actorList.get(position).profilePath)
            .into(holder.profileImg)

        holder.name?.text = "Name : " + actorList.get(position).name
        holder.depart?.text = "Department  : " + actorList.get(position).knownForDepartment

        if (actorList.get(position).gender == 1) {
            holder.gender?.text = "Female"
        } else {
            holder.gender?.text = "Male"
        }
    }

    fun setList(list: List<Actors.Result>) {
        this.actorList = list
        notifyDataSetChanged()
    }

    class ActorsViewHolders(itemView: View, listener: OnActorClickListener) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var onActorClickListener: OnActorClickListener? = null
        var name: TextView? = null
        var depart: TextView? = null
        var gender: TextView? = null

        var profileImg: CircleImageView? = null

        init {
            name = itemView.findViewById(R.id.nameID)
            depart = itemView.findViewById(R.id.departID)
            gender = itemView.findViewById(R.id.genderId)
            profileImg = itemView.findViewById(R.id.imgID)

            this.onActorClickListener = listener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            onActorClickListener?.onActorClick(adapterPosition)
        }
    }

    interface OnActorClickListener {
        fun onActorClick(position: Int)
    }
}