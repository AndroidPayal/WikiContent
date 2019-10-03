package com.payal.android.wikicontent.datas;

public class MainSubCategoryData {

   String subCategoryId;
    String subCategoryTitle;
    String subCategoryImage;


    public MainSubCategoryData(String subCategoryId, String subCategoryTitle, String subCategoryImage) {
        this.subCategoryId = subCategoryId;
        this.subCategoryTitle = subCategoryTitle;
        this.subCategoryImage = subCategoryImage;
    }


    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryTitle() {
        return subCategoryTitle;
    }

    public void setSubCategoryTitle(String subCategoryTitle) {
        this.subCategoryTitle = subCategoryTitle;
    }

    public String getSubCategoryImage() {
        return subCategoryImage;
    }

    public void setSubCategoryImage(String subCategoryImage) {
        this.subCategoryImage = subCategoryImage;
    }
}

