package com.example.spark;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSqlSession {

    private final SparkSession session;
    private final SparkContext context;

    public SparkSqlSession(SparkSession session) {
        this.session = session;
        this.context = session.sparkContext();
    }

    public SparkSqlSession newSession() {
        SparkSession newSession = this.session.newSession();
        return new SparkSqlSession(newSession);
    }

    public SparkContext sparkContext() {
        return session.sparkContext();
    }

    public DataFrameReader read() {
        return this.session.read();
    }

    public void createDatabase(String dbName) {
        String sqlStatement = String.format("CREATE DATABASE %s", dbName);
        this.executeSql(sqlStatement);
    }

    public Dataset<Row> executeSql(String sqlStatement) {
        this.context.setJobDescription(sqlStatement);
        return this.session.sql(sqlStatement);
    }

    public void useDatabase(String dbName) {
        String sqlStatement = String.format("USE %s", dbName);
        this.executeSql(sqlStatement);
    }

    public void dropDatabase(String dbName) {
        String sqlStatement = String.format("DROP DATABASE IF EXISTS %s CASCADE", dbName);
        this.executeSql(sqlStatement);
    }

    public Dataset<Row> table(String tableName) {
        return this.session.table(tableName);
    }

}
