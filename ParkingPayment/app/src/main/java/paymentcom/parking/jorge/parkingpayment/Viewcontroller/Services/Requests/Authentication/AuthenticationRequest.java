package paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Requests.Authentication;

import paymentcom.parking.jorge.parkingpayment.Model.Authentication.SignIn;
import paymentcom.parking.jorge.parkingpayment.Model.Authentication.SignInResponse;
import paymentcom.parking.jorge.parkingpayment.Model.Authentication.SignUpResponse;
import paymentcom.parking.jorge.parkingpayment.Model.Authentication.Signup;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.POST;

/**
 * Created by jorgehsrocha on 12/5/15.
 */
public interface AuthenticationRequest {
    @POST("/api/v1/user/signin")
    Call<SignInResponse> signin(@Body SignIn signIn);

    @POST("/api/v1/user/signup")
    Call<SignUpResponse> signup(@Body Signup signup);

    @DELETE("api/v1/user/logout")
    Call logout();
}
