package ch.bfh.java.experiments.softwareengineering.statepattern;

public class IdleState extends StopwatchState {
    public IdleState(StopwatchContext context) {
        super(context);
    }

    @Override
    public void entry() {
        context.setTime(0);
        context.updateDisplay();
        System.out.println("Entered idle state");
    }

    @Override
    public void handleB1() {
        // Handle b1, transition to running state
        context.transitionState(new RunningState(context));
    }

    // Nothing happens on b2...
}
