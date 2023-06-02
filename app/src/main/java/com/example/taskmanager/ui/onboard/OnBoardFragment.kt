package com.example.taskmanager.ui.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentOnBoardBinding
import com.example.taskmanager.ui.onboard.adapter.OnBoardAdapter

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    private val adapter = OnBoardAdapter(this::onFinishClick)
    private lateinit var pref: Pref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        initAdapter()
    }

    private fun initAdapter() {
        with(binding) {
            viewPagerOnboard.adapter = adapter
            circleIndicator.setViewPager(viewPagerOnboard)
        }
    }

    private fun onFinishClick() {
        pref.userSeen()
        findNavController().navigateUp()
    }
}