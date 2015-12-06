package paymentcom.parking.jorge.parkingpayment.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import paymentcom.parking.jorge.parkingpayment.R;

public class DashboardActivity extends AppCompatActivity {

    @Bind(R.id.tv_veichile_name)
    TextView tvVeichileName;

    @Bind(R.id.tv_veichile_identifier)
    TextView tvVeichileIdentifier;

    @Bind(R.id.bt_payTicket)
    Button btPayTicket;

    @Bind(R.id.bt_listPayments)
    Button btListPayments;

    @Bind(R.id.iv_profile_image)
    CircleImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        showAuthDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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

    /* Listeners */
    @OnClick(R.id.bt_payTicket)
    public void payTicketListener(){
        Intent intentTimerCountActivity = new Intent(this, TimerCountActivity.class);
        startActivity(intentTimerCountActivity);
    }

    @OnClick(R.id.bt_listPayments)
    public void listPaymentsListener(){
        Intent intentPayments = new Intent(this,  ListTicketsActivity.class);
        startActivity(intentPayments);
    }

    public void showAuthDialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View autheticationView = layoutInflater.inflate(R.layout.layout_dialog_register, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(autheticationView);
        AlertDialog alertDialog = builder.create();

        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();
    }
}
