package com.example.roomandmaterial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.roomandmaterial.controllers.UserController
import com.example.roomandmaterial.databinding.ActivityMainBinding
import com.example.roomandmaterial.models.UserEntity

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userController: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        userController = UserController(baseContext)

        this.handleEvents()
    }

    private fun handleEvents(){
        this.binding.register.setOnClickListener(this)
        this.binding.btn.setOnClickListener(this)
    }

    override fun onClick(component: View) {
        when(component.id){
            this.binding.register.id -> this.register()
            this.binding.btn.id -> this.login()
        }
    }

    private fun login(){

        val user: UserEntity = UserEntity(binding.email.editText?.text.toString(), binding.password.editText?.text.toString())
        if(userController.checkLogin(user)){
            val mainActivity2Intent = Intent(baseContext, MainActivity2::class.java)
            startActivity(mainActivity2Intent)
        }
    }

    private fun register(){
        val user: UserEntity = UserEntity(binding.email.editText?.text.toString(), binding.password.editText?.text.toString())
        userController.save(user.email, user.password)
    }
}