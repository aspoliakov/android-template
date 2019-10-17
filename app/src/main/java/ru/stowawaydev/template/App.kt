package ru.stowawaydev.template

import android.app.Application
import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import ru.stowawaydev.template.api.apiModule
import ru.stowawaydev.template.data.dataModule
import ru.stowawaydev.template.di.appModule
import ru.stowawaydev.template.domain.interactorsModule
import timber.log.Timber

/**
 * template header (replace it)
 */

class App : Application(), KodeinAware {

    override val kodein = Kodein {
        bind<Context>() with singleton { applicationContext }
        import(appModule())
        import(dataModule())
        import(interactorsModule())
        import(apiModule())
    }

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
