package com.example.tdd.mockio;

public class Home {
    private Person mPerson;

    public Home(Person person) {
        mPerson = person;
    }

    public String getMaster(){
        return mPerson.getName();
    }
}
