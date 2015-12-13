package paymentcom.parking.jorge.parkingpayment.Model.ApiConstants;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jorgehsrocha on 12/5/15.
 */
public final class ApiConstants {
    public static String BASE_URL = "http://pkapi.meuvalor.com";

    public static  String token;

    public static String getToken(Context context){
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences("pkapi", Context.MODE_PRIVATE);
            return preferences.getString("token","");
        }else{
            return "";
        }
    }




}