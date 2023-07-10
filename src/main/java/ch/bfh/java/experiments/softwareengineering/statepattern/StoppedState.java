package ch.bfh.java.experiments.softwareengineering.statepattern;

public class StoppedState extends StopwatchState {
    public StoppedState(StopwatchContext context) {
        super(context);
    }

    @Override
    public void entry() {
        System.out.println("Entered stopped state");
    }

    @Override
    public void handleB1() {
        // resume timer - back to running state
        context.transitionState(new RunningState(context));
    }

    @Override
    public void handleB2() {
        // reset timer - back to idle
        context.transitionState(new IdleState(context));
    }
}
