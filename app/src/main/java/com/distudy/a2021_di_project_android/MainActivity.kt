package com.distudy.a2021_di_project_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.distudy.a2021_di_project_android.common.Activity.BaseActivity
import com.distudy.a2021_di_project_android.ui.UserListFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.frame_content, UserListFragment()).commit()
    }
}