package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for filtering the list of flights
 */
public class FlightFilterImpl implements FlightFilter{

    private final List<Segment> segs = new ArrayList<>();
    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final List <Flight> tempListFlights = new ArrayList<>();


    /**
     * Method of displaying all flights
     */

    @Override
    public void allFlightSegments(List<Flight> flights) {
        for (Flight flight : flights) {
            for (int i = 0; i < flight.getSegments().size(); i++) {
                System.out.println(flight.getSegments().get(i));
            }
        }

    }

    /**
     * Method of displaying departures to the current point in time.
     */

    @Override
    public List<Flight> departureToTheCurrentPointInTime(List<Flight> flights) {
        for (Flight flight : flights) {
            segs.addAll(flight.getSegments());
            while (segs.size() > 0) {
                LocalDateTime dep = segs.get(0).getDepartureDate();
                LocalDateTime arr = segs.remove(0).getArrivalDate();
                if (dep.isBefore(currentDateTime)) {
                    showFlight(flight, dep, arr);
                    tempListFlights.add(flight);
                }
            }
        }
        return tempListFlights;
    }

    /**
     * Method of displaying flights, with arrival date earlier than departure date.
     */
    @Override
    public List<Flight> thereAreSegmentsWithTheArrivalDateEarlierThanTheDepartureDate(List<Flight> flights) {
        for (Flight flight : flights) {
            segs.addAll(flight.getSegments());
            while (segs.size() > 0) {
                LocalDateTime dep = segs.get(0).getDepartureDate();
                LocalDateTime arr = segs.remove(0).getArrivalDate();
                if (arr.isBefore(dep)) {
                    showFlight(flight, dep, arr);
                    tempListFlights.add(flight);
                }
            }
        }
        return tempListFlights;
    }

    /**
     * Method of displaying flights, time spent on the ground more than two hours.
     */

    @Override
    public List<Flight> theTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flights) {
        for (Flight flight : flights) {
            segs.addAll(flight.getSegments());
            if (segs.size() > 2) {
                while (segs.size() > 2) {
                    LocalDateTime arr = segs.remove(1).getArrivalDate();
                    LocalDateTime dep = segs.remove(1).getDepartureDate();
                    if (arr.plusHours(2).isBefore(dep)) {
                        showFlight(flight, dep, arr);
                        tempListFlights.add(flight);
                    }
                }
            }
        }
        return tempListFlights;
    }

    /**
     * Method of displaying flight information (Inside the class)
     */

    private void showFlight (Flight flight, LocalDateTime dep, LocalDateTime arr){
        System.out.println("departure: " + dateTimeFormatter.format(dep)+ "\n"
                + "arrival: " + dateTimeFormatter.format(arr) + "\n");
    }
}
