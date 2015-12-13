package paymentcom.parking.jorge.parkingpayment.Controller.Adapters.PaymentsList;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

import paymentcom.parking.jorge.parkingpayment.Model.Ticket.TicketResponse;
import paymentcom.parking.jorge.parkingpayment.R;
import paymentcom.parking.jorge.parkingpayment.View.ListTicketsActivity;

/**
 * Created by jorge on 30/09/15.
 */
public class PaymentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<TicketResponse> ticketResponseList;

    public PaymentListAdapter(Context context, List<TicketResponse> ticketResponseList) {
        this.context = context;
        this.ticketResponseList = ticketResponseList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_ticket_payment,parent,false);
        ViewHolderPayment holderPayment = new ViewHolderPayment(v);

        return holderPayment;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderPayment holderPayment = (ViewHolderPayment) holder;
        TicketResponse ticketResponse = ticketResponseList.get(position);
        holderPayment.setPayment(ticketResponse);
    }

    @Override
    public int getItemCount() {
        return ticketResponseList.size();
    }

    public class ViewHolderPayment extends RecyclerView.ViewHolder{

        @Bind(R.id.tv_ticket_information)
        TextView tvTicketInformation;

        @Bind(R.id.tv_ticket_price)
        TextView tvTicketPrice;

        @Bind(R.id.tv_payment_status)
        TextView tvPaymentStatus;

        public ViewHolderPayment(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

       void  setPayment(TicketResponse ticketResponse){
           DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
           String status;

           if (ticketResponse.getPaid()){
               status = context.getResources().getString(R.string.title_tv_payment_status_paid);
               tvPaymentStatus.setTextColor(Color.parseColor("#077e03"));
           }else{
               status = context.getResources().getString(R.string.title_tv_payment_status_waiting);
               tvPaymentStatus.setTextColor(Color.RED);
           }

           tvPaymentStatus.setText(status);

           try {

               Date createAt = df.parse(ticketResponse.getCreated_at());
               df = new SimpleDateFormat("dd/MM/yyyy");
               tvTicketInformation.setText(String.valueOf(df.format(createAt)));

               Locale locale = new Locale("pt", "BR");
               NumberFormat priceFormatter = NumberFormat.getCurrencyInstance(locale);
               tvTicketPrice.setText(String.valueOf(
                       priceFormatter.format(ticketResponse.getPrice())));

           }catch (ParseException e){

               Log.e("Parse exception", e.getMessage());

           }


       }
    }
}
