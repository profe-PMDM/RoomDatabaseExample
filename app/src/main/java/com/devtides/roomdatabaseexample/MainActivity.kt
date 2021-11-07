package com.devtides.roomdatabaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devtides.roomdatabaseexample.databinding.ActivityMainBinding
import com.devtides.roomdatabaseexample.db.User
import com.devtides.roomdatabaseexample.db.UserDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDao = UserDatabase.getInstance(application).userDao()

        val user1 = User("James", "Bond", 35, true)
        val user2 = User("Alice", "Smith", 28, true)
        val user3 = User("Michael", "M.", 25, false)

        userDao.insert(user1, user2, user3)
        val users = userDao.getAllUsers()
        var displayText = ""
        for (user in users) {
            displayText += "${user.name} ${user.lastName} - ${user.age}\n"
        }
        binding.display.text = displayText

    }
}