package ch.bfh.java.experiments.softwareengineering.actorrole;

public abstract class Actor {
    protected Role role;

    protected String name;

    public Actor(Role role, String name) {
        this.role = role;
        this.name = name;
    }

    public abstract void work();


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
