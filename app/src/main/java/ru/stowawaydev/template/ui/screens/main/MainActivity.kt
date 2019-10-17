package ru.stowawaydev.template.ui.screens.main

import android.os.Bundle
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ru.stowawaydev.template.R
import ru.stowawaydev.template.ui.screens.BaseActivity

/**
 * template header (replace it)
 */

class MainActivity : BaseActivity(), MainView {

    override fun depsModule(): Kodein.Module = mainModule(this)

    private val presenter: MainPresenter by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDestroy()
    }
}
