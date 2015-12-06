package paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Base;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import paymentcom.parking.jorge.parkingpayment.Model.ApiConstants.ApiConstants;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {

    public static final String API_BASE_PATH = ApiConstants.BASE_URL;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static Retrofit.Builder builder= new Retrofit.Builder();

    public static <S> S createService(Class<S> serviceClass){

        //Added HTTP log
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        okHttpClient.interceptors().add(logging);
        builder.baseUrl(API_BASE_PATH).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit= builder.client(okHttpClient).build();
        return retrofit.create(serviceClass);
    }


}