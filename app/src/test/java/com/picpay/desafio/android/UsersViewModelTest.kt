package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import org.junit.Rule
import org.mockito.Mock
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

import com.picpay.desafio.android.viewmodel.users.UsersViewModel
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.viewmodel.users.UsersDataResult
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: UsersViewModel

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        viewModel = UsersViewModel(userRepository)
    }
}

