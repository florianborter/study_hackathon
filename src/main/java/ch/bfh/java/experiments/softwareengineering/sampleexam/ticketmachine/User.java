package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

public class User {

    public static void main(String[] args) {
        TicketMachine ticketMachine = new TicketMachine();

        ticketMachine.on();
        ticketMachine.reset();
        ticketMachine.off();
    }

}
