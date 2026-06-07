package AppService;

import DataService.AccountService;

public class SettingsFunctions {

    public static String changeEmail(String email, String newEmail) {
        AccountService.updateEmail(email, newEmail);
        return newEmail;
    }

    public static String changePassword(String email, String newPassword) {
        AccountService.updatePassword(email, newPassword);
        return newPassword;
    }

    public static String changeFirstName(String email, String newFirstName) {
        AccountService.updateFirstName(email, newFirstName);
        return newFirstName;
    }
    
    public static String changeLastName(String email, String newLastName) {
        AccountService.updateLastName(email, newLastName);
        return newLastName;
    }

    public static String changePhone(String email, String newPhone) {
        AccountService.updatePhone(email, newPhone);
        return newPhone;
    }

    public static String changeAddress(String email, String newAddress) {
        AccountService.updateAddress(email, newAddress);
        return newAddress;
    }

    public static String changeBirthday(String email, String newBirthday) {
        AccountService.updateBirthday(email, newBirthday);
        return newBirthday;
    }
}