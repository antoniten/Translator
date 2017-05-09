package program.translator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TranslateApi {
    @GET("translate")
    Call<TranslateResponse> translate(@Query("key") String apiKey, @Query("lang") String lang, @Query("text") String text);

}