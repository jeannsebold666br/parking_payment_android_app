package paymentcom.parking.jorge.parkingpayment.Model.Authentication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgehsrocha on 12/5/15.
 */
public class Signup {
    @SerializedName("email")
    private String email="";
    @SerializedName("Password")
    private String password="";
    @SerializedName("identifier")
    private String identifier="";
    @SerializedName("name")
    private String name="";

    public Signup(String email, String password, String identifier, String name) {
        this.email = email;
        this.password = password;
        this.identifier = identifier;
        this.name = name;
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
