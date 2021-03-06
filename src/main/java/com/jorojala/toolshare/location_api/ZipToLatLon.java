package com.jorojala.toolshare.location_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jorojala.toolshare.models.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZipToLatLon {

    public static Location getLocation(String zipcode) throws IOException {

        String userZip = zipcode;
        String apiKey = "c12e8307e3d94229aff21166b9e6e2fc";

        Location location = null;
        try {
            URL url = new URL("https://api.geoapify.com/v1/geocode/search?postcode=" + userZip + "&filter=countrycode:us&format=json&apiKey=" + apiKey);
            HttpURLConnection zipHttp = (HttpURLConnection) url.openConnection();
            zipHttp.setRequestProperty("Accept", "application/json");

            System.out.println(zipHttp.getResponseCode() + " " + zipHttp.getResponseMessage());

            InputStreamReader zipInputStreamReader = new InputStreamReader(zipHttp.getInputStream());
            BufferedReader zipBufferedReader = new BufferedReader(zipInputStreamReader);

            String zipLine = zipBufferedReader.readLine();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            location = gson.fromJson(zipLine, Location.class);

            String locationJson = gson.toJson(location);

            zipHttp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot access Location API.");
        }

        return location;
    }


    public static double latLongDist(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (earthRadius * c);
        double distMiles = (0.00062137119224 * dist);

        return distMiles;
    }

}
