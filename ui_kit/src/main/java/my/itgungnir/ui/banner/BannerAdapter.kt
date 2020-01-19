package my.itgungnir.ui.banner

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BannerAdapter(
    private val layoutId: Int,
    private val isInfiniteScroll: Boolean,
    private val render: (position: Int, view: View, data: Any) -> Unit,
    private val onClick: (position: Int, data: Any) -> Unit
) : RecyclerView.Adapter<BannerAdapter.VH>() {

    @SuppressLint("DiffUtilEquals")
    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
    })

    private val items
        get() = differ.currentList

    @SuppressLint("CheckResult")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val vh = VH(
            LayoutInflater.from(parent.context).inflate(
                layoutId,
                parent,
                false
            )
        )

        vh.itemView.setOnClickListener {
            val realPosition = calculateIndex(vh.adapterPosition, items.size, isInfiniteScroll)
            onClick.invoke(realPosition, items[vh.adapterPosition])
        }

        return vh
    }

    fun update(newItems: List<Any>) {
        differ.submitList(newItems)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val realPosition = calculateIndex(position, items.size, isInfiniteScroll)
        render.invoke(realPosition, holder.itemView, items[position])
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
}