package paymentcom.parking.jorge.parkingpayment.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.shamanland.fab.FloatingActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import paymentcom.parking.jorge.parkingpayment.R;

public class TimerCountActivity extends AppCompatActivity {

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
        Intent pinChecker = new Intent(this, PinCheckerActivity.class);
        startActivity(pinChecker);
    }
}
