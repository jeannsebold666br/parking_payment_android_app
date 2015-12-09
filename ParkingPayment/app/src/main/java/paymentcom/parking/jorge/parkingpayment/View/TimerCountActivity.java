package paymentcom.parking.jorge.parkingpayment.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.shamanland.fab.FloatingActionButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import paymentcom.parking.jorge.parkingpayment.Model.Ticket.TicketResponse;
import paymentcom.parking.jorge.parkingpayment.R;
import paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Base.ServiceGenerator;
import paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Requests.Ticket.TicketRequest;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TimerCountActivity extends AppCompatActivity {

    TicketResponse ticketResponse;

    @Bind(R.id.tv_title_parking_timer_counter)
    TextView tvTileParkingTimerCounter;

    @Bind(R.id.tv_timer_counter)
    TextView tvTimerConter;

    @Bind(R.id.bt_fa_pay_parking)
    FloatingActionButton btConfirmParking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_count);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        receiveNoPaidTicket();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer_count, menu);
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
        }else if (id == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.bt_fa_pay_parking)
    public void pay(){

        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_FORMATS", "CODE_39," +
                "CODE_93," +
                "CODE_128," +
                "DATA_MATRIX," +
                "ITF," +
                "CODABAR," +
                "EAN_13," +
                "EAN_8," +
                "UPC_A" +
                ",QR_CODE");
        startActivityForResult(intent, 0);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {


                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                Intent pinChecker = new Intent(this, PinCheckerActivity.class);
                pinChecker.putExtra("BARCODE_CONTENT",contents);
                startActivity(pinChecker);

            } else if (resultCode == RESULT_CANCELED) {

                Log.i("App", "Scan unsuccessful");
            }
        }
    }

    public void receiveNoPaidTicket(){

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
                    for (TicketResponse ticket : ticketResponses){
                       ticketResponse = ticket;
                    }
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
