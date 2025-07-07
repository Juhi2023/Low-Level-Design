package components;

import java.util.*;
import winstrategy.WinStrategy;
import winstrategy.RowWinStrategy;
import winstrategy.ColumnWinStrategy;
import winstrategy.DiagonalWinStrategy;

public class Board{
    private Cell [][] board;
    public int size;
    private int movesCount;
    private final List<WinStrategy> strategy;

    public Board(int size, List<WinStrategy> winStrategies){
        this.size = size;
        board = new Cell[size][size];
        initializeBoard();
        strategy = winStrategies;
    }

    private void initializeBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new Cell();
            }
        }
    }

    public void placeMove(int row, int col, Symbol s){
        if(isValid(row, col)){
            board[row][col].setSymbol(s);
            movesCount++;
        }else{
            System.out.println("Invalid Move.");
        }
    }

    public void display() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col].getSymbol()==Symbol.EMPTY ? "_ " : board[row][col].getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void reset() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j].setSymbol(Symbol.EMPTY);

        movesCount=0;
    }

    public boolean checkWin(Symbol symbol){
        for(WinStrategy x: strategy) {
            if(x.checkWin(this, symbol)) {
                return true;
            }
        }
        return false;
    }

    public Symbol getSymbol(int row, int col){
        return board[row][col].getSymbol();
    }


    public boolean isValid(int row, int col){
        if(row>=0 && row<size && col>=0 && col<size && board[row][col].isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isFull() {
        return movesCount == size * size;
    }


} 