package com.deb.todoapptask.tasks

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.deb.todoapptask.R
import com.deb.todoapptask.databinding.FragmentTasksBinding
import com.deb.todoapptask.room.Tasks
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tasks.*

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks), TaskAdapter.OnItemClickListener {

    private val viewModel: TaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTasksBinding.bind(view)

        val taskAdapter = TaskAdapter(this)

        binding.apply {
            recyclerview.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
            fab.setOnClickListener {
                val action = TasksFragmentDirections.actionTasksFragmentToAddTasksFragment()
                findNavController().navigate(action)
            }
        }

        viewModel.tasks.observe(viewLifecycleOwner){
            taskAdapter.submitList(it)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.order_list,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.orderlist -> {
                return  true
            }
            else -> {
                return false
            }
        }
    }

    override fun onItemClick(tasks: Tasks) {
        viewModel.onTaskSelected(tasks)
    }

    override fun onCheckBoxClick(tasks: Tasks, isChecked: Boolean) {
        viewModel.onTaskCheckedChanged(tasks, isChecked)
    }

}