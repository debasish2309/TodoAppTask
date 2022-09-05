package com.deb.todoapptask.addtasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deb.todoapptask.R
import com.deb.todoapptask.databinding.FragmentAddTasksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTasksFragment : Fragment(R.layout.fragment_add_tasks) {
    private val viewModel : AddEditTaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddTasksBinding.bind(view)

        binding.buttonSubmit.setOnClickListener {
            viewModel.addTask(binding.insertText.text.toString())

            val action = AddTasksFragmentDirections.actionAddTasksFragmentToTasksFragment()
            findNavController().navigate(action)

        }
    }
}