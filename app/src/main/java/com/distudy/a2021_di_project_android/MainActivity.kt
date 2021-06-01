package com.distudy.a2021_di_project_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.distudy.a2021_di_project_android.ui.UserListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.frame_content, UserListFragment())
        }
    }
}