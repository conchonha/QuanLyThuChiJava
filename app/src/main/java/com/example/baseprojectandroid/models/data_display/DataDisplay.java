package com.example.baseprojectandroid.models.data_display;

import com.example.baseprojectandroid.R;

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/24/2022
*/
public class DataDisplay {
    private Integer titleCategory = R.string.lbl_category;
    private Integer titleDate = R.string.lbl_date;
    private Integer titlePrice = R.string.lbl_price;
    private Integer titleNote = R.string.lbl_note;
    private Integer titleSave = R.string.lbl_save;
    private Integer titleCancel = R.string.lbl_cancel;

    public DataDisplay(Integer titleCategory, Integer titleDate, Integer titlePrice, Integer titleNote, Integer titleSave, Integer titleCancel) {
        this.titleCategory = titleCategory;
        this.titleDate = titleDate;
        this.titlePrice = titlePrice;
        this.titleNote = titleNote;
        this.titleSave = titleSave;
        this.titleCancel = titleCancel;
    }

    public DataDisplay(){

    }

    public Integer getTitleCategory() {
        return titleCategory;
    }

    public void setTitleCategory(Integer titleCategory) {
        this.titleCategory = titleCategory;
    }

    public Integer getTitleDate() {
        return titleDate;
    }

    public void setTitleDate(Integer titleDate) {
        this.titleDate = titleDate;
    }

    public Integer getTitlePrice() {
        return titlePrice;
    }

    public void setTitlePrice(Integer titlePrice) {
        this.titlePrice = titlePrice;
    }

    public Integer getTitleNote() {
        return titleNote;
    }

    public void setTitleNote(Integer titleNote) {
        this.titleNote = titleNote;
    }

    public Integer getTitleSave() {
        return titleSave;
    }

    public void setTitleSave(Integer titleSave) {
        this.titleSave = titleSave;
    }

    public Integer getTitleCancel() {
        return titleCancel;
    }

    public void setTitleCancel(Integer titleCancel) {
        this.titleCancel = titleCancel;
    }
}
