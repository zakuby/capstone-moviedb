package com.dicoding.core.di

import androidx.lifecycle.ViewModel
import androidx.work.ListenableWorker
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
@Target(AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER)
annotation class ViewModelKey(val value: KClass<out ViewModel>)