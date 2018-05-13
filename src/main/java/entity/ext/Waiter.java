package entity.ext;

import entity.User;

import java.io.Serializable;

public class Waiter extends User implements Serializable,Cloneable {
    private String name;
    private String surname;
    private String email;

    public Waiter(){
        super();
    }

    public Waiter(int idWaiter, String login, String password, boolean role, String name, String surname, String email) {
        super(idWaiter, login, password, role);
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Waiter(String login, String password, boolean role, String name, String surname, String email) {
        super(login, password, role);
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
