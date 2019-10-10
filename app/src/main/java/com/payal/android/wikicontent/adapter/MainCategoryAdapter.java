package com.payal.android.wikicontent.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.payal.android.wikicontent.R;
import com.payal.android.wikicontent.datas.CategoryData;
import com.payal.android.wikicontent.datas.MainSubCategoryData;

import java.util.ArrayList;


public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.ViewHolder> {

    public String TAG = "Tag_MainCategoryAdapter";
    private Context context;
    private ArrayList<CategoryData> listItems;
    private ArrayList<ArrayList> modelDataArray;

    public MainCategoryAdapter(Context context, ArrayList listItems, ArrayList modelDataArray) {
        this.context = context;
        this.listItems = listItems;
        this.modelDataArray = modelDataArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_recycler_adapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainCategoryAdapter.ViewHolder holder, int position) {
        holder.categoryTitle.setText(listItems.get(position).getTitle());

        if (modelDataArray.size()!=0) {
            Log.d(TAG, "onBindViewHolder:size= "+modelDataArray.size());
            MainSubcategoryAdapter adapter = new MainSubcategoryAdapter(context, modelDataArray.get(position));
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false);
            holder.subCategoryRecyclerView.setHasFixedSize(true);
            holder.subCategoryRecyclerView.setLayoutManager(layoutManager);
            holder.subCategoryRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView categoryTitle;
        public RecyclerView subCategoryRecyclerView;
        public TextView viewAll;


        public ViewHolder(View itemView) {


            super(itemView);

            categoryTitle = itemView.findViewById(R.id.category_recycler_titleText);
            subCategoryRecyclerView = itemView.findViewById(R.id.subcategory_recyclerView);

            viewAll = itemView.findViewById(R.id.category_recycler_viewAll);

            viewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //TODO  : fetch product list of selected tab's category

                }
            });


        }
    }
}
