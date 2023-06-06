package com.example.taskmanager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.model.InfoProfile
import com.example.taskmanager.utils.loadImage

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        redactorProfile()
        initData()
    }

    private fun initData() {
        with(binding) {
            ivPerson.loadImage(pref.getPhoto())
            tvName.text = pref.getName()
            tvNameTop.text = pref.getName()
        }
    }


    private fun redactorProfile() {
        binding.btnRedactorProfile.setOnClickListener {
            findNavController().navigate(R.id.navigation_redactor_profile)
        }
    }

}