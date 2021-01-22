package com.dicoding.favorite.di

import android.content.Context
import com.dicoding.capstone.FavoriteModuleDependencies
import com.dicoding.favorite.ui.FavoriteFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [FavoriteModuleDependencies::class],
    modules = [ViewModelModule::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(moduleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}
