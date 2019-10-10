package com.payal.android.wikicontent;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.payal.android.wikicontent.adapter.MainCategoryAdapter;
import com.payal.android.wikicontent.datas.CategoryData;
import com.payal.android.wikicontent.datas.MainSubCategoryData;
import com.payal.android.wikicontent.sharedPref.InitApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static String TAG = "Tag_MainActivity";
    private DrawerLayout dl;
    private ActionBarDrawerToggle toggle;
    private NavigationView nv;
    Toolbar toolbar;
    RecyclerView recyclerView;

    ArrayList<ArrayList> subcategoryList= new ArrayList<>();
    ArrayList<CategoryData> categoryArray =  new ArrayList<>();
    ArrayList<MainSubCategoryData> subcategoryArrays;
    MainCategoryAdapter mainCategoryAdapter;
    public static int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: night mode ="+InitApplication.getInstance().isNightModeEnabled());

        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        toggle = new ActionBarDrawerToggle(this, dl,toolbar,R.string.Open, R.string.Close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        nv=findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.base_recycler);

        mainCategoryAdapter = new MainCategoryAdapter(getApplicationContext(),categoryArray,subcategoryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(mainCategoryAdapter);

        fetchArticleFromApi();
        //fetchDummyData();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_theme_setting:
                /*TODO : change screen to dark theme*/
                InitApplication.getInstance().setIsNightModeEnabled( !InitApplication.getInstance().isNightModeEnabled() , MainActivity.this);
                /*Intent i =getIntent();
                finish();
                startActivity(i);
                */recreate();
                break;
        }
        return false;
    }

    public void fetchArticleFromApi(){
        /*adding this three categories as top category*/
        final String[] topCategory ={"Education" , "Health" , "Crime"} ;
        count = 0;
        categoryArray.clear();subcategoryList.clear();count=0;

        Log.d(TAG, "fetchArticleFromApi: called----------------");
        for ( int i=0; i<topCategory.length ; i++){
            categoryArray.add(new CategoryData(""+i , topCategory[i]));
            /*url to get pages of particular category*/
            String url = "https://en.wikipedia.org/w/api.php?format=json&action=query&list=categorymembers&cmtitle=Category:"+topCategory[i]+"&cmsort=timestamp&cmdir=desc&cmlimit=5";

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Log.d(TAG, "onResponse() returned: " + response);
                    count++;
                    parseData(response,topCategory.length);
                    //Log.d(TAG, "onResponse: sub array len  = "+subcategoryList.size());

                    if (count == topCategory.length)
                    {Log.d(TAG, "onResponse: calling adapter");
                        mainCategoryAdapter.notifyDataSetChanged();
                    }else Log.d(TAG, "onResponse: adapter not called");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "onErrorResponse: ",error );
                }
            });
            requestQueue.add(request);
        }
    }

    private void parseData(String response, int categoryArrayLength) {
        try{
                JSONObject responseObject = new JSONObject(response);
                responseObject = responseObject.getJSONObject("query");
                JSONArray queryArray = responseObject.getJSONArray("categorymembers");
                subcategoryArrays = new ArrayList<>();
                String title;
                for (int i = 0; i< queryArray.length() ; i++){
                    title =queryArray.getJSONObject(i).get("title").toString();
                    subcategoryArrays.add(new MainSubCategoryData((i+1)+"" , title , ""));
                }
                if (subcategoryList.size()<=3)
                subcategoryList.add(subcategoryArrays);

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void fetchDummyData(){

    subcategoryArrays = new ArrayList<>();
    //adding dummy data for testing
    subcategoryArrays.add(new MainSubCategoryData("id1","title1","imageurl"));
    subcategoryArrays.add(new MainSubCategoryData("id2","title2","imageurl"));
    subcategoryArrays.add(new MainSubCategoryData("id3","title3","imageurl"));

    subcategoryList.add(subcategoryArrays);//products belong to one category
    subcategoryList.add(subcategoryArrays);
    subcategoryList.add(subcategoryArrays);
    subcategoryList.add(subcategoryArrays);
    subcategoryList.add(subcategoryArrays);
    subcategoryList.add(subcategoryArrays);

    categoryArray.add(new CategoryData("id1","CategoryName1"));//vertical list having all article list
    categoryArray.add(new CategoryData("id2","CategoryName2"));
    categoryArray.add(new CategoryData("id3","CategoryName3"));
    categoryArray.add(new CategoryData("id3","CategoryName3"));
    categoryArray.add(new CategoryData("id3","CategoryName3"));
    categoryArray.add(new CategoryData("id3","CategoryName3"));

    mainCategoryAdapter.notifyDataSetChanged();

}

}