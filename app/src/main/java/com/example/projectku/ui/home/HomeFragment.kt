package com.example.projectku.ui.home

import ApiClient
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projectku.R
import com.example.projectku.model.ResultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var root: View
    private val edtNama by lazy { root.findViewById<EditText>(R.id.edtnamaproject) }
    private val edtOwner by lazy { root.findViewById<EditText>(R.id.edtownername) }
    private val edtOwnerContact by lazy { root.findViewById<EditText>(R.id.edtownerpc) }
    private val edtFitur by lazy { root.findViewById<EditText>(R.id.edtfitur) }
    private val edtFee by lazy { root.findViewById<EditText>(R.id.edtfee) }
    private val spinnerStatus by lazy { root.findViewById<Spinner>(R.id.spinnerstatus) }
    private val edtMulai by lazy { root.findViewById<EditText>(R.id.edtwaktumulai) }
    private val edtBerakhir by lazy { root.findViewById<EditText>(R.id.edtwaktuberakhir) }
    private val edtDeskripsi by lazy { root.findViewById<EditText>(R.id.edtdeskripsi) }
    private val btnTambah by lazy { root.findViewById<Button>(R.id.btntambahkan) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        setTanggal(edtMulai)
        setTanggal(edtBerakhir)

        btnTambah.setOnClickListener {
            tambahProject()
        }

        return root
    }

    private fun tambahProject() {
        val progress = ProgressDialog(root.context)
        progress.setCancelable(false)
        progress.setMessage("Loading ...")
        progress.show()

        if (edtNama.length() == 0 || edtOwner.length() == 0 || edtOwnerContact.length() == 0 ||
            edtFitur.length() == 0 || edtFee.length() == 0 || edtMulai.length() == 0 ||
            edtBerakhir.length() == 0 || edtDeskripsi.length() == 0
        ) {
            Toast.makeText(root.context, "Tidak boleh kosong", Toast.LENGTH_SHORT).show()
        } else {
            ApiClient().getService().tambahproject(
                edtNama.text.toString(), edtOwner.text.toString(),
                edtOwnerContact.text.toString(), edtFitur.text.toString(), edtFee.text.toString(),
                spinnerStatus.selectedItemPosition.toString(), edtMulai.text.toString(),
                edtBerakhir.text.toString(), edtDeskripsi.text.toString()
            )!!.enqueue(object : Callback<ResultResponse?> {
                override fun onFailure(call: Call<ResultResponse?>, t: Throwable) {
                    progress.dismiss()
                    Toast.makeText(root.context, "Gagal Ditambahkan", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResultResponse?>,
                    response: Response<ResultResponse?>
                ) {
                    progress.dismiss()
                    Toast.makeText(root.context, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setTanggal(edtText: EditText) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        edtText.setOnClickListener {
            val dpd = DatePickerDialog(
                root.context,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    edtText.setText(getString(R.string.templatewaktu, year, month, dayOfMonth))
                }, year, month, day
            )
            dpd.show()
        }
    }
}
