package com.example.gymappkotlin.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.FragmentUserHomeBinding
import com.example.gymappkotlin.databinding.FragmentUserProfileBinding
import com.example.gymappkotlin.fragment.BaseFragment
import com.example.gymappkotlin.viewmodel.UserHomeFragmentViewModel
import com.example.gymappkotlin.viewmodel.UserProfileViewModel

class UserProfileFragment : BaseFragment() {

    lateinit var binding: FragmentUserProfileBinding
    private lateinit var viewmodel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_profile, container, false
        )

        val view: View = binding.getRoot()
        viewmodel = ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        return view
    }
}