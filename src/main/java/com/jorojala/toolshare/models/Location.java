package com.jorojala.toolshare.models;

import com.jorojala.toolshare.models.Results;

import java.util.Arrays;

public class Location {

    Results[] results;

    @Override
    public String toString() {
        return "Location{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}
