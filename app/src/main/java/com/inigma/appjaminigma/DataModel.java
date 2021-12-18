package com.inigma.appjaminigma;

public class DataModel {
    private String titleName; //주제이름
    private String category; //카테고리
    private String explain; //설명
    String[] option = new String[12];
    String optionValue = null;
    String memo = "메모할 내용을 입력하세요";

    public void setMemo(String memo) {
        this.memo = memo;
    }

    String getMemo() {
        return this.memo;
    }

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

    public void setOption(String option) {
        for (int i = 0; i < 12; i++) {
            if (this.option[i] == null) {
                this.option[i] = option;
                break;
            }
        }
    }

    public String getOption() {
        for (int i = 0; i < 12; i++) {
            if (this.option[i] == null) {
                this.optionValue = this.option[i];
                this.option[i] = null;
                shiftLeft(this.option, 1, 12);
                break;
            }
        }

        if (this.optionValue == null) {
            return null;
        } else {
            return this.optionValue;
        }
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
    void reverse(String arr[], int start, int end) {
        String temp;
        end = end - 1;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    void shiftLeft(String arr[], int d, int n) {
        reverse(arr, 0, d);
        reverse(arr, d, n);
        reverse(arr, 0, n);
    }

}
