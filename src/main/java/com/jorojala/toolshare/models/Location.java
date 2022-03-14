package com.jorojala.toolshare.models;

import com.jorojala.toolshare.models.Results;

import java.util.Arrays;

public class Location {

    Results[] results;

    public Results[] getResults() {
        return results;
    }

    public void setResults(Results[] results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Location{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}
