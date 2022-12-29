package com.example.myshop.bean;

import java.io.Serializable;

public class productBean implements Serializable {

    /**
     * id : 1
     * name : 花椒
     * description : 无
     * price : 1
     * categoryleveltwo_id : 1
     * categorylevelone_id : 1
     * categorylevelthree_id : 1
     * file_name : null
     * stock : 1
     */

    private int id;
    private String name;
    private String description;
    private int price;
    private int categoryleveltwo_id;
    private int categorylevelone_id;
    private int categorylevelthree_id;
    private String file_name;
    private int stock;
    private int number;
    private Boolean isSelected = true;

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Object getCategoryleveltwo_id() {
        return categoryleveltwo_id;
    }

    public void setCategoryleveltwo_id(int categoryleveltwo_id) {
        this.categoryleveltwo_id = categoryleveltwo_id;
    }

    public int getCategorylevelone_id() {
        return categorylevelone_id;
    }

    public void setCategorylevelone_id(int categorylevelone_id) {
        this.categorylevelone_id = categorylevelone_id;
    }

    public int getCategorylevelthree_id() {
        return categorylevelthree_id;
    }

    public void setCategorylevelthree_id(int categorylevelthree_id) {
        this.categorylevelthree_id = categorylevelthree_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
