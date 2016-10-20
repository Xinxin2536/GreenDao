package com.example;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGenerate {
    public static void main(String[] args){
        //初始数据库内容
        Schema schema = new Schema(1,"com.qf.lxsdsdsa");
        Entity userEntity = schema.addEntity("UserEntity");
        userEntity.setTableName("t_user");
        userEntity.addIdProperty().columnName("_id").autoincrement();
        userEntity.addStringProperty("name").columnName("name").notNull();
        userEntity.addIntProperty("age").columnName("age");
        userEntity.addStringProperty("tel").columnName("tel").unique();


        Entity fruitEntity = schema.addEntity("FruitEntity");
        fruitEntity.setTableName("t_fruit");
        fruitEntity.addIdProperty().columnName("_id").autoincrement();
        fruitEntity.addStringProperty("color").columnName("color");
        fruitEntity.addIntProperty("size").columnName("size");
        try {
            DaoGenerator daoGenerator = new DaoGenerator();
            daoGenerator.generateAll(schema,"D:/AndroidStudioProjects/GreenDao/app/src/main/java-gen");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
