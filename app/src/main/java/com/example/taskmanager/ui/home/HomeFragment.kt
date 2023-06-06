package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::longClick, this::updateClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewHome.adapter = adapter
        fabClick()
        getDataResult()
    }

    private fun fabClick() {
        with(binding) {
            btnFab.setOnClickListener {
                findNavController().navigate(R.id.navigation_task)
            }
        }
    }

    private fun getDataResult() {
        val tasks = App.db.dao().getAll()
        adapter.addData(tasks)
    }

    private fun longClick(task: Task) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Item")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Delete") { _, _ ->
                App.db.dao().delete(task)
                getDataResult()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun updateClick(tasks: Task) {
        findNavController().navigate(R.id.navigation_task, bundleOf(TASKS_KEY to tasks))
    }

    companion object {
        const val TASKS_KEY = "tasks.key"
    }

}

