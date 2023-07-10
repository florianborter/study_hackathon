package ch.bfh.java.experiments.softwareengineering.statepattern;

public class StopwatchContext {
    private StopwatchState state;
    private double time = 0;

    public StopwatchContext() {
        transitionState(new IdleState(this));
    }

    public void handleB1() {
        state.handleB1();
    }

    public void handleB2() {
        state.handleB2();
    }

    public void updateDisplay() {
        System.out.println("Current time: " + time / 1000);
    }

    public void transitionState(StopwatchState state) {
        if (this.state != null) {
            this.state.exit();
        }
        this.state = state;
        this.state.entry();
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public StopwatchState getState() {
        return state;
    }
}
