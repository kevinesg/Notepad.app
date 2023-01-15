package dev.kevinesg.notepadapp

import android.app.Application
import dev.kevinesg.notepadapp.data.AppContainer
import dev.kevinesg.notepadapp.data.AppDataContainer

class NoteApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}