package paymentcom.parking.jorge.parkingpayment.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import paymentcom.parking.jorge.parkingpayment.R;

public class PinCheckerActivity extends AppCompatActivity {

    @Bind(R.id.et_pin1)
    EditText etPin1;

    @Bind(R.id.et_pin2)
    EditText etPin2;

    @Bind(R.id.et_pin3)
    EditText etPin3;

    @Bind(R.id.et_pin4)
    EditText etPin4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_checker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        etPin1.setTag(0);
        etPin2.setTag(1);
        etPin3.setTag(2);
        etPin4.setTag(3);

        setEditTextListener(etPin1);
        setEditTextListener(etPin2);
        setEditTextListener(etPin3);
        setEditTextListener(etPin4);

        etPin1.requestFocus();
    }

    public void setEditTextListener(EditText editText){

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_DONE){
                    handled = true;
                    int tag = (int)v.getTag();
                    switch (tag){
                        case 0:
                            etPin2.requestFocus();
                            break;
                        case 1:
                            etPin3.requestFocus();
                            break;
                        case 3:

                            if ( TextUtils.isEmpty(etPin1.getText().toString()) ||
                                    TextUtils.isEmpty(etPin2.getText().toString()) ||
                                    TextUtils.isEmpty(etPin3.getText().toString()) ||
                                    TextUtils.isEmpty(etPin3.getText().toString()) ){
                                etPin1.requestFocus();
                            }else {
                                callIntent();
                            }

                            break;
                        default:
                            etPin1.requestFocus();
                            break;
                    }
                }

                return handled;
            }
        });

    }

    private void callIntent(){
        Intent congrats = new Intent(this, PaymentsCongratsActivity.class);
        startActivity(congrats);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pin_checker, menu);
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
}
