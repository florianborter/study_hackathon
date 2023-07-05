package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

public abstract class MachineState {
    protected TicketMachine ticketMachine;

    protected MachineState(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    public abstract void onOn();

    public abstract void onOff();

    public abstract void onReset();

    public abstract void onEnter();

    public void moveToNext() {
        //do nothing
    }

}
