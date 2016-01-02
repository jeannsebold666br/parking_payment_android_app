package paymentcom.parking.jorge.parkingpayment.View;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import paymentcom.parking.jorge.parkingpayment.Controller.Adapters.PaymentsList.PaymentListAdapter;
import paymentcom.parking.jorge.parkingpayment.Model.Ticket.TicketResponse;
import paymentcom.parking.jorge.parkingpayment.R;
import paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Base.ServiceGenerator;
import paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Requests.Ticket.TicketRequest;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ListTicketsActivity extends AppCompatActivity {

    @Bind(R.id.rv_ticket_list)
    RecyclerView rvTicketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tickets);
        ButterKnife.bind(this);
        receiveAllTickets();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_list_tickets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }else if (id == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadRecycleView(List<TicketResponse> list){

        RecyclerView.Adapter adapater = new PaymentListAdapter(this,list);
        rvTicketList.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvTicketList.setAdapter(adapater);
    }

    private void receiveAllTickets(){
        TicketRequest request= (TicketRequest) ServiceGenerator
                .createService(TicketRequest.class, getApplicationContext());
        Call<List<TicketResponse>> call= request.allTickets();

        final AlertDialog dialog = new SpotsDialog(this);
        dialog.show();

        call.enqueue(new Callback<List<TicketResponse>>() {
            @Override
            public void onResponse(Response<List<TicketResponse>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    List<TicketResponse> ticketResponses= response.body();
                    loadRecycleView(ticketResponses);
                    dialog.dismiss();

                }else{
                    Toast.makeText(getApplicationContext(),
                            getResources().getText(R.string.message_wrong_bad_request),
                            Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),
                        getResources().getText(R.string.message_wrong_bad_request),
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
