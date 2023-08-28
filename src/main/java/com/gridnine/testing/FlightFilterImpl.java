package com.gridnine.testing;

import java.util.List;

/**
 * A class for filtering the list of flights
 */
public class FlightFilterImpl implements FlightFilter{

    @Override
    public void allFlightSegments(List<Flight> flights) {

    }

    @Override
    public List<Flight> departureToTheCurrentPointInTime(List<Flight> flights) {
        return null;
    }

    @Override
    public List<Flight> thereAreSegmentsWithTheArrivalDateEarlierThanTheDepartureDate(List<Flight> flights) {
        return null;
    }

    @Override
    public List<Flight> theTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flights) {
        return null;
    }
}
