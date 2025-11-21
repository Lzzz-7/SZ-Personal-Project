import javax.sound.midi.Sequence;

/**
 * Concrete implementation of the HourlyTaskScheduler component.
 *
 * @Representation: Map<Integer, Sequence<String>> where each key is an hour
 *                  (0–23) and the value is a sequence of task names scheduled
 *                  during that hour.
 *
 * @Convention: - All map keys are between 0 and 23 inclusive. - No duplicate
 *              task names within the same sequence (i.e., per hour). - Each
 *              task name is non-null and non-empty.
 *
 * @Correspondence: - A Task scheduled from startTime to endTime appears in the
 *                  sequences for each hour h such that startTime ≤ h < endTime.
 *                  - The set of all tasks scheduled is the union of all values
 *                  in the map.
 */
public class HourlyTaskScheduleKernel extends HourlyTaskScheduleAbstract {

    /**
     * Adds a task name to all relevant hour blocks.
     *
     * @param t
     *            the task to add into the map
     * @updates this the map that contains tasks
     * @requries t != null
     * @ensures #this = this * t
     */
    public static void addTask(Task t) {
        for (int i = (int) t.startTime(); i < (int) t.endTime(); i++) {
            if (timeBlocks.hasKey(i)) {
                timeBlocks.value(i).add(timeBlocks.value(i).length(), t.name());
            } else {
                Sequence<String> newSequence = new Sequence1L<>();
                newSequence.add(0, t.name());
                timeBlocks.add(i, newSequence);
            }
        }
    }

    /**
     * Checks if you're available at a given hour.
     *
     * @param h
     *            the hour to check
     * @return whether it is available at time h
     * @ensures return false if this.hasKey(h) and otherwise
     */
    public static boolean ifAvailable(int h) {
        return !timeBlocks.hasKey(h) || timeBlocks.value(h).length() == 0;
    }
}
