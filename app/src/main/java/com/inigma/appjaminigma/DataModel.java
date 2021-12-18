package com.inigma.appjaminigma;

public class DataModel {
    private String titleName; //주제이름
    private String category; //카테고리
    private String explain; //설명

    public DataModel(String title, String explain) {
        this.titleName = title;
        this.explain = explain;
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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
