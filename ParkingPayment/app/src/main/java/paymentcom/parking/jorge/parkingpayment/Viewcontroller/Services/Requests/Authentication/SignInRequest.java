package paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Requests.Authentication;

import paymentcom.parking.jorge.parkingpayment.Model.Authentication.SignIn;
import paymentcom.parking.jorge.parkingpayment.Model.Authentication.SignInResponse;
import retrofit.Call;
import retrofit.http.POST;

/**
 * Created by jorgehsrocha on 12/5/15.
 */
public interface SignInRequest {
    @POST("/user/signin")
    Call<SignInResponse> signin();
}
