package com.soethan.apolloandroid.di

import android.app.Application
import com.soethan.apolloandroid.app.BaseApplication
import com.soethan.apolloandroid.di.module.ActivityModule
import com.soethan.apolloandroid.di.module.AppModule
import com.soethan.apolloandroid.di.module.ViewModelModule
import com.soethan.apolloandroid.repository.ToDoRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun toDoRepository(): ToDoRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
