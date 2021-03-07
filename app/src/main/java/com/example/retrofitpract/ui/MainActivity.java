package com.example.retrofitpract.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofitpract.R;
import com.example.retrofitpract.model.User;
import com.example.retrofitpract.network.RetrofitClient;
import com.example.retrofitpract.network.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private RetrofitClient retrofitClient;
    private ArrayList<User> arrayList = new ArrayList<>();
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnInit();
        OnCreateUsers();
    }

    void OnInit(){
        listView = findViewById(R.id.listViewUsers);
        retrofitClient = RetrofitService.getInstance();
    }
    private void OnCreateUsers() {
        Call<List<User>> call = retrofitClient.getUsers();
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();

                //Creating an String array for the ListView
                String[] users = new String[userList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < userList.size(); i++) {
                    users[i] = userList.get(i).getName();
                }

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, users));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}