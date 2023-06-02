package com.example.taskmanager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.HomeFragment


class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var tasks: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        with(binding) {
            btnSave.setOnClickListener {
                if (binding.btnSave.text.equals(getString(R.string.update))) {
                    onUpdate()
                } else {
                    onSave()
                }
                findNavController().navigateUp()
            }
        }
    }

    private fun onSave() {
        val result = Task(
            title = binding.etTitle.toString(),
            desc = binding.etDesc.toString()
        )
        App.db.dao().insert(result)
    }

    private fun onUpdate() {
        val result = tasks?.copy(
            title = binding.etTitle.toString(),
            desc = binding.etDesc.toString()
        )
        if (result != null) {
            App.db.dao().update(result)
        }
    }

    private fun initData() {
        tasks = arguments?.getSerializable(HomeFragment.TASKS_KEY) as Task?
        tasks?.let {
            binding.btnSave.text = getString(R.string.update)
            binding.etTitle.setText(it.title)
            binding.etDesc.setText(it.desc)
        }
    }

}