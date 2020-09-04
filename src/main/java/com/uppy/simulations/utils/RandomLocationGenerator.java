package com.uppy.simulations.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class RandomLocationGenerator {

    @Data
    @AllArgsConstructor
    public static class Location {
        private double latitude;
        private double longitude;
    }

    public static RandomLocationGenerator.Location getRandomLocation(double latitude, double longitude, int radiusKM) {
        Random random = new Random();

        // Convert radiusKM from meters to degrees
        double radiusMeters = radiusKM * 1000;
        double radiusInDegrees = radiusMeters / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(Math.toRadians(latitude));

        double foundLongitude = new_x + longitude;
        double foundLatitude = y + latitude;
        return new RandomLocationGenerator.Location(foundLatitude, foundLongitude);
    }

    public static List<RandomLocationGenerator.Location> getRandomLocations(int amountOfLocationsToGenerate, double longitude, double latitude, int radiusKM) {
        List<RandomLocationGenerator.Location> locations = new ArrayList<>();
        for (int i = 0; i < amountOfLocationsToGenerate; i++) {
            locations.add(getRandomLocation(longitude, latitude, radiusKM));
        }
        return locations;
    }
}
