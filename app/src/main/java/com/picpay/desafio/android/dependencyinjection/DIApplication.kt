package com.picpay.desafio.android.dependencyinjection

import android.app.Application
import com.picpay.desafio.android.data.repository.UserRepositoryOperation
import com.picpay.desafio.android.viewmodel.users.UsersViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class DIApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@DIApplication)
            modules(
                module {
                    single {
                        UserRepositoryOperation()
                    }

                    viewModel { (userRepository: UserRepositoryOperation) ->
                        UsersViewModel(userRepository)
                    }
                }
            )
        }
    }
}