package paymentcom.parking.jorge.parkingpayment.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.shamanland.fab.FloatingActionButton;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import paymentcom.parking.jorge.parkingpayment.Model.Ticket.TicketPay;
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
    Chronometer tvTimerConter;

    @Bind(R.id.bt_fa_pay_parking)
    FloatingActionButton btConfirmParking;

    @Bind(R.id.tv_value_ticket_price)
    TextView tvPrice;

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
//        getMenuInflater().inflate(R.menu.menu_timer_count, menu);
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

                Log.i("Ticket - AR", "Timestamp :=>" + ticketResponse.getCreated_at() + " Id :=>" + ticketResponse.getId());

                String hashkey= MD5(ticketResponse.getCreated_at().toString()
                        +ticketResponse.getId());

                String contentHash= MD5(contents);

                Log.i("Result", "Hash :=>"+hashkey+" content hash :=>"+contentHash);

                if (contentHash.equals(hashkey)){
                    final double tickecPrice= calculateTicketPrice(ticketResponse);

                    Locale locale = new Locale("pt", "BR");
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

                    new AlertDialog.Builder(this)
                            .setTitle("Confirmar pagamento")
                            .setMessage("Deseja pagar o valor de "+formatter.format(tickecPrice)+" para o ticket ?")
                            .setPositiveButton("Pagar agora", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TicketRequest request = ServiceGenerator
                                            .createService(TicketRequest.class,
                                                    getApplicationContext());
                                    TicketPay ticketPay = new TicketPay();
                                    ticketPay.setPrice(tickecPrice);
                                    ticketPay.setTicketId(ticketResponse.getId());
                                    ticketPay.setVehicleId(ticketResponse.getVehicle_id());

                                    Call<TicketResponse> call = request.payTicket(ticketPay);

                                    final AlertDialog alertDialog = new SpotsDialog(TimerCountActivity.this);
                                    alertDialog.show();

                                    call.enqueue(new Callback<TicketResponse>() {
                                        @Override
                                        public void onResponse(Response<TicketResponse> response, Retrofit retrofit) {
                                            if (response.isSuccess()) {

                                                TicketResponse ticket = response.body();

                                                if (ticket.getPaid()) {
                                                    Toast.makeText(getApplicationContext(),
                                                            getResources().getText(R.string.message_ticket_paid),
                                                            Toast.LENGTH_SHORT).show();
                                                    alertDialog.dismiss();
                                                    finish();
                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            getResources().getText(R.string.message_wrong_bad_request),
                                                            Toast.LENGTH_SHORT).show();
                                                    alertDialog.dismiss();
                                                }


                                            } else {
                                                Toast.makeText(getApplicationContext(),
                                                        getResources().getText(R.string.message_wrong_bad_request),
                                                        Toast.LENGTH_SHORT).show();
                                                alertDialog.dismiss();
                                            }

                                            alertDialog.dismiss();
                                        }

                                        @Override
                                        public void onFailure(Throwable t) {
                                            Toast.makeText(getApplicationContext(),
                                                    getResources().getText(R.string.message_wrong_bad_request),
                                                    Toast.LENGTH_SHORT).show();
                                            alertDialog.dismiss();
                                        }
                                    });


                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }

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
                    dialog.dismiss();
                    for (TicketResponse ticket : ticketResponses){
                        if (ticket.getPaid() == false) {
                            ticketResponse = ticket;
                            activeCounter();
                        }
                    }

                    if (ticketResponse == null){
                        finish();
                        Toast.makeText(getApplicationContext(),
                                getResources().getText(R.string.message_empty_tickets),
                                Toast.LENGTH_SHORT).show();
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

    public void activeCounter(){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date;
            try {
                Date dateNow= new Date();
                date = df.parse(ticketResponse.getCreated_at());
                long lastSuccess = date.getTime();
                long elapsedRealtimeOffset = System.currentTimeMillis() - SystemClock.elapsedRealtime();
                tvTimerConter.setBase(lastSuccess - elapsedRealtimeOffset);
                tvTimerConter.start();
                double price = calculateTicketPrice(ticketResponse);

                Locale locale = new Locale("pt", "BR");
                NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

                tvPrice.setText(String.valueOf(formatter.format(price)));

            } catch (ParseException e) {
                e.printStackTrace();
            }
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    private double calculateTicketPrice(TicketResponse t){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date dateNow= new Date();
        double price=0;

        try{

            Date date = df.parse(t.getCreated_at());
            //in milliseconds
            long diff = dateNow.getTime() - date.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            price= diffHours*3.59;

        }catch (ParseException e){
            Log.e("Parse exception", "Exception ->"+e.getMessage());
        }

        return price;

    }
}
