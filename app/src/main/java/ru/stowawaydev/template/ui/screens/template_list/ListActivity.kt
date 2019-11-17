package ru.stowawaydev.template.ui.screens.template_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ru.stowawaydev.template.R
import ru.stowawaydev.template.data.presentation.ItemVO
import ru.stowawaydev.template.ui.screens.BaseActivity

/**
 * template header (replace it)
 */

class ListActivity : BaseActivity(), ListView, ListNavigation {

    companion object {
        private const val LIST_SIZE_EXTRA = "list_size_extra"

        fun start(context: Context, listSize: Int) {
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra(LIST_SIZE_EXTRA, listSize)
            context.startActivity(intent)
        }
    }

    override fun depsModule(): Kodein.Module = listModule(this)

    private val presenter: ListPresenter by instance()
    private lateinit var itemsAdapter: ListRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setupItemsRvAdapter()
        presenter.onCreate(intent.getIntExtra(LIST_SIZE_EXTRA, 0))
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDestroy()
    }

    override fun onItemClick(itemId: String) {
        presenter.onItemClick(itemId)
    }

    override fun showItems(items: List<ItemVO>) {
        itemsAdapter.setItems(items)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupItemsRvAdapter() {
        itemsAdapter = ListRvAdapter(this, this)
        rv_items.layoutManager = LinearLayoutManager(this)
        rv_items.adapter = itemsAdapter
    }
}
