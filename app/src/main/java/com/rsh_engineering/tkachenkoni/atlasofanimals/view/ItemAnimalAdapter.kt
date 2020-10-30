package com.rsh_engineering.tkachenkoni.atlasofanimals.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rsh_engineering.tkachenkoni.atlasofanimals.R
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.getProgressDrawable
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.loadImage
import kotlinx.android.synthetic.main.item_animal.view.*

/**
 *
 * Created by Nikolay Tkachenko on 30, October, 2020
 *
 */
class ItemAnimalAdapter (val context: Context) : RecyclerView.Adapter<ItemAnimalAdapter.AnimalViewHolder>(){

    private var list : ArrayList<AnimalModel> = ArrayList()

    class AnimalViewHolder(var view: View): RecyclerView.ViewHolder(view)


    fun updateData(animalList: ArrayList<AnimalModel>){
        list.clear()
        list.addAll(animalList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_animal, parent, false)

        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.iv_animal.loadImage(list[position].imageUrl, getProgressDrawable(context))
        holder.view.tv_animal_name.text = list[position].name
        holder.itemView.setOnClickListener {viewitem ->

            val action = ListFragmentDirections.actionGoToDetail(list[position])
            Navigation.findNavController(viewitem).navigate(action)
        }
    }

    override fun getItemCount() = list.size

}