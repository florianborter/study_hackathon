package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

import java.util.Random;

public class OutOfOrder extends MachineState{
    public OutOfOrder(TicketMachine ticketMachine) {
        super(ticketMachine);
    }

    @Override
    public void onOn() {
        //do nothing
    }

    @Override
    public void onOff() {
        //do Nothing
    }

    @Override
    public void onReset() {
        ticketMachine.setState(new SelfDiagnosis(ticketMachine));
    }

    @Override
    public void onEnter() {
        System.out.println("Out of order :(");
    }
}
