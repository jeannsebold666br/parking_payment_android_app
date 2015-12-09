package paymentcom.parking.jorge.parkingpayment.Model.Ticket;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by jorgehsrocha on 12/8/15.
 */
public class TicketResponse {
//    {
//        "id": 1,
//            "vehicle_id": 2,
//            "user_id": 4,
//            "created_at": "2015-12-09T00:54:35.829Z",
//            "updated_at": "2015-12-09T00:55:02.074Z",
//            "paid": true,
//            "price": 45.99
//    }
    @SerializedName("id")
    private Integer id;
    @SerializedName("vehicle_id")
    private Integer vehicle_id;
    @SerializedName("created_at")
    private Date created_at;
    @SerializedName("updated_at")
    private Date updated_at;
    @SerializedName("price")
    private Float price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
