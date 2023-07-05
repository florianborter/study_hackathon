package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

import java.util.Random;

public class TakeMoney extends MachineStateOperational{
    int price;
    int balance;

    //TextField balanceField

    public TakeMoney(TicketMachine ticketMachine, int price, int balance/*, TextField balanceField*/) {
        super(ticketMachine);
        this.price = price;
        this.balance = balance;
        //this.balanceField = balanceField;
    }

    @Override
    public void onEnter() {
        System.out.println("Taking money");
        // leave display as it is
    }

    @Override
    public void onReset() {
        //return money
        ticketMachine.setState(new Idle(ticketMachine));
    }

    /**
     * handle Event of money taken
     */
    @Override
    public void next() {
        int moneyTaken = new Random().nextInt(10);
        //increase amount on display
        //balanceField.setText(balance + moneyTaken);

        ticketMachine.setState(new CalculateBalance(ticketMachine, price, balance + moneyTaken/*, balanceField*/));
    }


}
