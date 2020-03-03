package com.jgvidotto.hero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jgvidotto.hero.ui.main.Adapter.HeroAdapter;
import com.jgvidotto.hero.ui.main.JsonPlaceHolderApi;
import com.jgvidotto.hero.ui.main.Model.CharacterDataWrapper;
import com.jgvidotto.hero.ui.main.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.jgvidotto.hero.ui.main.HelperClass.PRIVATE_KEY;
import static com.jgvidotto.hero.ui.main.HelperClass.PUBLIC_KEY;
import static com.jgvidotto.hero.ui.main.Utils.md5;

public class MainActivity extends AppCompatActivity implements HeroAdapter.OnHeroListener {

    private HeroAdapter adapter;
    private RecyclerView recyclerView;
    CharacterDataWrapper mCharacterDataWrapper;
    ProgressDialog progressDoalog;

    String ts = "1";
    String setSufix = ts + PRIVATE_KEY + PUBLIC_KEY;
    String sufix = md5(setSufix);
    private JsonPlaceHolderApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


        service = RetrofitClientInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        callApi();
    }

    private void callApi(){
        Call<CharacterDataWrapper> call = service.getResults(ts, PUBLIC_KEY, sufix);
        call.enqueue(new Callback<CharacterDataWrapper>() {
            @Override
            public void onResponse(Call<CharacterDataWrapper>  call, Response<CharacterDataWrapper> response) {
                progressDoalog.dismiss();
                mCharacterDataWrapper = response.body();
                generateDataList(mCharacterDataWrapper);
            }

            @Override
            public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(CharacterDataWrapper characterList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new HeroAdapter(this,characterList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onHeroClick(int position) {
        Intent intent = new Intent(this, HeroDetailsActivity.class);
        intent.putExtra("hero_object", mCharacterDataWrapper.getData().getResults().get(position));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        ImageView closeButton = (ImageView)searchView.findViewById(R.id.search_close_btn);
        //Listener for search Text
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                service = RetrofitClientInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
                Call<CharacterDataWrapper> call = service.getSearchResults(ts, PUBLIC_KEY, sufix, s);
                call.enqueue(new Callback<CharacterDataWrapper>() {
                    @Override
                    public void onResponse(Call<CharacterDataWrapper>  call, Response<CharacterDataWrapper> response) {
                        progressDoalog.dismiss();
                        mCharacterDataWrapper = response.body();
                        generateDataList(mCharacterDataWrapper);
                    }

                    @Override
                    public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
                        progressDoalog.dismiss();
                        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.search_src_text);

                //Clear the text from EditText view
                et.setText("");

                //Clear query
                searchView.setQuery("", false);
                //Collapse the action view
                searchView.onActionViewCollapsed();
                callApi();
            }
        });
        return true;
    }
}
