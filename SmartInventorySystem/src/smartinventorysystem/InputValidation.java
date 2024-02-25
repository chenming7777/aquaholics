package purchaseordermanagementsystem;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Arrays;

public class InputValidation {

    public static boolean checkIsInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkIsDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkValidPrice(String input) {
        if (checkIsDouble(input)) {
            double price = Double.parseDouble(input);
            return price >= 0;
        }
        return false;
    }

    public static boolean checkValidQuantity(String input) {
        if (checkIsInt(input)) {
            int quantity = Integer.parseInt(input);
            return quantity >= 0;
        }
        return false;
    }
    
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String format03 = "^03\\d{8}$";
        String format01 = "^01\\d{8}$";
        return phoneNumber.matches(format03) || phoneNumber.matches(format01);
    }
    public static boolean isValidDate(String date) {
        String datePattern = "^(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-(\\d{4})$";
        Matcher matcher = Pattern.compile(datePattern).matcher(date);
        if (!matcher.matches()) {
            return false;
        }
        int day = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        int year = Integer.parseInt(matcher.group(3));
        if (month < 1 || month > 12 || day < 1 || day > daysInMonth(month, year)) {
            return false;
        }
        return true;
    }
    private static int daysInMonth(int month, int year) {
        if (month == 2) {
            // February: Check for leap year
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                return 29; // Leap year
            } else {
                return 28; // Non-leap year
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30; // April, June, September, November
        } else {
            return 31; // All other months
        }
    }
    
    public static boolean checkItemExistInFile(Item target){
        FileManager file = new FileManager("Item.txt");
        ArrayList<String[]> searchList = file.searchData(target.getItemName());
        ArrayList<String[]> filterList = file.filterData(5, target.getSupplier().getSupplierID());
        ArrayList<String[]> resultList = new ArrayList<String[]>();
        for(int i = 0; i<searchList.size();i++){
            for(int j = 0; j<filterList.size();j++){
                if(Arrays.equals(searchList.get(i),filterList.get(j)) == true){
                    resultList.add(searchList.get(i));               
                }
            }
        }
        return resultList.size()>0;
    }
    public static boolean checkSupplierItemExist(String SupplierID){
        FileManager file = new FileManager("Item.txt");
        ArrayList<String[]> filterList = file.filterData(5, SupplierID);
        return filterList.size()>0;
    }
}

