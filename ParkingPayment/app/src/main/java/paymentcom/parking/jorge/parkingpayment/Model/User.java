package paymentcom.parking.jorge.parkingpayment.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgehsrocha on 12/5/15.
 */
public class User {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("identifier")
    private String identifier;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("email")
    private String email;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getEmail() {
        return email;
    }
}
