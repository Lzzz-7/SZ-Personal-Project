import javax.sound.midi.Sequence;

public abstract class HourlyTaskScheduleAbstract implements HourlyTaskSchedulerKernel {
    public void printTasks(int h) {
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

    public void printAllScheduledTasks() {
        for (int h = 0; h < 24; h++) {
            this.printTasks(h);
        }
    }

    public void remove(Task t) {
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

    @Override
    public abstract boolean ifAvailable(double h);

    @Override
    public abstract void addTask(Task t);
}
