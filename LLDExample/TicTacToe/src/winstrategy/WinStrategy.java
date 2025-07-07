package winstrategy;

import components.Board;
import components.Symbol;

public interface WinStrategy{
    boolean checkWin(Board b, Symbol s);
}