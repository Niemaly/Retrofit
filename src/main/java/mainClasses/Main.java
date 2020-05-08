package mainClasses;

import okhttp3.OkHttpClient;
import pojo.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

//        syncCall(retrofit);
//
//        asyncCall(retrofit);

        syncCallGetAllUsers(retrofit);

    }


    static void syncCall(Retrofit retrofit){
        UserService service = retrofit.create(UserService.class);
        Call<User> callSync = service.getUser("Niemaly");

        try {
            Response<User> response = callSync.execute();
            User user = response.body();

            System.out.println("syncCall  "+user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    static void syncCallUsers(Retrofit retrofit){
        UserService service = retrofit.create(UserService.class);
        Call<List<User>> callSync = service.getUsers(100, 0);
        System.out.println(callSync.request().url().toString());
        try {
            Response<List<User>> response = callSync.execute();
            List<User> users = response.body();

            for (User user: users){
                System.out.println(user);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    static void asyncCall(Retrofit retrofit) {
        UserService service = retrofit.create(UserService.class);
        Call<User> callAsync = service.getUser("Niemaly");

        callAsync.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                System.out.println("asyncCall  "+ user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });

    }



    static void asyncCallUsers(Retrofit retrofit) {
        UserService service = retrofit.create(UserService.class);
        Call<List<User>> callAsync = service.getUsers(100000,1);

        callAsync.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>>  call, Response<List<User>> response) {
                List<User> users = response.body();
                users.stream().forEach(e-> System.out.println(e));

            }

            @Override
            public void onFailure(Call<List<User>>  call, Throwable throwable) {
                System.out.println(throwable);
            }
        });

    }

    static void asyncCallUsersById(Retrofit retrofit) {
        UserService service = retrofit.create(UserService.class);

        List<User> users = new ArrayList<>();

        for (int id= 1 ; id<10 ; id++){
            Call<User> callAsync = service.getUserById(id);

            callAsync.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                        users.add(response.body());
                        System.out.println(response.body());

                }

                @Override
                public void onFailure(Call<User>  call, Throwable throwable) {
                    System.out.println(throwable);
                }
            });
        }
        users.stream().forEach(e-> System.out.println(e));

    }

    static void syncCallGetAllUsers(Retrofit retrofit){
        UserService service = retrofit.create(UserService.class);
        Call<List<User>> callSync = service.getAllUsers(10000-10);

        try {
            Response<List<User>> response = callSync.execute();
            List<User> users = response.body();

            for (User user: users){
                System.out.println(user);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
