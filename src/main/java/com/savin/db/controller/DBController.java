package com.savin.db.controller;

import com.savin.db.structure.Record;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBController implements GuestBookController {
    private Record record;

    public DBController(DataSource ds){
        record = new Record(ds);
    }

    public void addRecord(String message) throws SQLException {
        record.addRecord(System.currentTimeMillis(), message);
    }

    public List<String> getRecords() throws SQLException { //Record {id, postDate, message}{
        return record.getRecords();

    }

    public void delete() throws SQLException {
        record.delete();
    }

    public void close() throws SQLException {
        record.close();
    }
}
