package com.example.gymappkotlin.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.FragmentUserHomeBinding
import com.example.gymappkotlin.fragment.BaseFragment
import com.example.gymappkotlin.viewmodel.UserHomeFragmentViewModel

class UserHomeFragment : BaseFragment(){

    private lateinit var viewmodel: UserHomeFragmentViewModel
    lateinit var binding: FragmentUserHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_home, container, false
        )

        val view: View = binding.getRoot()
        viewmodel = ViewModelProviders.of(this).get(UserHomeFragmentViewModel::class.java)
        return view
    }

}