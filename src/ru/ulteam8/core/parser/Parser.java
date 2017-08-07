package ru.ulteam8.core.parser;

import ru.ulteam8.core.awt.Token;
import ru.ulteam8.core.awt.TokenType;
import ru.ulteam8.core.expressions.BinaryExpressions;
import ru.ulteam8.core.expressions.NumberExpression;
import ru.ulteam8.core.interfaces.Expression;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final Token EOF = new Token(TokenType.EOF, "");
    private List<Token> tokens;
    private final int size;
    private int position;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.size = tokens.size();
    }

    public List<Expression> parse(){
        List<Expression> result = new ArrayList<>();
        while (!match(TokenType.EOF)){
            result.add(expression());
        }
        return result;
    }

    private Expression expression() {
        return additive();
    }

    private Expression additive() {
        Expression result = multiplicative();
        while (true){
            if (match(TokenType.ADDITION)){
                result = new BinaryExpressions('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.SUBTRACTION)){
                result = new BinaryExpressions('-', result, multiplicative());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression multiplicative() {
        Expression result = unary();
        while (true){
            if (match(TokenType.MULTIPLICATION)){
                result = new BinaryExpressions('*', result, unary());
                continue;
            }
            if (match(TokenType.DIVISION)){
                result = new BinaryExpressions('/', result, unary());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression unary() {
        return primary();
    }

    private Expression primary() {
        Token token = get(0);
        if (match(TokenType.NUMBER)){
            return new NumberExpression(Double.parseDouble(token.getValue()));
        }
        if (match(TokenType.LPAR)){
            Expression result = expression();
            match(TokenType.RPAR);
            return result;
        }
        throw new RuntimeException("Unknown expression");
    }

    private boolean match(TokenType type){
        Token token = get(0);
        if (type != token.getType()){
            return false;
        }

        this.position++;
        return true;
    }

    private Token get(int relativePosition){
        int position = this.position + relativePosition;
        Token token = null;

        if (this.position < this.size){
            token = this.tokens.get(this.position);
        } else {
            token = EOF;
        }

        return token;
    }
}
