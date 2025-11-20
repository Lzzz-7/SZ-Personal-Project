import java.util.Map;

import javax.sound.midi.Sequence;

import components.map.Map1L;
import components.sequence.Sequence1L;

public class HourlyTaskScheduler {

    private static Map<Integer, Sequence<String>> timeBlocks = new Map1L<>();

    /**
     * Adds a task name to all relevant hour blocks.
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
     * Prints all tasks at a given hour.
     */
    public static void printTasks(int h) {
        System.out.print("During the time at " + h + ":00, you have ");
        if (timeBlocks.hasKey(h)) {
            Sequence<String> tasks = timeBlocks.value(h);
            for (int i = 0; i < tasks.length(); i++) {
                System.out.print(tasks.entry(i) + ", ");
            }
        } else {
            System.out.print("no plans ");
        }
        System.out.println("to do.");
    }

    /**
     * Checks if you're available at a given hour.
     */
    public static boolean ifAvailable(int h) {
        return !timeBlocks.hasKey(h) || timeBlocks.value(h).length() == 0;
    }

    /**
     * Prints all scheduled tasks in the day by hour.
     */
    public static void printAllScheduledTasks() {
        for (int h = 0; h < 24; h++) {
            printTasks(h);
        }
    }

    public static void remove(Task t) {
        for (int i = (int) t.startTime(); i <= (int) t.endTime(); i++) {
            if (timeBlocks.hasKey(i)) {
                Sequence<String> taskList = timeBlocks.value(i);
                Sequence<String> tempList = taskList.newInstance();
                for (int i = 0; i < taskList.size(); i++) {
                    String temp = taskList.remove(i);
                    if (!temp.equals(t.name())) {
                        tempList.add(temp);
                    }
                }
                while (tempList.size() > 0) {
                    String temp2 = tempList.remove(0);
                    taskList.add(temp2);
                }
            }
        }
    }

    public static void main(String[] args) {
        Task t1 = new Task("Homework", 14.0, 16.0);
        Task t2 = new Task("Lunch", 12.0, 13.0);
        addTask(t1);
        addTask(t2);

        printTasks(14);
        printTasks(15);
        printTasks(12);
        printTasks(9);

        System.out.println("Available at 14? " + ifAvailable(14));
        System.out.println("Available at 10? " + ifAvailable(10));
    }
}
