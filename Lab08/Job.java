// Kirsty Alexandra Nguegang

package Lab08;

public class Job implements Comparable<Job>
{
    private int jobNo;
    private int priority;
    private int createdAtTime;
    private int timeLeft;
    private static int jobsCreated = 0;

    public Job(int priority, int createdAtTime, int timeLeft) // still figuring what a, b and c are
    {
        this.priority = priority;
        this.createdAtTime = createdAtTime;
        this.timeLeft = timeLeft;
        jobsCreated++; // increment number of jobs created
        this.jobNo = jobsCreated;

    }

    public int compareTo(Job j) //compares two Jobs' priorities and returns the smaller one
    {
        int result = 0;
        if (this.priority < j.priority)
        {
            result = -1;
        }
        else if (this.priority == j.priority)
        {
            result = 0;
        }
        else
        {
            result = 1;
        }
        return result;
    }

    public int getTimeLeft()
    {
        return this.timeLeft;
    }

    public int getJobsCreated()
    {
        return this.jobsCreated;
    }

    public void update(int timeToNextJob)
    {
        this.timeLeft--;
    }

    public boolean isFinished()
    {
        return this.timeLeft == 0;      // if time left == 0, then the job is complete
    }

    public String toString()
    {
        return "Job #" + this.jobNo + " priority("   + this.priority +  "), created at " + this.createdAtTime + ", time left " + this.timeLeft;

    }
}
