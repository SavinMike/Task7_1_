package com.savin.db.controller;

import com.savin.db.structure.Record;

import java.sql.SQLException;
import java.util.List;


public interface GuestBookController extends AutoCloseable {
    void addRecord(String message) throws SQLException;

    List<Record> getRecords() throws SQLException;

    void close() throws SQLException;

    void delete() throws SQLException;
}
