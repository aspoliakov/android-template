package ru.stowawaydev.template.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.stowawaydev.template.data.database.dao.TemplateDao
import ru.stowawaydev.template.data.model.TemplateDB

/**
 * template header (replace it)
 */

@Database(
    entities = [
        TemplateDB::class
    ],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "template.db"

        const val TABLE_TEMPLATE = "categories"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }

    abstract fun templateDao(): TemplateDao
}
