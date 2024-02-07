/**
 * We are adding a new feature to the Dispatch system at Axon, which will allow us to determine the
 * average travel time of police units between different locations.
 *
 * <p>Implement a DispatchTravel class that is initialized with a List of data points related to
 * dispatched police units (unitId, location, timestamp), and that is able to return the AVERAGE
 * travel time from any one location to another through the following method: getAverageTime(String
 * startLocation, String endLocation)
 *
 * <p>The average travel time is computed from all trips that happened directly FROM startLocation
 * TO endLocation.
 *
 * <p>Input format: [[unitId, location, timestamp], [unitId, location, timestamp], ...]
 *
 * <p>When a Unit ID is first encountered, it means the unit was dispatched and a trip started from
 * that location and timestamp. When that same Unit ID is encountered again, it means the trip ended
 * at that location and timestamp. The travel time is the difference in timestamps.
 *
 * <p>Example:
 * input = [ [1, "SW Station", 3],
 * [2, "Clovis", 4],
 * [3, "SW Station", 5],
 * [1, "Mayfair", 10],
 * [3, "Mayfair", 20],
 * [2, "Downtown Fresno", 25] ]
 * dt = DispatchTravel(input)
 * dt.getAverageTime("SW Station", "Mayfair") -------> (7+15)/2 = 11.0
 * dt.getAverageTime("Clovis", "Downtown Fresno") -------> 21.0
 *
 * <p>Explanation: - Unit with ID 1 travels from SW Station at timestamp 3 to Mayfair at timestamp
 * 10 (Travel time = 7) - Unit with ID 3 travels from SW Station at timestamp 5 to Mayfair at
 * timestamp 20 (Travel time = 15) --> Average travel time from SW Station to Mayfair = 11.0
 * (average of the 2 trips)
 *
 * <p>- Unit with ID 2 travels from Clovis at timestamp 4 to Downtown Fresno at timestamp 25 (Travel
 * time = 21) --> Average travel time from Clovis to Downtown Fresno = 21.0 (only 1 trip recorded)
 */
import java.io.*;
import java.util.*;

public class TravelTime {
  public static void main(String[] args) {
    DispatchDetails[] input = new DispatchDetails[6];
    input[0] = new DispatchDetails(1, "SW Station", 3);
    input[1] = new DispatchDetails(2, "Clovis", 4);
    input[2] = new DispatchDetails(3, "SW Station", 5);
    input[3] = new DispatchDetails(1, "Mayfair", 10);
    input[4] = new DispatchDetails(3, "Mayfair", 20);
    input[5] = new DispatchDetails(2, "Downtown Fresno", 25);

    DispatchTravel dispatchTravel = new DispatchTravel(input);
    System.out.println(dispatchTravel.getAverageTime("SW Station", "Mayfair"));
    System.out.println(dispatchTravel.getAverageTime("Clovis", "Downtown Fresno"));
  }

  /*
  Individual instance of the unit dispatch
   */
  static class DispatchDetails {
    int unitId;
    String location;
    int ts;

    public DispatchDetails(int unitId, String location, int ts) {
      this.unitId = unitId;
      this.location = location;
      this.ts = ts;
    }
  }

  /*
  Extracted trip details containing location and ts
   */
  static class TripDetail {
    String location;
    int ts;

    public TripDetail(String location, int ts) {
      this.location = location;
      this.ts = ts;
    }
  }

  /*
  Maintaining location stats `
   */
  static class LocationStat {
    int time;
    int count;

    public void addTime(int moreTime) {
      this.time += moreTime;
    }

    public void incrementTripCount() {
      this.count++;
    }

    @Override
    public String toString () {
      return new String( "Time : " + time + " Count : " + count);
    }
  }

  /*
   Processing class
  */
  static class DispatchTravel {

    DispatchDetails[] input;
    // Track trip details for a unit
    // This is used to tracke the trips of a unit. When a unit completes a trip
    // The src - destination details are updated in the other map.
    Map<Integer, TripDetail> unitTrip = new HashMap<>();
    // Tracks the actual src-destination location pair and the
    // distance and the no. of trip taken
    Map<String, LocationStat> locationData = new HashMap<>();

    public DispatchTravel(DispatchDetails[] input) {
      this.input = input;
      process();
    }

    private void process() {
      for (DispatchDetails travel : input) {
        if (unitTrip.containsKey(travel.unitId)) {
          // The unit trip is already present so the current trip
          // must be information about destination
          TripDetail startTrip = unitTrip.get(travel.unitId);

          String startLocation = startTrip.location;
          int startTime = startTrip.ts;

          String endDestination = travel.location;
          int endTime = travel.ts;

          String tripKey = startLocation + "$" + endDestination;
          LocationStat currStats = null;
//          System.out.println("Trip Key : " + tripKey);
          if (locationData.containsKey(
              tripKey)) { // check if the pair of src - destination already exists
            currStats = locationData.get(tripKey);
          } else {
            currStats = new LocationStat();
          }
          currStats.addTime(endTime - startTime); // Add travel time
          //System.out.println(endTime - startTime);
          currStats.incrementTripCount(); // number of trip between src - destination pain
          locationData.put(tripKey, currStats);
          // The given unit has completed the trip between source and
          // destination so remove it from the tracking
          unitTrip.remove(travel.unitId);
        } else {
          // The tracking map does not contain any information for this unit
          // So create one
          TripDetail startTrip = new TripDetail(travel.location, travel.ts);
//          System.out.println(travel.location + " ::: " +  travel.ts);
          // Track the information in the map
          unitTrip.put(travel.unitId, startTrip);
        }
      }
    }

    public double getAverageTime(String start, String end) {
      String tripKey = start + "$" + end;
      // Check for availability of src - destination pair and return 0 or null
      LocationStat currLocationStat = locationData.get(tripKey);
      System.out.println("Curr Location Stats :  " + start + " and " + end + " :: " + currLocationStat);
      return (double) currLocationStat.time / currLocationStat.count;
    }
  }
}
