package ch.bfh.java.experiments.softwareengineering.statepattern;

public class RunningState extends StopwatchState {
    private boolean isRunning = false;

    public RunningState(StopwatchContext context) {
        super(context);
    }

    @Override
    public void entry() {
        System.out.println("Entered running state");
        // start timer
        Thread updaterThread = new Thread(() -> {
            while (isRunning) {
                context.setTime(context.getTime() + 100);
                context.updateDisplay();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        isRunning = true;
        updaterThread.start();
    }
    @Override
    public void exit() {
        isRunning = false;
    }

    @Override
    public void handleB1() {
        // transition to intermediate state
        context.transitionState(new IntermediateState(context));
    }

    @Override
    public void handleB2() {
        // transition to stopped state
        context.transitionState(new StoppedState(context));
    }
}
