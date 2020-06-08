import com.example.projectku.model.ResponseCreatePost
import com.example.projectku.model.ResponseProjectsGET
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


class ApiClient {
    val BASE_URL = "http://192.168.1.6:9155"

    fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun getService() = getRetrofit().create(ApiService::class.java)

}

interface ApiService {
    @FormUrlEncoded
    @POST("projects/")
    fun createProject(
        @Field("NamaProject") namaproject: String?,
        @Field("Owner") owner: String?,
        @Field("OwnerPC") ownercontact: String?,
        @Field("Fitur") fiturproject: String?,
        @Field("Deskripsi") deskripsi: String?,
        @Field("Fee") fee: String?,
        @Field("WaktuMulai") waktumulai: String?,
        @Field("WaktuBerakhir") waktuberakhir: String?,
        @Field("StatusProject") statusproject: String?
    ): Call<ResponseCreatePost?>?

    @GET("projects/")
    fun getListProject(): Call<ResponseProjectsGET?>?
}