package com.example.projectku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectku.R
import com.example.projectku.model.DataItem

class ProjectsAdapter(val projects: List<DataItem?>?) :
    RecyclerView.Adapter<ProjectsAdapter.ListViewHolder>() {
    lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_data, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (projects != null) {
            return projects.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val project = projects?.get(position)
        var waktuBerakhir = project?.waktuBerakhir?.split("T")

        holder.tvNamaProject.text = project?.namaProject ?: toString()
        holder.tvOwner.text = project?.owner ?: toString()
        holder.tvOwnerPC.text = project?.ownerPC ?: toString()
        holder.tvWaktuAkhir.text = waktuBerakhir?.get(0) ?: toString()
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNamaProject: TextView = itemView.findViewById(R.id.tvnamaproject)
        var tvOwner: TextView = itemView.findViewById(R.id.tvownerproject)
        var tvOwnerPC: TextView = itemView.findViewById(R.id.tvownerpc)
        var tvWaktuAkhir: TextView = itemView.findViewById(R.id.tvwaktuberakhir)
    }
}