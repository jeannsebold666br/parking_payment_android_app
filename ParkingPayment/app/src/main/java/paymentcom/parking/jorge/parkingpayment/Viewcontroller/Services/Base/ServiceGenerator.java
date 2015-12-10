package paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.security.PublicKey;

import paymentcom.parking.jorge.parkingpayment.Model.ApiConstants.ApiConstants;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {

    public static final String API_BASE_PATH = ApiConstants.BASE_URL;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Retrofit.Builder builder= new Retrofit.Builder();
    public static  String token;

    public static <S> S createService(Class<S> serviceClass, Context context){

        //Added HTTP log
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences("pkapi", Context.MODE_PRIVATE);
            token= preferences.getString("token","");
            Log.i("TOken",token);
            if (!token.equals("")){
                okHttpClient.interceptors().add(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("token", token)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                });
            }
        }

        builder.baseUrl(API_BASE_PATH).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit= builder.client(okHttpClient).build();

        return retrofit.create(serviceClass);
    }


}