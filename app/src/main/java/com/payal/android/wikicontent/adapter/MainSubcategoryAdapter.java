package com.payal.android.wikicontent.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.payal.android.wikicontent.R;
import com.payal.android.wikicontent.datas.MainSubCategoryData;

import java.util.ArrayList;


public class MainSubcategoryAdapter extends RecyclerView.Adapter<MainSubcategoryAdapter.ViewHolder> {

    public String TAG = "Tag_MainSubcategoryAdapter";
    private Context context;
//    private List<ListItemQuote> listItems;
    private ArrayList<MainSubCategoryData> listItems;

    public MainSubcategoryAdapter(Context context, ArrayList listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategory_recycler_adapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainSubcategoryAdapter.ViewHolder holder, int position) {
        // TODO : bind data values with view here
        //Log.d(TAG, "onBindViewHolder: subcategory = "+listItems.get(position).getSubCategoryTitle());
        holder.subCategoryTitle.setText(listItems.get(position).getSubCategoryTitle());
        holder.subCategoryImage.setImageResource(R.drawable.wikilogo);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView subCategoryTitle;
        public ImageView subCategoryImage;
        public LinearLayout subCategoryLayout;


        public ViewHolder(final View itemView) {


            super(itemView);

            subCategoryLayout = itemView.findViewById(R.id.subcategory_recycler_layout);

            subCategoryTitle = itemView.findViewById(R.id.adaptertext);

            subCategoryImage = itemView.findViewById(R.id.adapterimage);

            subCategoryLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO : open complete article of this page

                }
            });


        }
    }
}
