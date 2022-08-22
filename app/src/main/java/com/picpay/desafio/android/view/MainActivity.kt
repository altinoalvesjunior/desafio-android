package com.picpay.desafio.android.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.data.repository.UserRepositoryOperation
import com.picpay.desafio.android.viewmodel.users.UsersDataResult
import com.picpay.desafio.android.viewmodel.users.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: UsersViewModel by viewModel {
            parametersOf(UserRepositoryOperation())
        }

        createRecyclerView()
        createProgressBar()

        model.usersMutableLiveData.observe(this, Observer { users ->
            users?.let {
                adapter.notifyDataSetChanged()

                when (it) {
                    is UsersDataResult.ReturnUsers -> showUsers(it.userList)
                    is UsersDataResult.ReturnError -> showError()
                }
            }
        })

        model.getUsers()
    }

    private fun createProgressBar() {
        progressBar = findViewById(R.id.user_list_progress_bar)
        progressBar.visibility = View.VISIBLE
    }

    private fun createRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showError() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        showNotification(this, getString(R.string.error))
    }

    private fun showUsers(user: List<User>) {
        progressBar.visibility = View.GONE
        adapter.users = user
    }

    private fun showNotification(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
