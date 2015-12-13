package paymentcom.parking.jorge.parkingpayment.Model.Authentication;

import com.google.gson.annotations.SerializedName;

import paymentcom.parking.jorge.parkingpayment.Model.User;

/**
 * Created by jorgehsrocha on 12/5/15.
 */
public class SignUpResponse {
    @SerializedName("user")
    User user;
    @SerializedName("token")
    String token;
}
