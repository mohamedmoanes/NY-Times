package com.moanes.nytimes.data.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    @SerializedName("abstract")
    var `abstract`: String="",
    @SerializedName("adx_keywords")
    var adxKeywords: String="",
    @SerializedName("asset_id")
    var assetId: Long=0,
    @SerializedName("byline")
    var byline: String="",
    @SerializedName("des_facet")
    var desFacet: List<String> = ArrayList<String>(),
    @SerializedName("eta_id")
    var etaId: Int=0,
    @SerializedName("geo_facet")
    var geoFacet: List<String> = ArrayList<String>(),
    @SerializedName("id")
    var id: Long= 0 ,
    @SerializedName("media")
    var media: List<Media> = ArrayList<Media>(),
    @SerializedName("nytdsection")
    var nytdsection: String="",
    @SerializedName("org_facet")
    var orgFacet: List<String> = ArrayList<String>(),
    @SerializedName("per_facet")
    var perFacet: List<String> = ArrayList<String>(),
    @SerializedName("published_date")
    var publishedDate: String="",
    @SerializedName("section")
    var section: String="",
    @SerializedName("source")
    var source: String="",
    @SerializedName("subsection")
    var subsection: String="",
    @SerializedName("title")
    var title: String="",
    @SerializedName("type")
    var type: String="",
    @SerializedName("updated")
    var updated: String="",
    @SerializedName("uri")
    var uri: String="",
    @SerializedName("url")
    var url: String=""
) : Parcelable {
    @Parcelize
    data class Media(
        @SerializedName("approved_for_syndication")
        var approvedForSyndication: Int=0,
        @SerializedName("caption")
        var caption: String="",
        @SerializedName("copyright")
        var copyright: String="",
        @SerializedName("media-metadata")
        var mediaMetadata: List<MediaMetadata> = ArrayList<MediaMetadata>(),
        @SerializedName("subtype")
        var subtype: String="",
        @SerializedName("type")
        var type: String=""
    ) : Parcelable {
        @Parcelize
        data class MediaMetadata(
            @SerializedName("format")
            var format: String="",
            @SerializedName("height")
            var height: Int=0,
            @SerializedName("url")
            var url: String="",
            @SerializedName("width")
            var width: Int
        ) : Parcelable
    }
}