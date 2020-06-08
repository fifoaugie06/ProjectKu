package com.example.projectku.model

import com.google.gson.annotations.SerializedName

data class ResponseProjectsGET(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("data_count")
	val dataCount: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItem(

	@field:SerializedName("NamaProject")
	val namaProject: String? = null,

	@field:SerializedName("Owner")
	val owner: String? = null,

	@field:SerializedName("WaktuMulai")
	val waktuMulai: String? = null,

	@field:SerializedName("Fitur")
	val fitur: String? = null,

	@field:SerializedName("Fee")
	val fee: String? = null,

	@field:SerializedName("StatusProject")
	val statusProject: String? = null,

	@field:SerializedName("Deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("WaktuBerakhir")
	val waktuBerakhir: String? = null,

	@field:SerializedName("IdProject")
	val idProject: Int? = null,

	@field:SerializedName("OwnerPC")
	val ownerPC: String? = null
)
