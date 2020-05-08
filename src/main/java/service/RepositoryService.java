package service;

import pojo.RepositoryList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface RepositoryService {

    @GET("/users/{user_name}/repos")
    public Call<List<RepositoryList>> callByUserId(@Path("user_name") String userName);


}
