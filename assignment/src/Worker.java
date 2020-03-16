import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Worker implements Runnable {

    private final int id;  // Unique worker ID.
    
    private final JobStack jobStack;  // Reference to the job stack.
    private final ResourceStack resourceStack;  // Reference to the resource stack.
    
    private Job job;  // Job being processed.
    private Resource[] resources;  // Resources being used for job being processed.
    
    private boolean busy;  // Indicates the status of the worker. True when they are working (executing jobs) and false when there are no more jobs left to execute.
    
    private final Map<Integer, ArrayList<Integer>> jobsCompleted;  // The job record of the worker. Stores each job's ID and the IDs of the resources used for each job.
    
    // Constructor.
    public Worker(int theId, JobStack theJobStack, ResourceStack theResourceStack) {
        id = theId;
        jobStack = theJobStack;
        resourceStack = theResourceStack;
        job = null;
        busy = true;
        jobsCompleted = new TreeMap<>();
    }

    
    /// UNDER CONSTRUCTION /////////////////////////////////////////////////////

    //private static Lock threadLock = new ReentrantLock();
   
    public void run() {
       
    System.out.println("Worker " + id + " started.");
        
    while(jobStack.getSize() >= -1) {
        
        try {
            
            job = jobStack.pop();
            int jobID = job.getId();
            ArrayList<Integer> resArrayList = new ArrayList<Integer>();

            int resSize = resourceStack.getSize();
        
            resources = resourceStack.pop(job.getResourceRequirement()); //pops resources from resource stack depending on job's resource requirement
        
            //Add resource IDs to arraylist
            for(int i = 0; i < resources.length; i++) {
            resSize = resources[i].getId();
            resArrayList.add(resSize);
            }
        
        
            //Thread sleeps for time depending on job time to complete
            try {
            Thread.sleep(job.getTimeToComplete());
            }
            catch(InterruptedException n) {
            System.out.println("Interrupted exception in Worker " + id);
            }
        
            //return resources to stack AFTER set amount of time (5ms?)
            resourceStack.push(resources);
 
            System.out.println("Worker " + id + " completed job " + jobID + ".");
        
            jobsCompleted.put(jobID, resArrayList);
            
        }
        catch(NullPointerException l) {
            
            //sets worker busy state to false when stack is empty, triggering the NullPointerException
            System.out.println("Worker " + id + " finished.");
            busy = false;
            break;
        }
    }
    
    }
    
    //sets worker state
    public boolean busyState() {
        
        boolean isBusy = busy;
        return isBusy;
    }
    
    //Prints Job IDs and the IDs of resources used for each job by the worker
    
    public void printRecords() {
        
        System.out.println("Worker: " + id);
        
        for (Map.Entry<Integer, ArrayList<Integer>> entry : jobsCompleted.entrySet()) {
            
        System.out.println("Job: " + entry.getKey() + ", Resources: " + entry.getValue());
        }
        
    }
    
    
}
    
    

