package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

import java.util.Random;

public class SelfDiagnosis extends MachineState {
    public SelfDiagnosis(TicketMachine ticketMachine) {
        super(ticketMachine);
    }

    @Override
    public void onOn() {
        //do nothing
    }

    @Override
    public void onOff() {
        ticketMachine.setState(new Off(ticketMachine));
    }

    @Override
    public void onReset() {
        //do nothing
    }

    @Override
    public void onEnter() {
        System.out.println("diagnosis beep boop");
        int diag = new Random().nextInt(10);

        if (diag > 5) { //successful
            System.out.println("successful");
            ticketMachine.setState(new Idle(ticketMachine));
        } else { //unsuccessful
            System.out.println("unsuccessful");
            ticketMachine.setState(new OutOfOrder(ticketMachine));
        }
    }
}
