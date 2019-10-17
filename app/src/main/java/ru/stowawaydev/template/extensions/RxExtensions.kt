package ru.stowawaydev.template.extensions

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * template header (replace it)
 */

// -> Observable

fun <T : Any> Observable<T>.applySchedulers(): Observable<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Observable<T>.applyMainSchedulers(): Observable<T> =
    this.subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Observable<T>.applyIoSchedulers(): Observable<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())

fun <T : Any> Observable<T>.surround(before: () -> Unit, after: () -> Unit): Observable<T> =
    this.doOnSubscribe { before() }
        .doOnTerminate { after() }

inline fun <T : Any> Observable<T>.doCompletable(crossinline completable: (T) -> Completable): Observable<T> =
    this.flatMap { obj ->
        completable(obj)
            .andThen(Observable.just(obj))
    }

// <- Observable

// -> Flowable

fun <T : Any> Flowable<T>.applySchedulers(): Flowable<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
// <- Flowable


// -> Completable

fun Completable.applySchedulers(): Completable =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun Completable.applyMainSchedulers(): Completable =
    this.subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())

fun Completable.applyIoSchedulers(): Completable =
    this.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())

fun Completable.surround(before: () -> Unit, after: () -> Unit): Completable =
    this.doOnSubscribe { before() }
        .doOnTerminate { after() }

// <- Completable

// -> Single

fun <T : Any> Single<T>.applySchedulers(): Single<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Single<T>.applyMainSchedulers(): Single<T> =
    this.subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Single<T>.applyIoSchedulers(): Single<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())

fun <T : Any> Single<T>.surround(before: () -> Unit, after: () -> Unit): Single<T> =
    this.doOnSubscribe { before() }
        .doOnSuccess { after() }
        .doOnError { after() }
// <- Single

// -> Maybe

fun <T : Any> Maybe<T>.applySchedulers(): Maybe<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Maybe<T>.applyMainSchedulers(): Maybe<T> =
    this.subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Maybe<T>.applyIoSchedulers(): Maybe<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())

fun <T : Any> Maybe<T>.surround(before: () -> Unit, after: () -> Unit): Maybe<T> =
    this.doOnSubscribe { before() }
        .doOnSuccess { after() }
        .doOnError { after() }

// <- Maybe

// -> Disposable

fun Disposable.addTo(disposables: CompositeDisposable?) = disposables?.add(this)

// <- Disposable
