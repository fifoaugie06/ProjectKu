import com.example.projectku.model.ResultResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

class ApiClient {
    val BASE_URL = "http://192.168.1.6/projectKu/"

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
    @POST("tambahproject.php")
    fun tambahproject(
        @Field("namaproject") namaproject: String?,
        @Field("owner") owner: String?,
        @Field("ownercontact") ownercontact: String?,
        @Field("fiturproject") fiturproject: String?,
        @Field("fee") fee: String?,
        @Field("statusproject") statusproject: String?,
        @Field("waktumulai") waktumulai: String?,
        @Field("waktuberakhir") waktuberakhir: String?,
        @Field("deskripsi") deskripsi: String?
    ): Call<ResultResponse?>?
}