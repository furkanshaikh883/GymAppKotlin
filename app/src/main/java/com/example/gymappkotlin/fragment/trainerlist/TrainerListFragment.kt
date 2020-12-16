package com.example.gymappkotlin.fragment.trainerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.FragmentTrainerListBinding
import com.example.gymappkotlin.fragment.BaseFragment
import com.example.gymappkotlin.viewmodel.TrainerListFragmentViewModel

class TrainerListFragment: BaseFragment() {

    private lateinit var viewmodel: TrainerListFragmentViewModel
    lateinit var binding: FragmentTrainerListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_trainer_list, container, false
        )

        val view: View = binding.getRoot()
        viewmodel = ViewModelProviders.of(this).get(TrainerListFragmentViewModel::class.java)
        return view
    }
}