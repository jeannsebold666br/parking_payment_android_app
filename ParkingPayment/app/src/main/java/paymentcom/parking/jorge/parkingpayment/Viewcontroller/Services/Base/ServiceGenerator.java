package paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Base;

import com.squareup.okhttp.OkHttpClient;

import paymentcom.parking.jorge.parkingpayment.Model.ApiConstants.ApiConstants;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {

    public static final String API_BASE_PATH = ApiConstants.BASE_URL;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Retrofit.Builder builder= new Retrofit.Builder()
            .baseUrl(API_BASE_PATH)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass){
        Retrofit retrofit= builder.client(okHttpClient).build();
        return retrofit.create(serviceClass);
    }


}