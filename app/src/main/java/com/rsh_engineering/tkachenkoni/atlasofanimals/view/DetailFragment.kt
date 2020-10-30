package com.rsh_engineering.tkachenkoni.atlasofanimals.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.rsh_engineering.tkachenkoni.atlasofanimals.R
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.getProgressDrawable
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*


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
    }

    fun viewSetup(){
        iv_animal_detail.loadImage(animal?.imageUrl, getProgressDrawable(requireContext()))
        tv_animal_name_detail.text = animal?.name
        tv_animal_location.text = animal?.location
        tv_animal_lifespan.text = animal?.lifeSpan
        tv_animal_diet.text = animal?.diet
    }

}