package ru.vaimon.vkservices.screens.main.adapters

import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.vaimon.vkservices.databinding.ItemVkserviceBinding
import ru.vaimon.vkservices.models.VKService

class VkServicesRecyclerViewAdapter(
    private var values: List<VKService> = listOf(),
    private val mListener: OnItemInteractionListener? = null
) : RecyclerView.Adapter<VkServicesRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener = View.OnClickListener {
        val item = it.tag as VKService
        mListener?.onItemInteraction(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVkserviceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.serviceName.text = item.name
        Picasso.with(holder.itemView.context)
            .load(item.iconUrl)
            .into(holder.serviceIcon);

        mListener?.also {
            with(holder.itemView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    fun replaceData(newData: List<VKService>){
        values = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: ItemVkserviceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val serviceName = binding.tvVkServiceName
        val serviceIcon = binding.ivVkServiceIcon
    }

    interface OnItemInteractionListener {
        fun onItemInteraction(item: VKService)
    }

}