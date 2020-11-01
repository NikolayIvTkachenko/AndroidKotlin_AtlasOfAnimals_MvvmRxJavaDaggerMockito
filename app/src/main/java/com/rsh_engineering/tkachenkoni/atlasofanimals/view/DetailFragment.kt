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
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.getProgressDrawable
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    var animal : AnimalModel? = null

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
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

    }


    fun initUI(){
        viewSetup()
        viewSetupBackgroundColor()
    }

    fun viewSetup(){
        iv_animal_detail.loadImage(animal?.imageUrl, getProgressDrawable(requireContext()))
        tv_animal_name_detail.text = animal?.name
        tv_animal_location.text = animal?.location
        tv_animal_lifespan.text = animal?.lifeSpan
        tv_animal_diet.text = animal?.diet
    }

    fun viewSetupBackgroundColor(){
        animal?.imageUrl?.let { url ->
            Log.d("COLORPALETTE", " url = $url")
            Glide.with(requireActivity())
                .asBitmap()
                .load(url)
//                .listener(object : RequestListener<Bitmap> {
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: Target<Bitmap>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        Log.d("COLORPALETTE", " onLoadFailed")
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: Bitmap?,
//                        model: Any?,
//                        target: Target<Bitmap>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        Log.d("COLORPALETTE", " onResourceReady")
//                        resource?.let {
//                            val palImg = Palette.from(it).generate()
//                            val intColor = palImg?.lightMutedSwatch?.rgb ?: 0
//                            Log.d("COLORPALETTE", " iniColor = $intColor")
//                            ll_animal_detail.setBackgroundColor(intColor)
//
//                            return true
//                        }
//                        return false
//                    }
//
//                })
                .into(object : CustomTarget<Bitmap>() {

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {

                        Log.d("COLORPALETTE", " resource = $resource")
                        Log.d("COLORPALETTE", " resource.byteCount = ${resource.byteCount}")
//                        val palette = Palette.from(resource).generate(object : Palette.PaletteAsyncListener{
//                            override fun onGenerated(palette: Palette?) {
//                                palette?.let {
//                                    val defaultColor = 0
//                                    val intColor = it.getDarkMutedColor(defaultColor)
//                                    //ll_animal_detail.setBackgroundColor(intColor)
//                                }
//
//                            }
//                        })
                        Palette.from(resource).generate{ pal ->
                            val intColor = pal?.lightMutedSwatch?.rgb ?: 0
                            Log.d("COLORPALETTE", " iniColor = $intColor")
                            //ll_animal_detail.setBackgroundColor(intColor)
                        }

                    }

                    override fun onLoadCleared(placeholder: Drawable?) {


                    }

                })
        }
    }
}