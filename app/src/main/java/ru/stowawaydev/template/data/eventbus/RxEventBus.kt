package ru.stowawaydev.template.data.eventbus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * template header (replace it)
 */

abstract class RxEventBus<T> {

    private val busObservable: Subject<T>

    init {
        busObservable = PublishSubject.create()
    }

    protected fun publishEvent(event: T) = busObservable.onNext(event)

    protected fun listenEvents(): Observable<T> = busObservable
}
