package dev.kevinesg.notepadapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var content: String,
    @ColumnInfo(name = "updated_at") var updatedAt: String
)