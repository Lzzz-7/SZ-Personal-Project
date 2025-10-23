public class Task {
    private String name;
    private double start;
    private double end;

    public Task(String name, double start, double end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String name() {
        return this.name;
    }

    public double startTime() {
        return this.start;
    }

    public double endTime() {
        return this.end;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.start + "-" + this.end + ")";
    }
}
