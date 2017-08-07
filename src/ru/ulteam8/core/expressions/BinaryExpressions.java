package ru.ulteam8.core.expressions;

import ru.ulteam8.core.interfaces.Expression;

public class BinaryExpressions implements Expression {

    private final char operation;
    private final Expression expr1, expr2;

    public BinaryExpressions(char operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }


    @Override
    public double eval() {
        double result = 0.0;

        switch (this.operation){
            case '-':
                result  = this.expr1.eval() - this.expr2.eval();
                break;
            case '*':
                result  = this.expr1.eval() * this.expr2.eval();
                break;
            case '/':
                result  = this.expr1.eval() / this.expr2.eval();
                break;

            default:
                result  = this.expr1.eval() + this.expr2.eval();
                break;
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", this.expr1, this.operation, this.expr2);
    }
}
