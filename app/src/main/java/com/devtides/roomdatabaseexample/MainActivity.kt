package com.devtides.roomdatabaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devtides.roomdatabaseexample.databinding.ActivityMainBinding
import com.devtides.roomdatabaseexample.db.User
import com.devtides.roomdatabaseexample.db.UserDao
import com.devtides.roomdatabaseexample.db.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = UserDatabase.getInstance(application).userDao()

        val user1 = User("James", "Bond", 35, true)
        val user2 = User("Alice", "Smith", 28, true)
        val user3 = User("Michael", "M.", 25, false)



        GlobalScope.launch {
            withContext(Dispatchers.Main){
                userDao.deleteAll()
                userDao.insert(user1, user2, user3)
                displayUsers()
            }
        }
    }

    private suspend fun displayUsers() {
        val users = userDao.getAllUsers()
        var displayText = ""
        for (user in users) {
            displayText += "${user.name} ${user.lastName} - ${user.age}\n"
        }
        binding.disp.text=displayText
    }
}