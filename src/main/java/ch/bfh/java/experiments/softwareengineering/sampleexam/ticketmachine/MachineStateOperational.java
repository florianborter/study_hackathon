package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

public abstract class MachineStateOperational extends MachineState {
    protected MachineStateOperational(TicketMachine ticketMachine) {
        super(ticketMachine);
    }

    public abstract void next();

    @Override
    public void onOn() {
        ticketMachine.setState(new Idle(ticketMachine));
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
    public void moveToNext() {
        next();
    }
}
