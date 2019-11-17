package ru.stowawaydev.template.ui.screens.main

import android.content.Context
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import ru.stowawaydev.template.R
import ru.stowawaydev.template.extensions.applySchedulers
import ru.stowawaydev.template.ui.screens.BasePresenter
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * template header (replace it)
 */

class MainPresenter(
        private val context: Context,
        private val view: MainView
) : BasePresenter() {

    companion object {
        private const val TIK_TAK_INTERVAL_SECONDS: Long = 1
    }

    private var tikTakDisposable: Disposable? = null
    private var tikTakSeconds: Int = 0

    fun onCreate() {
        runTikTak()
    }

    fun onDestroy() {
        tikTakDisposable?.dispose()
    }

    fun onOpenListClick() {
        view.openListActivity(tikTakSeconds)
    }

    private fun runTikTak() {
        tikTakSeconds = 0
        showTikTak()
        tikTakDisposable?.dispose()
        tikTakDisposable = Observable.interval(TIK_TAK_INTERVAL_SECONDS, TimeUnit.SECONDS)
                .applySchedulers()
                .subscribe({ seconds ->
                    tikTakSeconds = seconds.toInt()
                    showTikTak()
                }, Timber::e)
    }

    private fun showTikTak() {
        view.showTikTakTimer(context.getString(R.string.tik_tak, tikTakSeconds))
    }
}
