package com.example.yandexhw.main_screen.di

import android.content.Context
import com.example.yandexhw.main_screen.models.FileNotebook
import com.example.yandexhw.main_screen.utils.MainConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File

@Module
@InstallIn(ViewModelComponent::class)
object FileNotebookModule {

    @Provides
    fun provideFileNotebook(@ApplicationContext context: Context): FileNotebook {
        val file = File(context.filesDir, MainConstant.FILE_NAME)
        return FileNotebook(file)
    }
}