// Kirsty Alexandra Nguegang

package Lab08;

import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Comparator;
import java.util.Scanner;

public class SchedulingSimulation
{
    private PriorityQueue<Job> waitingJobs;
    private ArrayDeque<Job> allJobs;
    private Job currentJob;
    private int numOfExecutions;            // determines whether job should be polled if currentJob.getTimeLeft() > TIME_SLICE_PER_JOB

    public static final int TIME_SLICE_PER_JOB = 3;
    public static final int SIMULATION_DURATION = 100;
    public static final int JOB_PROBABILITY  = 30;
    public static final int JOB_PRIORITY = 4;
    public static final int JOB_MIN_TIME = 1;
    public static final int JOB_MAX_TIME = 5;
    public static final int SORT_BY_PRIORITY = 1; // the number 1 determines whether the program will sort by priority
    public static final int SORT_BY_LENGTH = 2;  // the number 2 determines whether the program will sort by length
    public Random generator = new Random(101);

    public SchedulingSimulation(int num)
    {
        if (num == SORT_BY_LENGTH)
        {
            this.waitingJobs = new PriorityQueue<Job>(new Comparator<Job>()
            {
                public int compare(Job job1, Job job2)
                {
                    return job1.getTimeLeft() - job2.getTimeLeft();
                }
            });
        }
        else
        {
            this.waitingJobs = new PriorityQueue<Job>();
        }
        this.allJobs = new ArrayDeque<Job>();
    }

    public void runSimulation()
    {
        if (this.currentJob == null )
        {
            System.out.println("        executing: NONE");

        }
        else
        {
            if (!this.currentJob.isFinished())  // if currentJob is not finished, update currentJob and increment numOfExecutions
            {
                System.out.println("        executing: " + this.currentJob.toString());
                this.currentJob.update(this.currentJob.getTimeLeft()); // to reduce the time left
                this.numOfExecutions++;
            }
            if (this.currentJob != null && this.currentJob.isFinished())
            {
                System.out.println("        completed: Job #"  + this.currentJob.getJobsCreated() + " at time ");
                this.currentJob = this.waitingJobs.poll();
                this.numOfExecutions = 0;
            }
        }
    }

    public void generateJob(int startTime)
    {
         int randomProbability = this.generator.nextInt(JOB_PROBABILITY + 1);
         if (randomProbability > (int)(JOB_PROBABILITY * 0.50)) // probability must be greater than 50% for job to be generated
         {
            int randomPriority = this.generator.nextInt(JOB_PRIORITY - 1 + 1) + 1;
            int randomTimeLeft = this.generator.nextInt(JOB_MAX_TIME - JOB_MIN_TIME + 1) + JOB_MIN_TIME;   // random timeLeft generated between 1 and 5 (both inclusive)
            Job job = new Job(randomPriority, startTime, randomTimeLeft);
            this.allJobs.add(job);                                                     // populating allJobs arraydeque
              System.out.println("        created: " + job.toString());

            if (waitingJobs.size() == 0 && (this.currentJob == null))
            {
                this.currentJob = job;
            }
            else
            {
                this.waitingJobs.offer(job);
                if (this.waitingJobs.size() > 0 && this.numOfExecutions >= TIME_SLICE_PER_JOB)
                {
                    this.waitingJobs.offer(this.currentJob);
                    this.currentJob = this.waitingJobs.poll();
                    this.numOfExecutions = 0;
                }
            }
         }

    }

    public void reportAtTimeMarker(int simulationTimer)
    {
        int waiting = this.waitingJobs.size();
        System.out.println("Time Marker " + simulationTimer + "         waiting: " + waiting);
    }

    public void finalSimulationReport()
    {
        int waitCount = 0;
        int unfinishedJobWait = 0;
        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println();
        System.out.println("********************** Final Report: ***************************");
        System.out.println("    Active jobs: ");
        while (!this.waitingJobs.isEmpty())
        {
            unfinishedJobWait += this.waitingJobs.peek().getTimeLeft();
            System.out.println(this.waitingJobs.poll().toString());
            waitCount++;
        }
        System.out.println();
        System.out.println("The number of jobs currently executing is " + waitCount);
        System.out.println("The number of completed jobs is " + (this.allJobs.size() - waitCount));
        System.out.println("The total number of jobs is " + this.allJobs.size());
        System.out.println("The average time left for unfinished jobs is " + df.format((double) unfinishedJobWait / waitCount)); 
    }

    public static void main(String [] args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter either 1 or 2: 1 - for sort by priority, 2 - for sort by length");
        int num = keyboard.nextInt();

        SchedulingSimulation simulator = new SchedulingSimulation(num);
        for (int clock = 0; clock < simulator.SIMULATION_DURATION; clock++)
        {
            simulator.reportAtTimeMarker(clock);
            simulator.runSimulation();
            simulator.generateJob(clock);
        }
        simulator.finalSimulationReport();
    }
}
