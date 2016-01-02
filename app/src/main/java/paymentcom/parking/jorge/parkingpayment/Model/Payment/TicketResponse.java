package paymentcom.parking.jorge.parkingpayment.Model.Payment;

import java.util.Date;

/**
 * Created by jorge on 30/09/15.
 */
public class TicketResponse {

    private Date date;
    private Double price;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
