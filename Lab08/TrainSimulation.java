package Lab08;

import java.text.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * A class that simulates a train line with passengers.
 *
 * @author Kirsty Alexandra Nguegang
 * @version 3/13/2018
 */
public class TrainSimulation
{
    // an array that will hold all stations
    private Station[] allStations;
    // a queue that will hold all passengers, so we can print statistics
    // when the simulation is over
    private Queue<Passenger> allPassengers;
    // a queue that will hold all trains
    private Queue<Train> allTrains;
    // keeps track of the number of trains en route
    private int trainCount;
    // total number of passengers created
    private int passengersCreated;
    // total number of passengers on the trains
    private int passengersOnTrains;
    // total number of passengers off the trains
    private int passengersDelivered;
    // number of stations for the simulation
    private final int STATIONS = 10;
    private final int FINAL_STATION = STATIONS - 1;
    // frequency of trains departing from the station 0
    private final int TRAIN_INTERVAL = 5;
    // max number of passengers per train
    private final int TRAIN_CAPACITY = 20;
    // simulation time
    private final int DURATION = 50;
    // time range between two stations
    private final int MIN_TIME_TO_NEXT_STATION = 5;
    private final int MAX_TIME_TO_NEXT_STATION = 9;
    // max number of passengers to be randomly generated in one simulation tick
    private final int MAX_NUM_OF_PASSENGERS = 10;

    public Random generator;

    public TrainSimulation()
    {
        // create an array that will hold all stations
        this.allStations = new Station[STATIONS];
        // create a queue that will hold all trains
        this.allTrains = new ArrayDeque<>();
        // create a queue that will hold all passengers
        this.allPassengers = new ArrayDeque<>();
        // initialize counters
        this.trainCount = 0;
        this.passengersCreated = 0;
        this.passengersOnTrains = 0;
        this.passengersDelivered = 0;
        // create Random object to be used for generating random values
        this.generator = new Random(101); // how come when i set the seed to 101, my output still can't match??!!!!
        // generate all stations
        generateStations();
    }

    public void generateStations()
    {
        // TODO Project 3 - Step #1
        // fill the allStation array with Station objects where the value
        // of "time to next station" is randomly generated.
        // for each created station print the station's "time to next"
        System.out.println("--> Creating 10 stations: ");
        for (int i = 0; i < this.allStations.length; i++)
        {
           int timeToNext = this.generator.nextInt((MAX_TIME_TO_NEXT_STATION - MIN_TIME_TO_NEXT_STATION) + 1) + MIN_TIME_TO_NEXT_STATION;
           this.allStations[i] = new Station(i, timeToNext);
           System.out.println(this.allStations[i].toString());
        }
        System.out.print("\n");
    }

    public void startNewTrain(int clock)
    {
        // TODO Project 3 - Step #3

        // create new train object and add it to the allTrains queue
        // print the new train object
        // increment the train count by 1
        if ((clock % TRAIN_INTERVAL) == 0)
        {
            Train t = new Train(TRAIN_CAPACITY);
            this.allTrains.add(t);
            System.out.println("New train: " + t.toString());
            this.trainCount++;
        }
    }

    public void generatePassengers(int clock)
    {
        // randomly generate number of new passengers
        int newPassengers = this.generator.nextInt(MAX_NUM_OF_PASSENGERS) + 1;
        int startStation, destStation;
        for (int i = 0; i < newPassengers; i++)
        {
            startStation = this.generator.nextInt(STATIONS);
            destStation = this.generator.nextInt(STATIONS);
            if (startStation < destStation)
            {
                Passenger p = new Passenger(startStation, destStation, clock);
                this.allPassengers.add(p);
                this.passengersCreated++;
                allStations[startStation].addPassenger(p);
                System.out.println(p.toString());
            }
        }
        // add each passenger to the allPassengers queue and increment the number
        // of passengers created - you will need this information for the statistics

        // add each passenger to its appropriate start station - use allStations array
    }

    public void moveTrains(int clock)
    {
        System.out.println("\n>> Moving each train <<");
        int trainsToCheck = this.trainCount;

        for (int i = 0; i < trainsToCheck; i++)
        {
            // TODO Project 3 - Step #4
            // 1. remove train from the allTrains queue
            // 2. move the train (see the Train class)
            // 3. if the train's time to the next station is 0 the train is at the station
            //    a. unload the passengers from the train
            //    b. load the waiting passengers on the train
            //    c. update the passenger counters
            //    d. if the train is at the final station
            //            print the appropriate message and decrement the number of trains
            //       otherwise update train's next station data: train.updateStation(allStations[stationNo].getTimeToNextStation())
            //            put the train back on the queue, and print the the train object
            // 4. if the train's time to the next station is not 0 the train is still in transit
            //            so put it back to the queue and print the train object
            Train t = this.allTrains.remove();
            t.move();
            if (t.getTimeToNext() == 0)
            {
                int delivered = t.unloadPassengers(t.getNextStation());
                this.passengersDelivered += delivered;
                this.passengersOnTrains +=  t.loadPassengers(this.allStations[t.getNextStation()], clock) - delivered;

                if (t.getNextStation() == FINAL_STATION)
                {
                    System.out.println(t.toString());
                    this.trainCount--;
                }
                else
                {
                    t.updateStation(this.allStations[i].getTimeToNextStation());
                    this.allTrains.add(t);
                    System.out.println(t.toString());
                }
            }
            else
            {
                this.allTrains.add(t);
                System.out.println(t.toString());
            }
        }
    }

    public void reportAtTimeMarker(int simulationTimer)
    {
        int passengersWaiting = this.passengersCreated - this.passengersOnTrains - this.passengersDelivered;

        System.out.println("-----At time marker " + simulationTimer + " -> passengers waiting: " + passengersWaiting +
                "\t on trains: " + this.passengersOnTrains + "\t active trains: " + this.trainCount + "-----");
        System.out.println();
    }

    public void finalSimulationReport(int clock)
    {
        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println("*****************  Final Report  ***************");
        System.out.println("The total number of passengers is " + this.passengersCreated);
        System.out.println("The number of passengers currently on a train " + this.passengersOnTrains);
        System.out.println("The number of passengers delivered is " + this.passengersDelivered);
        int passengersWaiting = this.passengersCreated - this.passengersOnTrains - this.passengersDelivered;
        System.out.println("The number of passengers waiting is " + passengersWaiting);

        int waitBoardedSum = 0;
        Passenger passenger;
        for (int i = 0; i < this.passengersCreated; i++)
        {
            passenger = this.allPassengers.poll();
            if (passenger.boarded())
            {
                waitBoardedSum += passenger.waitTime(clock);
            }
        }
        System.out.print("The average wait time for passengers that have boarded is ");
        System.out.println(df.format((double) waitBoardedSum / (this.passengersOnTrains + this.passengersDelivered)));
    }

    public static void main(String args[])
    {
        System.out.println("**************  TRAIN SIMULATION  **************\n");
        TrainSimulation simulator = new TrainSimulation();

        System.out.println("--> Starting the clock; duration set to " + simulator.DURATION + "\n");
        for (int clock = 0; clock < simulator.DURATION; clock++)
        {
            simulator.generatePassengers(clock);
            simulator.startNewTrain(clock);
            simulator.moveTrains(clock);
            simulator.reportAtTimeMarker(clock);
        }
        simulator.finalSimulationReport(simulator.DURATION);
    }
}
