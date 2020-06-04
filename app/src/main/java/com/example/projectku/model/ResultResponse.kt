package com.example.projectku.model

import com.google.gson.annotations.SerializedName

data class ResultResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
