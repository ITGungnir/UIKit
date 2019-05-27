package my.itgungnir.ui.easy_adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.bind(
    manager: RecyclerView.LayoutManager = LinearLayoutManager(context),
    diffAnalyzer: Differ? = null
): EasyAdapter {
    layoutManager = manager
    return EasyAdapter(recyclerView = this, diffAnalyzer = diffAnalyzer)
}

@Suppress("UNCHECKED_CAST")
fun <T : ListItem> RecyclerView.update(items: List<T>) {
    (adapter as? EasyAdapter)?.update(items)
}
