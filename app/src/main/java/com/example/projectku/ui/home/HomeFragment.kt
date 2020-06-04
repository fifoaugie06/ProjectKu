package com.example.projectku.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projectku.R

class HomeFragment : Fragment() {
    private lateinit var root: View
    val edtNama by lazy { root.findViewById<EditText>(R.id.edtnamaproject) }
    val edtOwner by lazy { root.findViewById<EditText>(R.id.edtownername) }
    val edtOwnerContact by lazy { root.findViewById<EditText>(R.id.edtownerpc) }
    val edtFitur by lazy { root.findViewById<EditText>(R.id.edtfitur) }
    val btnTambah by lazy { root.findViewById<Button>(R.id.btntambahkan) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        btnTambah.setOnClickListener {
            Toast.makeText(root.context, edtNama.text.toString(), Toast.LENGTH_SHORT).show()
        }

        return root
    }
}
