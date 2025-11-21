public interface HourlyTaskScheduler extends HourlyTaskSchedulerKernel {

    /**
     * Removes the specified task from the scheduler.
     *
     * @param t
     *            the task to remove
     * @updates this
     * @requires t != null
     * @ensures this = #this with t removed from all hours it was in
     */
    void removeTask(Task t);

    /**
     * Prints all tasks at a given time.
     *
     * @param h
     *            the hour to print
     * @requires 0 <= hour < 24
     * @ensures output = task names during that hour
     */
    void printTasks(int h);

    /**
     * Prints al tasks of a day.
     *
     * @ensures output = all tasks scheduled during the day
     */
    void printAllScheduledTasks();

}