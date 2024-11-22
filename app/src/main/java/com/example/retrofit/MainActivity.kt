package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.api.todo
import com.example.retrofit.appui.TodoViewmodel
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.api.NetworkResponse

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewmodel = ViewModelProvider(this)[TodoViewmodel::class.java]
        setContent {
            RetrofitTheme {
                TodoListPage(viewmodel)
            }
        }
    }
}

@Composable
fun TodoListPage(viewmodel : TodoViewmodel){

    val taskresult = viewmodel.todolist.observeAsState()
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp,40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween // Align text and icon with space between
                ) {
                    Text(
                        text = "Hello, Jetpack Compose!",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    IconButton(
                        onClick = {
                            viewmodel.getData()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search for any location"
                        )
                    }
                }
            }
            when(val result = taskresult.value){
                is NetworkResponse.Error -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = result.message)
                    }
                }
                NetworkResponse.Loading -> {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is NetworkResponse.Success -> {
                    TaskList(data = result.data)
                }
                null -> {}
            }
        }
    }
}


@Composable
fun TaskList(data: List<todo>){
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(data){ tasks ->
            TaskCard(tasks)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TaskCard(todo: todo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Task Name: ${todo.title}", style = MaterialTheme.typography.titleMedium)
            Text(text = "task id: ${todo.id}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "completion: ${todo.completed}", style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitTheme {
            TodoListPage(viewmodel = TodoViewmodel())
    }
}