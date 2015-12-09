package paymentcom.parking.jorge.parkingpayment.View;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import paymentcom.parking.jorge.parkingpayment.Model.Authentication.SignIn;
import paymentcom.parking.jorge.parkingpayment.Model.Authentication.SignInResponse;
import paymentcom.parking.jorge.parkingpayment.Model.Utils.StringValidations;
import paymentcom.parking.jorge.parkingpayment.R;
import paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Base.ServiceGenerator;
import paymentcom.parking.jorge.parkingpayment.Viewcontroller.Services.Requests.Authentication.AuthenticationRequest;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AuthenticationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AuthenticationFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AuthenticationFragment extends DialogFragment {

    @Bind(R.id.et_email)
    EditText etEmail;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.bt_signin)
    Button btSignin;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static AuthenticationFragment newInstance(String param1, String param2) {
        AuthenticationFragment fragment = new AuthenticationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public AuthenticationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.i("OnCreate","");
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_authentication, container, false);
        ButterKnife.bind(this, v);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        addFocusChangeListener(etEmail);
        addFocusChangeListener(etPassword);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @OnClick(R.id.bt_signin)
    public void siginAuthListener(){

        String email= etEmail.getText().toString();
        String password= etPassword.getText().toString();
        SignIn signIn= new SignIn(email,password);

        AuthenticationRequest request = (AuthenticationRequest) ServiceGenerator
                .createService(AuthenticationRequest.class, null);
        Call<SignInResponse> call= request.signin(signIn);

        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();

        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Response<SignInResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    SignInResponse r = response.body();
                    SharedPreferences preferences= getActivity().getSharedPreferences("pkapi", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("token", r.token);
                    getDialog().dismiss();
                    editor.commit();
                }else{
                    Toast.makeText(getActivity(),
                            getResources().getText(R.string.message_wrong_credentials),
                            Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(),
                        getResources().getText(R.string.message_wrong_bad_request),
                        Toast.LENGTH_SHORT).show();;
                dialog.dismiss();
            }


        });


    }

    public void addFocusChangeListener(final EditText editText){
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(editText);
                }
            }
        });
    }

    private void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
