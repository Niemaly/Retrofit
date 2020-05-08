package chackNameApp;

public class LoginValidator {
    public boolean validate(String login) {
        if (login.length()>0 && login.matches("^*$")){
            return true;
        } else{
            return false;
        }
    }
}
