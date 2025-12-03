package org.ankurGangwar.systemDesign.model;

import org.ankurGangwar.systemDesign.model.playerPiece.PlayerPiece;
import org.ankurGangwar.systemDesign.model.playerPiece.PlayerPieceX;

public class Board {
    private int size;
    private PlayerPiece[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new PlayerPiece[size][size];
    }

    public PlayerPiece getPlayerPiece(int x, int y) {
        return this.board[x][y];
    }

    public boolean setPlayerPiece(int x, int y, PlayerPiece playerPiece) {
        if(getPlayerPiece(x, y) != null){
            return false;
        }
        this.board[x][y] = playerPiece;
        return true;
    }

    public boolean hasFreeCells() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if(getPlayerPiece(i, j) == null){
                    return true;
                }
            }
        }
        return false;
    }

    public void printBoard(){
        for(int i = 0; i < size; ++i){
            for(int j = -1; j < size; ++j){
                if(j == -1){
                    System.out.print("|");
                    continue;
                }
                if(this.board[i][j] == null){
                    System.out.print(" ");
                } else {
                    PlayerPiece playerPiece = this.board[i][j];
                    if(playerPiece instanceof PlayerPieceX){
                        System.out.print("X");
                    } else {
                        System.out.print("O");
                    }
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }
}
