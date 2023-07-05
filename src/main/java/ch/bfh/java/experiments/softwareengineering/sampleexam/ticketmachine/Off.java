package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

public class Off extends MachineState{
    public Off(TicketMachine ticketMachine) {
        super(ticketMachine);
    }

    @Override
    public void onOn() {
        ticketMachine.setState(new SelfDiagnosis(ticketMachine));
    }

    @Override
    public void onOff() {
        //do Nothing
    }

    @Override
    public void onReset() {
        //do nothing
    }

    @Override
    public void onEnter() {
        System.out.println("Machine shut down");
    }
}
