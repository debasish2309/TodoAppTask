package com.deb.todoapptask.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.deb.todoapptask.room.Tasks
import com.deb.todoapptask.room.TasksDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class TaskViewModel @ViewModelInject constructor(
    private val tasksDao: TasksDao
) : ViewModel(){

    private val tasksEventChannel = Channel<TasksEvent>()
    val tasksEvent = tasksEventChannel.receiveAsFlow()

    val tasks = tasksDao.getAllTasks().asLiveData()

    fun onTaskSelected(tasks: Tasks){
    }

    fun onTaskCheckedChanged(tasks: Tasks, isChecked: Boolean) = viewModelScope.launch {
        tasksDao.updateTask(tasks.copy(completed = isChecked))
    }


    sealed class TasksEvent {
        data class ShowUndoDeleteTaskMessage(val tasks: Tasks) : TasksEvent()
    }


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