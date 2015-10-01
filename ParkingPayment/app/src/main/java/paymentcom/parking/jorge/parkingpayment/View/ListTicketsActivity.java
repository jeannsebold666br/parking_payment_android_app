package paymentcom.parking.jorge.parkingpayment.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import paymentcom.parking.jorge.parkingpayment.Controller.Adapters.PaymentsList.PaymentListAdapter;
import paymentcom.parking.jorge.parkingpayment.Model.Payment.Payment;
import paymentcom.parking.jorge.parkingpayment.R;

public class ListTicketsActivity extends AppCompatActivity {

    @Bind(R.id.rv_ticket_list)
    RecyclerView rvTicketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tickets);
        ButterKnife.bind(this);
        loadRecycleView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_tickets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadRecycleView(){

        Payment payment = new Payment();
        payment.setDate(new Date());
        payment.setPrice(45.40);


        List<Payment> payments = new ArrayList<>();
        for (int i=0; i<6; i++){
            payments.add(payment);
        }

        RecyclerView.Adapter adapater = new PaymentListAdapter(this,payments);
        rvTicketList.setAdapter(adapater);
    }
}
