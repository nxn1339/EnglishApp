package com.example.appenglish.Model;

public class Database {
    public static final String DATABASE_NAME = "dbEngLishApp.db";
    public static final  String TABLE_USER = "User";

    public static final String TABLE_TOPIC ="Topic";
    public static final String TABLE_QUESTION ="Question";

    public static final String TABLE_USER_TOPIC ="UserTopic";
    public static final int DATABASE_VERSION = 1;
    // Câu lệnh SQL tạo mới cơ sở dữ liệu.
    public static final String DATABASE_CREATE_USER = "create table "+TABLE_USER+"(\n" +
            "\t\"id_user\"\tINTEGER NOT NULL,\n" +
            "\t\"user_name\"\ttext NOT NULL UNIQUE,\n" +
            "\t\"password\"\ttext NOT NULL,\n" +
            "\t\"lv\"\ttext NOT NULL,\n" +
            "\t\"role\"\ttext NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_user\" AUTOINCREMENT)\n" +
            "); ";
    public static final String DATABASE_CREATE_TOPIC = "create table "+TABLE_TOPIC+"(\n" +
            "\t\"id_topic\"\tINTEGER NOT NULL,\n" +
            "\t\"title\"\tTEXT NOT NULL,\n" +
            "\t\"img\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_topic\" AUTOINCREMENT)\n" +
            ");";
    public static final String DATABASE_CREATE_USER_TOPIC = "create table "+TABLE_USER_TOPIC+"(\n" +
            "\t\"id_user_topic\"\tINTEGER NOT NULL,\n" +
            "\t\"id_user\"\tINTEGER DEFAULT NULL,\n" +
            "\t\"id_topic\"\tINTEGER DEFAULT NULL,\n" +
            "\t\"point\"\tINTEGER,\n" +
            "\tPRIMARY KEY(\"id_user_topic\" AUTOINCREMENT),\n" +
            "\tFOREIGN KEY(\"id_topic\") REFERENCES \"Topic\"(\"id_topic\"),\n" +
            "\tFOREIGN KEY(\"id_user\") REFERENCES \"User\"(\"id_user\")\n" +
            ");";
    public static final String DATABASE_CREATE_QUESTION = "create table "+TABLE_QUESTION+"(\n" +
            "\t\"id_question\"\tINTEGER NOT NULL,\n" +
            "\t\"id_topic\"\tINTEGER DEFAULT NULL,\n" +
            "\t\"question\"\tINTEGER,\n" +
            "\tFOREIGN KEY(\"id_topic\") REFERENCES \"Topic\"(\"id_topic\"),\n" +
            "\tPRIMARY KEY(\"id_question\" AUTOINCREMENT)\n" +
            ");";

    private static final String TAG = "EngLishAppDatabaseAdapter";
}
