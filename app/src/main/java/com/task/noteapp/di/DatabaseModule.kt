package com.task.noteapp.di

import android.content.Context
import androidx.room.Room
import com.task.noteapp.application.MainApplication
import com.task.noteapp.data.database.NotesDatabase
import com.task.noteapp.data.database.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideContext(): Context = MainApplication()


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NotesDatabase =
        Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes.db"
        ).fallbackToDestructiveMigration().build()


    @Provides
    fun provideLogDao(database: NotesDatabase): NotesDao {
        return database.notesDao()
    }
}