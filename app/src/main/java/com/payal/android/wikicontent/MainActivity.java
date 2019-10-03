package com.payal.android.wikicontent;

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
import android.view.MenuItem;

import com.payal.android.wikicontent.adapter.MainCategoryAdapter;
import com.payal.android.wikicontent.datas.CategoryData;
import com.payal.android.wikicontent.datas.MainSubCategoryData;
import com.payal.android.wikicontent.sharedPref.InitApplication;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "Tag_MainActivity";
    private DrawerLayout dl;
    private ActionBarDrawerToggle toggle;
    private NavigationView nv;
    Toolbar toolbar;
    RecyclerView recyclerView;

    ArrayList<ArrayList> subcategoryList;
    ArrayList<CategoryData> categoryArray;
    ArrayList<MainSubCategoryData> subcategoryArrays;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_theme_setting:
                        Intent i =new Intent(MainActivity.this,SettingTheme.class);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });

        categoryArray = new ArrayList<>();
        subcategoryList = new ArrayList<>();

        recyclerView = findViewById(R.id.base_recycler);

        fetchArticleFromApi();

        MainCategoryAdapter mainCategoryAdapter = new MainCategoryAdapter(getApplicationContext(),categoryArray,subcategoryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setAutoMeasureEnabled(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);

        recyclerView.setHasFixedSize(false);

        recyclerView.setAdapter(mainCategoryAdapter);


    }

    public void fetchArticleFromApi(){
/*https://en.wikipedia.org/w/api.php?format=json&action=query&generator=random&grnnamespace=0&prop=revisions%7Cimages&rvprop=content&grnlimit=10
* */
        //TODO : here we need to fetch data from api and add in two arrays

        subcategoryArrays = new ArrayList<>();
        //adding dummy data for testing
        subcategoryArrays.add(new MainSubCategoryData("id1","title1","imageurl"));
        subcategoryArrays.add(new MainSubCategoryData("id2","title2","imageurl"));
        subcategoryArrays.add(new MainSubCategoryData("id3","title3","imageurl"));

        subcategoryList.add(subcategoryArrays);//products belong to one category

        CategoryData categoryData = new CategoryData("id1","CategoryName1");
        categoryArray.add(categoryData);//vertical list having all article list
        categoryData = new CategoryData("id2","CategoryName2");
        categoryArray.add(categoryData);categoryData = new CategoryData("id3","CategoryName3");
        categoryArray.add(categoryData); categoryArray.add(categoryData);categoryData = new CategoryData("id3","CategoryName3");
        categoryArray.add(categoryData); categoryArray.add(categoryData);categoryData = new CategoryData("id3","CategoryName3");
        categoryArray.add(categoryData); categoryArray.add(categoryData);categoryData = new CategoryData("id3","CategoryName3");
        categoryArray.add(categoryData);


    }



}
