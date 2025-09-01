package com.foodics.data.di

import androidx.room.Room
import local.dao.MenuDatabase
import local.dao.MenuLocalDataSource
import local.dao.MenuLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val daoModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MenuDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single { get<MenuDatabase>().menuDao() }

    single<MenuLocalDataSource> {
        MenuLocalDataSourceImpl(
            menuDao = get()
        )
    }
}

private const val DATABASE_NAME = "foodics_database"