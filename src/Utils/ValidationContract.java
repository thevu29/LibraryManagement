package Utils;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidationContract {
    public static class Validation {
        // RFC822
        private static Pattern emailPattern = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
        public static boolean isValidEmail(String email) {
            return email.matches(emailPattern.pattern());
        }

        public static boolean isValidPhoneNumber(String phone) {
            return phone.matches("[0-9]{10}");
        }

        public static boolean isValidDate(String date) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            try {
                Date d = sdf.parse(date);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
