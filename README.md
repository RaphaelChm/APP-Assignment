# APP-Assignment
this is a Java assigment centred around managing workers, jobs and resources (which workers use to complete jobs). 
The task was to complete a partially written program. This program implemented a typical workplace scenario in which a number of
jobs need to be executed. Each job requires a number of resources to be completed. These resources are reusable and returned to a pool
once the worker completes their job. 
Workers must work concurrently (using threads) but each worker can only perform one job at a time. Once complete, the worker 
appends their job record with the job's ID and the IDs of all the resources that were used to execute it. 
Finally they release/return the resources used before attempting to acquire the next job from the stack.
