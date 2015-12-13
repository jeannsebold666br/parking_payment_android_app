package paymentcom.parking.jorge.parkingpayment.Controller.Adapters.PaymentsList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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

        public ViewHolderPayment(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

       void  setPayment(TicketResponse ticketResponse){

           tvTicketPrice.setText("R$ "+ ticketResponse.getPrice().toString());
           tvTicketInformation.setText(ticketResponse.getCreated_at());

       }
    }
}
