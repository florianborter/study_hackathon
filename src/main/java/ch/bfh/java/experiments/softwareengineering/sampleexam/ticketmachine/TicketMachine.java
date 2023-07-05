package ch.bfh.java.experiments.softwareengineering.sampleexam.ticketmachine;

import java.util.Random;

public class TicketMachine /*implements ButtonClickListener*/ {
    private MachineState state;

    //private Window window;

    public TicketMachine() {
        state = new Off(this);
        //window = new Window();
        //window.add(new Button("next", this));
    }

    /**
     * When button pressed
     */
    /*@Override
    public void buttonClicked(ClickEvent event) {

        if (event.buttonId = ON) {
            on();
        } else if (event.buttonId = OFF) {
            off();
        } else if(event.buttonId = RESET) {
            reset();
        } else {
            userNext();
        }
    }*/

    //when user input is done into machine
    /*private void userNext() {
        state.moveToNext();
    }*/

    public void on() {
        state.onOn();
    }

    public void off() {
        state.onOff();
    }

    public void reset() {
        state.onReset();
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
        state.onEnter();
    }

    /*public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }*/
}
