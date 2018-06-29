package com.simpleharmonic.kars;

public class MathParserTranslator {
    private final Cradle cradle;

    public MathParserTranslator(Cradle cradle) {
        this.cradle = cradle;
    }


    public void term() {
        cradle.emitLn("MOVE #" + cradle.getNum() + ",D0");
    }

    public void expression() {
        term();
        while(cradle.getLook() != null && (cradle.getLook().equals('+') || cradle.getLook().equals('-'))) {
            cradle.emitLn("MOVE D0, -(SP)");
            switch (cradle.getLook()) {
                case '-':
                    subtract();
                    break;
                case '+':
                    add();
                    break;
                default:
                    cradle.expected("Addop");
            }
        }
    }

    public void add() {
        cradle.match('+');
        term();
        cradle.emitLn("Add (SP)+, D0");
    }

    public void subtract() {
        cradle.match('-');
        term();
        cradle.emitLn("Sub (SP)+, D0");
    }

}
