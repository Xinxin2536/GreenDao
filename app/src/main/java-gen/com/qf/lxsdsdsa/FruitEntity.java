package com.qf.lxsdsdsa;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table t_fruit.
 */
public class FruitEntity {

    private Long id;
    private String color;
    private Integer size;

    public FruitEntity() {
    }

    public FruitEntity(Long id) {
        this.id = id;
    }

    public FruitEntity(Long id, String color, Integer size) {
        this.id = id;
        this.color = color;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}