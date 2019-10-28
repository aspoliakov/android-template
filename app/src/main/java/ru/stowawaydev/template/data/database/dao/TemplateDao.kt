package ru.stowawaydev.template.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import ru.stowawaydev.template.data.database.AppDatabase
import ru.stowawaydev.template.data.model.TemplateDB

/**
 * template header (replace it)
 */

@Dao
abstract class TemplateDao : BaseDao<TemplateDB>() {

    companion object {
        const val TABLE = AppDatabase.TABLE_TEMPLATE
    }

    // -> read

    @Query("SELECT * FROM $TABLE")
    abstract fun readAll(): Maybe<List<TemplateDB>>

    @Query("SELECT * FROM $TABLE WHERE template_id = :templateId LIMIT 1")
    abstract fun readById(templateId: String): Maybe<TemplateDB>

    // <- read

    // -> update

    @Query("UPDATE $TABLE SET field = :value WHERE template_id = :templateId")
    abstract fun updateField(templateId: String, value: String): Completable

    // <- update

    // -> delete

    @Query("DELETE FROM $TABLE")
    abstract fun deleteAll(): Completable

    @Query("DELETE FROM $TABLE WHERE template_id = :templateId")
    abstract fun deleteById(templateId: String): Completable

    // <- delete
}
