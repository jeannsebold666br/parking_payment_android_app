package paymentcom.parking.jorge.parkingpayment.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import paymentcom.parking.jorge.parkingpayment.Model.Authentication.SignIn;
import paymentcom.parking.jorge.parkingpayment.Model.Authentication.SignInResponse;
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
public class AuthenticationFragment extends Fragment {

    @Bind(R.id.et_email)
    EditText etEmail;

    @Bind(R.id.et_password)
    EditText etPassword;

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
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authentication, container, false);
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

        SignIn signIn= new SignIn("jhrocha.sousa@gmail.com","12345678");
        AuthenticationRequest request = (AuthenticationRequest) ServiceGenerator.createService(AuthenticationRequest.class);
        Call<SignInResponse> call= request.signin(signIn);

        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Response<SignInResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    SignInResponse r = response.body();
                    Log.i("Success - onResponse", r.token);
                } else {
                    Log.i("Fail - onResponse", response.message() + " - Code: " + response.code() + "" +
                            " " + retrofit.baseUrl().url() + "");
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("onFailure", t.getMessage());
            }
        });


    }

}
