package paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Requests.Ticket;

import java.util.List;

import paymentcom.parking.jorge.parkingpayment.Model.Ticket.TicketPay;
import paymentcom.parking.jorge.parkingpayment.Model.Ticket.TicketResponse;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by jorgehsrocha on 12/8/15.
 */
public interface TicketRequest {

    @GET("/api/v1/ticket/all")
    Call<List<TicketResponse>> allTickets();

    @POST("/api/v1/ticket/pay")
    Call<TicketResponse> payTicket(@Body TicketPay ticketPay);
}
