package com.meza.moviesapp.core.di

import com.meza.moviesapp.core.BaseApplication
import com.meza.moviesapp.core.di.modules.RepositoryModule
import com.meza.moviesapp.core.di.modules.NetworkModule
import com.meza.moviesapp.core.di.viewmodel.ViewModelFactory
import com.meza.moviesapp.core.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuildersModule::class,
    FragmentBuildersModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    RepositoryModule::class
])
@Singleton
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): AppComponent

    }

    fun factory(): ViewModelFactory

}