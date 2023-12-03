package com.example.roomandmaterial.controllers

import android.content.Context
import com.example.roomandmaterial.database.DatabaseDB
import com.example.roomandmaterial.models.UserEntity
import com.example.roomandmaterial.repository.UserDAO

class UserController(private var context: Context) {
    private lateinit var dao: UserDAO

    init {
        dao = DatabaseDB.getDatabase(context).userDAO()
    }

    fun save(email: String, password: String){
        if(email.count{it == '@'} == 1){
            val user: UserEntity = UserEntity(email, password)
            dao.save(user)
        }else{
            println("FAIL TO SAVE USER" )
        }
    }


    fun checkLogin(userComp: UserEntity): Boolean{
        val user: UserEntity? = getUser(userComp.email)
        if (user?.password == userComp.password ){
            println("OK TO LOGIN USER" )

            return true
        }
        println("FAIL TO LOGIN USER" )

        return false
    }

    private fun getUser(email: String): UserEntity?{
        var user: UserEntity? = null
        if(email.count{it == '@'} == 1){
            user = dao.getUser(email)
        }
        println("USER $user EMAIL $email" )
        return user
    }
}