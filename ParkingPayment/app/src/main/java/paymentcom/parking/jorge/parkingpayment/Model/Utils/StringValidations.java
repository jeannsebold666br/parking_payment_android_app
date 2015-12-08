package paymentcom.parking.jorge.parkingpayment.Model.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jorgehsrocha on 12/6/15.
 */
public class StringValidations {

    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
