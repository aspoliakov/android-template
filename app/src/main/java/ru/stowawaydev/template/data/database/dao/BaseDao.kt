package ru.stowawaydev.template.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * template header (replace it)
 */

abstract class BaseDao<T> {

    companion object {
        const val TRUE = 1
        const val FALSE = 0
    }

    // -> create

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun create(entity: T): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun createWithResult(entity: T): Maybe<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun createBlocking(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun createList(entities: Collection<T>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun createListBlocking(entities: Collection<T>)

    // <- create

    // -> update

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(vararg entity: T): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateBlocking(vararg entity: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateList(entities: Collection<T>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateListBlocking(entities: Collection<T>)

    fun update(entity: Maybe<T>, modify: (T) -> Unit): Completable {
        return entity.map {
            modify(it)
            it
        }.flatMapCompletable { update(it) }
    }

    fun updateList(entities: Maybe<List<T>>, modify: (T) -> Unit): Completable {
        return entities.flatMapObservable { Observable.fromIterable(it) }
            .map {
                modify(it)
                it
            }.toList()
            .flatMapCompletable { updateList(it) }
    }

    // <- update
}
