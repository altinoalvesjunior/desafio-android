package com.picpay.desafio.android.viewmodel.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.repository.UserRepository
import kotlinx.coroutines.launch

class UsersViewModel(private val repository: UserRepository): ViewModel() {

    val usersMutableLiveData = MutableLiveData<UsersDataResult>()

    fun getUsers() {
        viewModelScope.launch {
            try {
                val result = repository.getUserFromDataSource()
                usersMutableLiveData.value = UsersDataResult.ReturnUsers(result)
            } catch (e: Exception) {
                usersMutableLiveData.value = UsersDataResult.ReturnError(true)
            }
        }
    }
}