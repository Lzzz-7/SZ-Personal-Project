public interface HourlyTaskSchedulerKernel {
    /**
     * Adds task to the scheduler.
     *
     * @param t
     *            the task to add
     * @updates this
     * @requires t != null
     * @ensures this = #this with t added in all hours from start to end
     */
    void addTask(Task t);

    /**
     * Reports whether the user is busy at the given time in the scheduler.
     *
     * @param time
     *            the time to check
     * @return true if there is a task scheduled at that time
     * @requires 0.0 <= time < 24.0
     * @ensures isBusyAt = (there is a task that overlaps with time)
     */
    boolean ifAvailable(double time);

    /**
     * Prints all tasks at a given time.
     *
     * @param hour
     *            the hour to print
     * @requires 0 <= hour < 24
     * @ensures output = task names during that hour
     */
    void printTasksAtHour(int hour);
}
