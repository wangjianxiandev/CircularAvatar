package com.wjx.android.circularavatar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        avatar_one.setCircleName("嘿嘿嘿嘿")
        avatar_two.setCircleName("wjxbless")
    }
}
