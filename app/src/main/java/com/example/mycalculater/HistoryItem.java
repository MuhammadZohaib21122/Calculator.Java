package com.example.mycalculater;

public class HistoryItem {
    private String result;
    private String expression;
    int id;

    public HistoryItem(int id ,String result, String expression) {
        this.result = result;
        this.expression = expression;
        this.id = id;
    }


    public String getResult() {
        return result;
    }

    public String getExpression() {
        return expression;
    }

    public int getId() {
        return id;
    }
}