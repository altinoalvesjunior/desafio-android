package com.picpay.desafio.android.viewmodel.users

import com.picpay.desafio.android.model.User

sealed class UsersDataResult {
    data class ReturnUsers(val userList: List<User>): UsersDataResult()
    data class ReturnError(val error: Boolean): UsersDataResult()
}