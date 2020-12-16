package com.example.gymappkotlin.fragment.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.FragmentUserHomeBinding
import com.example.gymappkotlin.fragment.BaseFragment

class UserMoreFragment : BaseFragment() {

    lateinit var binding: FragmentUserHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_more_options, container, false
        )

        val view: View = binding.getRoot()
        return view
    }
}