package mainClasses;

import pojo.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import service.GitHubServiceGenerator;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        UserService service
                = GitHubServiceGenerator.createService(UserService.class,  "e82668ec2cfc7e32fe555e0791f9ec72922f6419");

        asyncCallUsersById(service);


    }

    static void syncCall(UserService service){
        Call<User> callSync = service.getUser("Niemaly");

        try {
            Response<User> response = callSync.execute();
            User user = response.body();

            System.out.println("syncCall  "+user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    static void asyncCallUsersById(UserService service) {

        List<User> users = new ArrayList<>();

        for (int id= 1 ; id<100 ; id++){
            Call<User> callAsync = service.getUserById(id);

            callAsync.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User>  call, Response<User> response) {

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



}
