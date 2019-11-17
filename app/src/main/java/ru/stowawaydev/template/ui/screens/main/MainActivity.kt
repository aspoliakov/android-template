package ru.stowawaydev.template.ui.screens.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ru.stowawaydev.template.R
import ru.stowawaydev.template.ui.screens.BaseActivity
import ru.stowawaydev.template.ui.screens.template_list.ListActivity

/**
 * template header (replace it)
 */

class MainActivity : BaseActivity(), MainView {

    override fun depsModule(): Kodein.Module = mainModule(this)

    private val presenter: MainPresenter by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListeners()
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDestroy()
    }

    override fun openListActivity(listSize: Int) {
        ListActivity.start(this, listSize)
    }

    override fun showTikTakTimer(time: String) {
        tv_tik_tak.text = time
    }

    private fun setupListeners() {
        btn_open_list.setOnClickListener { presenter.onOpenListClick() }
    }
}
