package me.mitul.todo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import dagger.hilt.android.AndroidEntryPoint
import me.mitul.todo.app.MainContent

@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainContent()
        }
    }
}
