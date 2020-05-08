package mainClasses;

import pojo.RepositoryList;
import retrofit2.Call;
import retrofit2.Response;
import service.GitHubServiceGenerator;
import service.RepositoryService;

import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        RepositoryService service
                = GitHubServiceGenerator.createService(RepositoryService.class);



        syncCall(service);
    }

    static void syncCall(RepositoryService service){

        Call<List<RepositoryList>> callSync = service.callByUserId("Niemaly");
        System.out.println(callSync.request().url().toString());

        try {
            Response<List<RepositoryList>> response = callSync.execute();
       //     Repository repository = response.body();
            response.body().stream().forEach(e-> System.out.println(e.toString()));

           // System.out.println(repository.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
