package com.example.tdd;

import java.util.Objects;

public class Dollar {
    private int amount;

    public Dollar(int amount) {
        this.setAmount(amount);
    }

    public Dollar times(int multiplier) {
        return new Dollar(getAmount() *multiplier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dollar dollar = (Dollar) o;
        return getAmount() == dollar.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
