package com.moanes.nytimes.data.models


import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("copyright")
    var copyright: String,
    @SerializedName("num_results")
    var numResults: Int,
    @SerializedName("results")
    var results: List<Article>,
    @SerializedName("status")
    var status: String
)