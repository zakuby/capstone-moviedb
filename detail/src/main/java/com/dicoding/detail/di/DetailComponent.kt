package com.dicoding.detail.di

import android.content.Context
import com.dicoding.core.di.FavoriteModuleDependencies
import com.dicoding.detail.ui.DetailActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [FavoriteModuleDependencies::class],
    modules = [DetailModule::class, DetailNetworkModule::class, ViewModelModule::class]
)
interface DetailComponent {

    fun inject(activity: DetailActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(moduleDependencies: FavoriteModuleDependencies): Builder
        fun build(): DetailComponent
    }
}
