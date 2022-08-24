package com.picpay.desafio.android
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import junit.framework.Assert.assertEquals
import org.junit.rules.TestRule
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.mockito.Mock
import org.junit.Before
import org.junit.Test

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.viewmodel.users.UsersDataResult
import com.picpay.desafio.android.viewmodel.users.UsersViewModel

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever

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

   @Test
    fun `should be return a user`() {
        suspend fun returnUserTest() = runBlocking {

            val usuario: List<User> =
                listOf(User(img = "", name = "name", id = 1, username = "username"))

            val users = MutableLiveData<UsersDataResult>()
            viewModel.getUsers()

            doReturn(usuario).`when`(userRepository).getUserFromDataSource()

            assertEquals(usuario, users.value)
        }
    }

    @Test
    fun `should be return an error`() = runBlocking {

        val returnErrorEnabled: UsersDataResult.ReturnError = UsersDataResult.ReturnError(error = true)

        val users = MutableLiveData<UsersDataResult>()
        viewModel.getUsers()

        doReturn(returnErrorEnabled).`when`(userRepository).getUserFromDataSource()

        assertEquals(returnErrorEnabled, users.value)
    }
}