package sample;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public Person() {
        this("", "");
    }

    public Person(String name, String phone) {
        setName(name);
        setPhone(phone);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String fName) {
        name.set(fName);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String fName) {
        phone.set(fName);
    }
}

