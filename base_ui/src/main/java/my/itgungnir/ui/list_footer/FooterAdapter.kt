package my.itgungnir.ui.list_footer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.itgungnir.ui.R

class FooterAdapter(
    private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    private var status: FooterStatus.Status,
    private var renderPair: Pair<Int, (View, FooterStatus.Status) -> Unit>? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = if (adapter.itemCount > 0) {
        adapter.itemCount + 1
    } else {
        adapter.itemCount
    }

    override fun getItemViewType(position: Int): Int =
        if (position == itemCount - 1) Int.MAX_VALUE
        else adapter.getItemViewType(position)

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
        adapter.setHasStableIds(hasStableIds)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            Int.MAX_VALUE ->
                FooterViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        renderPair?.first ?: R.layout.view_uikit_list_footer, parent, false
                    )
                )
            else ->
                adapter.onCreateViewHolder(parent, viewType)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            Int.MAX_VALUE ->
                (holder as FooterViewHolder).applyStatus(status)
            else ->
                adapter.onBindViewHolder(holder, position)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) =
        when (payloads.isNullOrEmpty()) {
            true ->
                this.onBindViewHolder(holder, position)
            else ->
                adapter.onBindViewHolder(holder, position, payloads)
        }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when (holder.itemViewType) {
            Int.MAX_VALUE -> super.onViewRecycled(holder)
            else -> adapter.onViewRecycled(holder)
        }
    }

    fun notifyStatusChanged(status: FooterStatus.Status) {
        this.status = status
        this.notifyDataSetChanged()
    }

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun applyStatus(status: FooterStatus.Status) {
            if (renderPair == null) {
                itemView.findViewById<TextView>(R.id.title)?.apply {
                    text = when (status) {
                        FooterStatus.Status.PROGRESSING -> "正在加载..."
                        FooterStatus.Status.NO_MORE -> "没有更多数据了"
                        FooterStatus.Status.SUCCEED -> "加载成功"
                        FooterStatus.Status.FAILED -> "加载失败"
                    }
                }
            } else {
                renderPair?.second?.invoke(itemView, status)
            }
            itemView.invalidate()
        }
    }
}