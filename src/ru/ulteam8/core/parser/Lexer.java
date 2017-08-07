package ru.ulteam8.core.parser;

import ru.ulteam8.core.awt.Token;
import ru.ulteam8.core.awt.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private static final String OPERATOR_CHARS = "+-*/()";
    private static final TokenType[] OPERATOR_TOKENS = {
            TokenType.ADDITION,
            TokenType.SUBTRACTION,
            TokenType.MULTIPLICATION,
            TokenType.DIVISION,
            TokenType.LPAR,
            TokenType.RPAR
    };
    private final String input;
    private final int length;
    private final List<Token> tokens;
    private int position;


    public Lexer(String input) {
        this.input = input;
        this.length = input.length();
        this.tokens = new ArrayList<>();
    }

    public List<Token> tokenize(){
        while(this.position < this.length){
            char current = peek(0);
            if (Character.isDigit(current)) {
                tokenizeNumber();
            } else if (OPERATOR_CHARS.indexOf(current) != -1) {
                tokenizeOperator();
            } else {
                next();
            }
        }
        return  this.tokens;
    }

    private void tokenizeOperator() {
        int position = OPERATOR_CHARS.indexOf(peek(0));
        addToken(OPERATOR_TOKENS[position]);
        next();
    }

    private void tokenizeNumber() {

        StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (true){
            if (current == '.'){
                if (buffer.indexOf(".") != -1) throw new RuntimeException("Invalid float number");
            } else if (!Character.isDigit(current)){
                break;
            }

            buffer.append(current);
            current = next();
        }
        addToken(TokenType.NUMBER, buffer.toString());
    }

    private void addToken(TokenType type){
        addToken(type, "");
    }

    private void addToken(TokenType type, String value){
        this.tokens.add(new Token(type, value));
    }

    private char peek(int relativePosition){
        int position = this.position + relativePosition;
        char ch;
        if (position < this.length){
           ch = this.input.charAt(position);
        } else {
            ch = '\0';
        }
        return ch;
    }

    private char next(){
        this.position++;
        return peek(0);
    }
}
