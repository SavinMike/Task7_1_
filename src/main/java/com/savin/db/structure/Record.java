package com.savin.db.structure;

public class Record {
    private String postMessage;
    private long data;
    private int id=0;

    public Record(String postMessage) {
        this.data = System.currentTimeMillis();
        this.postMessage = postMessage;
    }

    public Record(int id, long data, String postMessage) {
        this.data = data;
        this.id = id;
        this.postMessage = postMessage;
    }

    public long getData() {
        return data;
    }

    public String toString() {
        return "Message:" + postMessage;
    }

}

