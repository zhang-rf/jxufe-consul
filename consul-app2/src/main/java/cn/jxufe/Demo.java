package cn.jxufe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Demo {

    @GET("demo")
    Call<String> demo(@Query("n") int n);
}
