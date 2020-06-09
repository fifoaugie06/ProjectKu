package com.example.projectku.ui.dashboard

import ApiClient
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectku.R
import com.example.projectku.adapter.ProjectsAdapter
import com.example.projectku.model.ResponseProjectsGET
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {
    lateinit var rvProjects: RecyclerView
    lateinit var viewOfLayout: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewOfLayout = inflater.inflate(R.layout.fragment_dashboard, container, false)
        rvProjects = viewOfLayout.findViewById(R.id.rvProjects)
        rvProjects.setHasFixedSize(true)
        rvProjects.layoutManager = LinearLayoutManager(viewOfLayout.context)

        LoadData()

        return viewOfLayout
    }

    private fun LoadData() {
        val progress = ProgressDialog(viewOfLayout.context)
        progress.setCancelable(false)
        progress.setMessage("Loading ...")
        progress.show()

        ApiClient().getService().getListProject()?.enqueue(object : Callback<ResponseProjectsGET?> {
            override fun onFailure(call: Call<ResponseProjectsGET?>, t: Throwable) {
                Toast.makeText(viewOfLayout.context, "Timeout", Toast.LENGTH_SHORT)
                    .show()
                progress.dismiss()
            }

            override fun onResponse(
                call: Call<ResponseProjectsGET?>,
                response: Response<ResponseProjectsGET?>
            ) {
                if (response.isSuccessful) {
                    rvProjects.adapter = ProjectsAdapter(response.body()?.data)
                    progress.dismiss()
                } else {
                    Toast.makeText(
                        viewOfLayout.context,
                        response.body()?.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    progress.dismiss()
                }
            }

        })
    }
}
