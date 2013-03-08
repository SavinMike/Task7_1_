package com.savin.db.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GuestBookController extends AutoCloseable {
    void addRecord(String message) throws SQLException;

    List<String> getRecords() throws SQLException; //Record {id, postDate, message}

    void close() throws SQLException;

    void delete() throws SQLException;
}
