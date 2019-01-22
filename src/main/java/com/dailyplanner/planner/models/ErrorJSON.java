package com.dailyplanner.planner.models;

public class ErrorJSON {
    private String error;

    public ErrorJSON(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {

        this.error = error;
    }
}
