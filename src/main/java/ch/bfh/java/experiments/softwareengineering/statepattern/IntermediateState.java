package ch.bfh.java.experiments.softwareengineering.statepattern;

public class IntermediateState extends StopwatchState {
    public IntermediateState(StopwatchContext context) {
        super(context);
    }

    @Override
    public void entry() {
        System.out.println("Entered intermediate state");
    }

    @Override
    public void handleB1() {
        // transition back to running state
        context.transitionState(new RunningState(context));
    }

    @Override
    public void handleB2() {
        // transition to stopped state
        context.transitionState(new StoppedState(context));
    }
}
