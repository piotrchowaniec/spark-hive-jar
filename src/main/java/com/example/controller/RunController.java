package com.example.controller;

import com.example.spark.SparkSqlSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author <a href="mailto:piotr.chowaniec@fingo.pl">Piotr Chowaniec - FINGO</a>
 */
@RestController
public class RunController {

    private final SparkSqlSession sqlSession;

    public RunController(SparkSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @GetMapping(path = "run")
    public String run() {
        String dbName = "test_" + System.currentTimeMillis();
        sqlSession.createDatabase(dbName);
        sqlSession.useDatabase(dbName);
        Dataset<Row> tables = sqlSession.executeSql("show tables");
        return "OK " + tables.count();
    }
}
