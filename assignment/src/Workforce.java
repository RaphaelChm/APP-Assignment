public class Workforce {

    private final Worker[] pool;  // The worker population.
    private int workerCount = 0;  // Used to generate each worker's ID and to keep a record of the number of workers in the workforce.
    
    Thread[] workerThreads;
            
    private final JobStack jobStack;  // Reference to the job stack.
    private final ResourceStack resourceStack;  // Reference to the resource stack.
    
    // Constructor.
    public Workforce(int size, JobStack theJobStack, ResourceStack theResourceStack) {
        jobStack = theJobStack;
        resourceStack = theResourceStack;

        pool = new Worker[size];
        for(int i=0; i<pool.length; i++) {
            pool[i] = new Worker(workerCount, jobStack, resourceStack);
            workerCount++;
        }

        workerThreads = new Thread[pool.length];
        for(int i=0; i<workerThreads.length; i++) {
            workerThreads[i] = new Thread(pool[i]);
        }
    }
    
    
    /// UNDER CONSTRUCTION /////////////////////////////////////////////////////
    
    // Starts all the worker threads.
   public void start() {
        
        for(int i = 0; i < workerCount; i++) {  
            workerThreads[i].start();
        }
        
        if(allWorkersFinished() == true){
            printJobRecords();
        }
     }
    
    // Checks whether all workers have finished.
    public boolean allWorkersFinished() {   
        
        boolean allFinished = true;
        for(int i = 0; i < workerThreads.length; i ++)  {
            
            if(pool[i].busyState() == true) {
                allFinished = false;
            }
            
        }
        
        return allFinished;
    }

    // Prints the job record of all workers.
    public void printJobRecords() {
        
        //for loop, prints records for each worker
        
        for(int i = 0; i < workerThreads.length; i++) {
            pool[i].printRecords();
        }
        
    }
}