package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

public class CalculateBalance extends MachineStateOperational {
    int price;
    int balance;

    //TextField balanceField

    public CalculateBalance(TicketMachine ticketMachine, int price, int balance/*, TextField balanceField*/) {
        super(ticketMachine);
        this.price = price;
        this.balance = balance;
        //this.balanceField = balanceField;
    }

    @Override
    public void onEnter() {
        System.out.println("calculating balance");
        // leave display as it is

        if (balance < price) {
            ticketMachine.setState(new TakeMoney(ticketMachine, price, balance/*, balanceField*/));
        } else {
            ticketPurchased();
        }
    }

    private void ticketPurchased() {
        //print ticket
        //return change if necessary
        //sout("Ticket is being printed");

        ticketMachine.setState(new Idle(ticketMachine));
    }

    @Override
    public void onReset() {
        //do nothing
    }

    @Override
    public void next() {
        // do Nothing
    }


}
