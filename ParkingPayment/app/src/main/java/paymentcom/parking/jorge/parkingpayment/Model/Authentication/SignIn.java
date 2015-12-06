package paymentcom.parking.jorge.parkingpayment.Model.Authentication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgehsrocha on 12/5/15.
 */
public class SignIn {
    @SerializedName("email")
    private String email="";
    @SerializedName("Password")
    private String password="";

    public SignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
