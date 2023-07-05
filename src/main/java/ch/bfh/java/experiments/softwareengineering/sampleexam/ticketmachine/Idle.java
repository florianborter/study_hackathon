package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

public class Idle extends MachineStateOperational {
    public Idle(TicketMachine ticketMachine) {
        super(ticketMachine);
    }

    @Override
    public void onEnter() {
        System.out.println("Machine Idle");
        //clear display
        //ticketMachine.setWindow(new Window())

    }

    /**
     * handle Event of selected ticket
     */
    @Override
    public void next() {
        //show price
        //TextField priceField = new Textfield(15);
        //TextField balanceField = new Textfield(0);
        //ticketMachine.getWindow().add(priceField);
        //ticketMachine.getWindow().add(balanceField);

        ticketMachine.setState(new TakeMoney(ticketMachine, 15, 0/*, balanceField*/));
    }
}
