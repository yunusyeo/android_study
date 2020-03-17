package com.example.firmtask.Activity.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.SearchView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.firmtask.Util.Constants;
import com.example.firmtask.Model.Response;
import com.example.firmtask.Presenter.MainActivityPresenter;
import com.example.firmtask.R;
import com.example.firmtask.Util.Util;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private ProgressDialog progressDialog;
    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityPresenter = new MainActivityPresenter();
        mainActivityPresenter.setView(this);
        mainActivityPresenter.create();
    }

    @Override
    public void bindViews() {
        setUI();
        progressDialog = new ProgressDialog(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, new ArrayList<Response>());
        recyclerView.setAdapter(recyclerViewAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(Util.isConnected(MainActivity.this)){
                    mainActivityPresenter.request(query);
                }else{
                    Toast.makeText(MainActivity.this, getString(R.string.internet_warning),
                            Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.toolbar_title));
        setSupportActionBar(toolbar);

        searchView = (SearchView) findViewById(R.id.search_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
        Util.showProgress(progressDialog);
    }

    @Override
    public void hideProgress() {
        Util.dismissProgress(progressDialog);
    }

    @Override
    public void response(Response response) {
        if (response.getResponse().equals(Constants.SUCCESS)) {
            recyclerViewAdapter.responses.add(response);
            recyclerViewAdapter.notifyDataSetChanged();
        } else {
            recyclerViewAdapter.responses.clear();
            recyclerViewAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Sonuç Bulunamadı.", Toast.LENGTH_SHORT).show();
        }
    }
}
