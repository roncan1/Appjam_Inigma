package com.inigma.appjaminigma;

public class DataModel {
    private String titleName; //주제이름
    private String category; //카테고리
    private String[] detailGraph; //세부가지

    public DataModel() {

    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
