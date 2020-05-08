package service;


import pojo.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface UserService {

    @GET("/users")
    public Call<List<User>> getUsers(
            @Query("per_page") int per_page,
            @Query("page") int page);

    @GET("/users")
    public Call<List<User>> getAllUsers(@Query("since") int since);

    @GET("/users/{username}")
    public Call<User> getUser(@Path("username") String username);

    @GET("/users/{id}")
    Call<User>getUserById(@Path("id")int id);
}
