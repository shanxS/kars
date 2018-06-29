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
        while(cradle.getLook().equals('+') || cradle.getLook().equals('-')) {
            cradle.emitLn("MOVE D0, D1");
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
        cradle.emitLn("Add D1, D0");
    }

    public void subtract() {
        cradle.match('-');
        term();
        cradle.emitLn("Sub D1, D0");
    }

}
