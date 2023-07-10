package ch.bfh.java.experiments.softwareengineering.statepattern;

public class Main {

    public static void main(String[] args) {
        StopwatchContext context = new StopwatchContext();

        new Thread(() -> {
            try {
                // Start stopwatch
                context.handleB1();
                // assert stopwatch running
                assert context.getState() instanceof RunningState : "Stopwatch should be in running state";
                // Wait 1 second
                Thread.sleep(1000);
                // Stop
                context.handleB2();
                // assert stopwatch stopped
                assert context.getState() instanceof StoppedState : "Stopwatch should be in stopped state";
                // Reset
                context.handleB2();
                // assert stopwatch idle
                assert context.getState() instanceof IdleState : "Stopwatch should be in idle state";

                // Start stopwatch
                context.handleB1();
                // assert stopwatch running
                assert context.getState() instanceof RunningState : "Stopwatch should be in running state";
                // Wait 0.5 second
                Thread.sleep(500);
                // Pause
                context.handleB1();
                // Wait 1 second
                Thread.sleep(1000);
                // Resume
                context.handleB1();
                // Wait 0.5 second
                Thread.sleep(500);
                // Stop
                context.handleB2();
                // assert stopwatch stopped
                assert context.getState() instanceof StoppedState : "Stopwatch should be in stopped state";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
