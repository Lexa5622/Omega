package ru.ulteam8;

import ru.ulteam8.core.awt.Token;
import ru.ulteam8.core.interfaces.Expression;
import ru.ulteam8.core.parser.Lexer;
import ru.ulteam8.core.parser.Parser;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    String input = "(3.5 + 2) * 2.1";
	    Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        for (Token token: tokens){
            System.out.println(token.toString());
        }

        Parser parser = new Parser(tokens);
        List<Expression> expressions = parser.parse();

        for (Expression expression: expressions){
            System.out.println(expression + " = " + expression.eval());
        }
    }
}
