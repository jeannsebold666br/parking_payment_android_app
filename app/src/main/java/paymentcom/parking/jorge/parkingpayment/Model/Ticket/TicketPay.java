package paymentcom.parking.jorge.parkingpayment.Model.Ticket;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgehsrocha on 12/12/15.
 */
public class TicketPay {
    @SerializedName("ticket_id")
    private Integer ticketId;
    @SerializedName("vehicle_id")
    private Integer vehicleId;
    @SerializedName("price")
    private Double price;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
