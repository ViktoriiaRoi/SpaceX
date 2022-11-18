package com.viktoriiaroi.core.di

import android.content.Context
import androidx.room.Room
import com.viktoriiaroi.core.database.SpaceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    companion object {
        private const val DB_NAME = "spacex.db"
    }

    @Provides
    @Singleton
    fun provideSpaceDatabase(@ApplicationContext appContext: Context): SpaceDatabase {
        return Room.databaseBuilder(
            appContext,
            SpaceDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesCompanyDao(database: SpaceDatabase) = database.getCompanyDao()

    @Provides
    @Singleton
    fun providesNewsDao(database: SpaceDatabase) = database.getNewsDao()

    @Provides
    @Singleton
    fun providesLaunchDao(database: SpaceDatabase) = database.getLaunchDao()

}
