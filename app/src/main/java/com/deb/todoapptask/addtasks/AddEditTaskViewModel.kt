package com.deb.todoapptask.addtasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.deb.todoapptask.room.Tasks
import com.deb.todoapptask.room.TasksDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddEditTaskViewModel @ViewModelInject constructor(
    private val tasksDao: TasksDao
) : ViewModel() {

    private val timeStamp: String = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())

    fun addTask(task : String){
        CoroutineScope(Dispatchers.IO).launch {
            tasksDao.insertTask(
                Tasks(0,
                    task,
                    timeStamp,
                    false)
            )
        }
    }
}