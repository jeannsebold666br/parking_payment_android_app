package paymentcom.parking.jorge.parkingpayment.Model.Ticket;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jorgehsrocha on 12/8/15.
 */
public class TicketResponse {

    @SerializedName("id")
    private Integer id;
    @SerializedName("vehicle_id")
    private Integer vehicle_id;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("price")
    private Float price;
    @SerializedName("paid")
    private Boolean paid;

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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Float getPrice() {

        if (!getPaid()){
            return calculatePrice();
        }else{
            return price;
        }

    }

    public void setPrice(Float price) {
        this.price = price;
    }


    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public float calculatePrice(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date dateNow= new Date();
        float price=0;

        try{

            Date date = df.parse(getCreated_at());
            //in milliseconds
            long diff = dateNow.getTime() - date.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            price= (float) (diffHours*3.59);

        }catch (ParseException e){
            Log.e("Parse exception", "Exception ->" + e.getMessage());
        }
        return price;
    }
}
