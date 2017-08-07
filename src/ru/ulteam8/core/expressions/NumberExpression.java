package ru.ulteam8.core.expressions;

import ru.ulteam8.core.interfaces.Expression;

public class NumberExpression implements Expression{

    private final double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public double eval() {
        return this.value;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }
}
