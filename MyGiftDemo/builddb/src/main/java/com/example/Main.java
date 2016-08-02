package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {

    public static void main(String[] args) {
        // 创建数据库
        // Schema 引用 greenrobot这个包下的
        // 第一个参数是版本号,第二个是数据库存储的地方.(app中存储.)
        Schema schema = new Schema(1, "comnd.example.dllo.mygiftdemo.db");

        Entity entity = schema.addEntity("PresentBean");
        //添加id和列
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("userName");
        entity.addStringProperty("imageUrl");
        entity.addStringProperty("title");
        entity.addStringProperty("author");
        entity.addStringProperty("nextUrl");
        entity.addStringProperty("likesCount");
        entity.addStringProperty("other");
        entity.addStringProperty("others");
        entity.addStringProperty("path");
        entity.addBooleanProperty("islike");
        // 类别 如果单独创建一个数据库用来标识不同用户的收藏
        // 如果A用户收藏 这时候存入A用户的信息
        // 如果B用户收藏,这时候存入B用户的信息
        entity.addStringProperty("classify");


        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
