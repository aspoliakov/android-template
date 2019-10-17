package ru.stowawaydev.template.ui.screens.template_list

import android.os.Bundle
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ru.stowawaydev.template.R
import ru.stowawaydev.template.ui.screens.BaseActivity

/**
 * template header (replace it)
 */

class ListActivitty : BaseActivity(), ListView {

    override fun depsModule(): Kodein.Module = mainModule(this)

    private val presenter: ListPresenter by instance()

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
