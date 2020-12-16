package com.example.gymappkotlin.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.FragmentUserProfileBinding
import com.example.gymappkotlin.databinding.FragmentUserSearchBinding
import com.example.gymappkotlin.fragment.BaseFragment
import com.example.gymappkotlin.viewmodel.UserProfileViewModel
import com.example.gymappkotlin.viewmodel.UserSearchFragmentViewModel

class UserSearchFragment  : BaseFragment() {

    lateinit var binding: FragmentUserSearchBinding
    private lateinit var viewmodel: UserSearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_search, container, false
        )

        val view: View = binding.getRoot()
        viewmodel = ViewModelProviders.of(this).get(UserSearchFragmentViewModel::class.java)
        return view
    }
}