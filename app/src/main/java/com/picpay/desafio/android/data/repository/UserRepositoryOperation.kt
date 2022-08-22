package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.service.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryOperation : UserRepository {
    override suspend fun getUserFromDataSource() = withContext(Dispatchers.IO) {
        RemoteDataSource().service.getUsers()
    }
}