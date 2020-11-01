package com.rsh_engineering.tkachenkoni.atlasofanimals.view

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.rsh_engineering.tkachenkoni.atlasofanimals.R
import com.rsh_engineering.tkachenkoni.atlasofanimals.databinding.FragmentDetailBinding
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalePalette
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.getProgressDrawable
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.loadImage


class DetailFragment : Fragment() {

    var animal : AnimalModel? = null
    private lateinit var databinding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            animal = DetailFragmentArgs.fromBundle(it).animal
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

    }

    fun initUI(){

        databinding.animalmodel = animal

        viewSetup()
        viewSetupBackgroundColor()
    }

    fun viewSetup(){
        //databinding.ivAnimalDetail.loadImage(animal?.imageUrl, getProgressDrawable(requireContext()))
    }

    fun viewSetupBackgroundColor(){
        animal?.imageUrl?.let { url ->
            Log.d("COLORPALETTE", " url = $url")
            Glide.with(requireActivity())
                .asBitmap()
                .load(url)

                .into(object : CustomTarget<Bitmap>() {

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        Palette.from(resource).generate{ pal ->
                            val intColor = pal?.lightMutedSwatch?.rgb ?: 0
                            Log.d("COLORPALETTE", " iniColor = $intColor")
                            //databinding.llAnimalDetail.setBackgroundColor(intColor)

                            //databinding.palette = AnimalePalette(intColor)

                        }

                    }

                    override fun onLoadCleared(placeholder: Drawable?) {


                    }

                })
        }
    }
}