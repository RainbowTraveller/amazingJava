/* Given data for an airport
 *
 * Flight no.       Landing Request Time        Taxi Time
 * 345                  14                          5
 * 344                  10                          10
 * 460                  15                          8
 *
 * Rules :
 * 1. Flights land in the order of flight number
 * 2. If the runway is not empty, delay landing request time for the corresponding planes by 10 units
 * 3. Print proper sequence of flight landings
 *
 * e.g. As per above data, flight 344 will start landing first. So the runway will be busy upto 10 + 10 = 20
 * In the meantime, flight 345 tries to land at 14, but as the runway is busy till 20, its landing time is increased to 24.
 *
 */
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Comparator;

public class FlightLanding {


    public static void main(String[] args) {
        List<Flight> flights = Arrays.asList(
                    new Flight(345, 17, 10),
                    new Flight(545, 22, 30),
                    new Flight(145, 14, 50),
                    new Flight(245, 20, 10),
                    new Flight(645, 15, 30),
                    new Flight(845, 21, 20),
                    new Flight(945, 10, 60),
                    new Flight(745, 17, 15)
                );
        FlightLanding fl = new FlightLanding();
        fl.scheduleFlightLandings(flights);
    }

    static class Flight {
        public int flightNo;
        public int landingRequestTime;
        public int taxiTime;

        public Flight(int no, int requestTime, int taxiTime) {
            this.flightNo = no;
            this.landingRequestTime = requestTime;
            this.taxiTime = taxiTime;
        }

        public String toString() {
            return "Flight No. : " + flightNo + " Landing Request Time : " + landingRequestTime + " Taxi Time : " + taxiTime;
        }
    }

    public void scheduleFlightLandings(List<Flight> flights) {
        //Using lambda instead of traditional Java7 comparator
        PriorityQueue<Flight> priorityFlights = new PriorityQueue<Flight>((f1, f2) -> f1.flightNo - f2.flightNo);

        //Using stream based internal for loop against conventional external looping
        flights.stream()
            .forEach(priorityFlights::add);//Method reference at work

        int currFlightDuration  = 0;
        while(!priorityFlights.isEmpty()){
            Flight currFlight = priorityFlights.poll();
            if(currFlightDuration >= currFlight.landingRequestTime) {
                currFlight.landingRequestTime += 10;
                System.out.println("Delayed Flight : " + currFlight);
                priorityFlights.add(currFlight);
            } else {
                System.out.println("Landing Flight : " + currFlight);
                currFlightDuration += currFlight.landingRequestTime +  currFlight.taxiTime;
            }
        }
    }
}
