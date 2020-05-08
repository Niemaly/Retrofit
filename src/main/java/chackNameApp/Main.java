package chackNameApp;

import pojo.User;
import retrofit2.Call;
import retrofit2.Response;
import service.GitHubServiceGenerator;
import service.UserService;

import java.util.HashSet;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {

        UserService service
                = GitHubServiceGenerator.createService(UserService.class,  "e82668ec2cfc7e32fe555e0791f9ec72922f6419");

        boolean run = true;

        while(run) {

            String login = insertLogin();

            Set<String> set = new HashSet<>();

            if (!loginIsAvailable(service, login)) {
                set = createDifferentLogin(login);
            }

            if (set.size()>0){
                System.out.println("This login is taken  "+ login + "  below you finde recommended logins that is free to chose :) ");
                set.forEach(e-> System.out.println(e));
            } else{
                System.out.println("Your login   "+ login + "  is available ");
                run = false;
            }


        }

    }

    static boolean loginIsAvailable(UserService service, String login){
        Call<User> callSync = service.getUser(login);

        try {
            Response<User> response = callSync.execute();
            if (response.isSuccessful()){
                User user = response.body();
                if (user!=null){
                    System.out.println("user login is taken  "+user);
                    return false;
                } else{
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    static Set<String> createDifferentLogin(String login){
        Set<String> set = new HashSet<>();

        if (login.contains(".")){
            set.add(login.replace(".", "_"));
        }
        if (login.contains(" ")){
            set.add(login.replace(" ", "_"));
        }

        return set;

    }


    static String insertLogin() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("write your login");

        String login = scanner.nextLine();

        scanner.close();

        LoginValidator validator = new LoginValidator();

        if (validator.validate(login)){
            return login;
        }else {
            throw new Exception();
        }
    }

    static boolean nextCheck(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("do you want check another login??");

        String newLogin = scanner.nextLine();



        return true;
    }

}
