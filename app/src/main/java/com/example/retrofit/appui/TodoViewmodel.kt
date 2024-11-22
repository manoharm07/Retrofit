package com.example.retrofit.appui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.api.NetworkResponse
import com.example.retrofit.api.RetrofitInstance
import com.example.retrofit.api.fakelist
import com.example.retrofit.api.todo
import kotlinx.coroutines.launch

class TodoViewmodel: ViewModel() {

    private val todoapi = RetrofitInstance.api
    private val _todolist = MutableLiveData<NetworkResponse<List<todo>>>()
    val todolist : LiveData<NetworkResponse<List<todo>>> = _todolist


    fun getData(){
        _todolist.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
              val response = todoapi.getTodos()
              if(response.isSuccessful){
                    response.body()?.let{
                        _todolist.value = NetworkResponse.Success(it)
                    }
                }else{
                    _todolist.value = NetworkResponse.Error("Failed to load data")
                }
            }
            catch (ex : Exception){
                _todolist.value = NetworkResponse.Error("Exception")
            }
        }

    }
}
