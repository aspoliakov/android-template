package ru.stowawaydev.template.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.stowawaydev.template.data.database.AppDatabase

/**
 * template header (replace it)
 */

@Entity(tableName = AppDatabase.TABLE_TEMPLATE, indices = [
    Index(value = ["template_id"], unique = true)]
)
data class TemplateDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0,

    @ColumnInfo(name = "template_id")
    var templateId: String,

    @ColumnInfo(name = "field")
    var field: String
)
