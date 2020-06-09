package com.example.projectku.adapter

import ApiClient
import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectku.R
import com.example.projectku.model.DataItem
import com.example.projectku.model.ResponseProjectGET
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val waktuBerakhir = project?.waktuBerakhir?.split("T")

        holder.tvNamaProject.text = project?.namaProject ?: toString()
        holder.tvOwner.text = project?.owner ?: toString()
        holder.tvOwnerPC.text = project?.ownerPC ?: toString()
        holder.tvWaktuAkhir.text = waktuBerakhir?.get(0) ?: toString()

        holder.itemView.setOnClickListener {
            val dialog = Dialog(view.context)
            dialog.setContentView(R.layout.fragment_home)

            val lp: WindowManager.LayoutParams = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT

            val edtNama: EditText? = dialog.findViewById(R.id.edtnamaproject)
            edtNama?.setText(project?.namaProject)
            val edtOwner: EditText? = dialog.findViewById(R.id.edtownername)
            edtOwner?.setText(project?.owner)
            val edtOwnerContact: EditText? = dialog.findViewById(R.id.edtownerpc)
            edtOwnerContact?.setText(project?.ownerPC)
            val edtFitur: EditText? = dialog.findViewById(R.id.edtfitur)
            edtFitur?.setText(project?.fitur)
            val edtFee: EditText? = dialog.findViewById(R.id.edtfee)
            edtFee?.setText(project?.fee)
            val spinStatus: Spinner? = dialog.findViewById(R.id.spinnerstatus)
            project?.statusProject?.toInt()?.let { it1 -> spinStatus?.setSelection(it1) }
            val waktuMulai: EditText? = dialog.findViewById(R.id.edtwaktumulai)
            waktuMulai?.setText(project?.waktuMulai)
            val waktuBerakhir: EditText? = dialog.findViewById(R.id.edtwaktuberakhir)
            waktuBerakhir?.setText(project?.waktuBerakhir)
            val deskripsi: EditText? = dialog.findViewById(R.id.edtdeskripsi)
            deskripsi?.setText(project?.deskripsi)
            val btnUpdate: Button? = dialog.findViewById(R.id.btntambahkan)
            btnUpdate?.setText("Update Project")
            btnUpdate?.setOnClickListener {

            }


            dialog.show()
            dialog.window?.attributes = lp
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNamaProject: TextView = itemView.findViewById(R.id.tvnamaproject)
        var tvOwner: TextView = itemView.findViewById(R.id.tvownerproject)
        var tvOwnerPC: TextView = itemView.findViewById(R.id.tvownerpc)
        var tvWaktuAkhir: TextView = itemView.findViewById(R.id.tvwaktuberakhir)
    }
}