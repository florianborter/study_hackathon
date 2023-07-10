package ch.bfh.java.experiments.softwareengineering.statepattern;

public abstract class StopwatchState {
    protected StopwatchContext context;

    public StopwatchState(StopwatchContext context) {
        this.context = context;
    }

    public void entry() {}
    public void exit() {}

    public void handleB1() {}
    public void handleB2() {}
}
