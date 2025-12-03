package org.ankurGangwar.systemDesign;

import org.ankurGangwar.systemDesign.model.Board;
import org.ankurGangwar.systemDesign.model.Player;
import org.ankurGangwar.systemDesign.model.playerPiece.PlayerPiece;
import org.ankurGangwar.systemDesign.model.playerPiece.PlayerPieceO;
import org.ankurGangwar.systemDesign.model.playerPiece.PlayerPieceX;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    Deque<Player> players;
    Board gameBoard;

    public void initializeGame(int size) {
        players = new LinkedList<>();

        PlayerPiece crossPiece = new PlayerPieceX();
        Player player1 = new Player("Player-1", crossPiece);

        PlayerPiece noughtPiece = new PlayerPieceO();
        Player player2 = new Player("Player-2", noughtPiece);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(size);
    }

    public String startGame() {
        boolean noWinner = true;
        while(noWinner) {
            if(!gameBoard.hasFreeCells()){
                return "No one won. tie !!";
            }
            Player playerTurn = players.removeFirst();
            gameBoard.printBoard();
            System.out.println("Player: "+playerTurn.getPlayerId()+" Enter x,y: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            if(values.length != 2){
                System.out.println("Please enter correct input");
                players.addFirst(playerTurn);
                continue;
            } else {
                try {
                    int inputRow = Integer.parseInt(values[0]);
                    int inputColumn = Integer.parseInt(values[1]);

                    if(inputRow < 0 || inputRow >= gameBoard.getSize() || inputColumn < 0 || inputColumn >= gameBoard.getSize()){
                        System.out.println("Please enter correct input");
                        players.addFirst(playerTurn);
                        continue;
                    }

                    boolean isPieceAdded = gameBoard.setPlayerPiece(inputRow, inputColumn, playerTurn.getPlayerPiece());
                    if(!isPieceAdded){
                        System.out.println("Incorrect location !!");
                        players.addFirst(playerTurn);
                        continue;
                    }
                    boolean isPlayerWinner = isPlayerWinner(inputRow, inputColumn, playerTurn.getPlayerPiece());
                    if(isPlayerWinner){
                        return "Congratulation !! "+playerTurn.getPlayerId()+" you are winner.";
                    } else {
                        players.addLast(playerTurn);
                        continue;
                    }
                } catch (Exception e){
                    System.out.println("Please enter correct input");
                    players.addFirst(playerTurn);
                    continue;
                }
            }
        }
        return "No one won. tie !!";
    }

    private boolean isPlayerWinner(int x, int y, PlayerPiece playerPiece){
        boolean isRowSame = true;
        for(int i = 0; i < gameBoard.getSize(); ++i){
            if(gameBoard.getPlayerPiece(i,y) != playerPiece){
                isRowSame = false;
                break;
            }
        }

        boolean isColSame = true;
        for(int j = 0; j < gameBoard.getSize(); ++j){
            if(gameBoard.getPlayerPiece(x,j) != playerPiece){
                isColSame = false;
                break;
            }
        }

        boolean isDiagSame = true;
        for(int i = 0, j = 0; i < gameBoard.getSize(); ++i, ++j){
            if(gameBoard.getPlayerPiece(i, j) != playerPiece){
                isDiagSame = false;
                break;
            }
        }

        boolean isAntiDiagSame = true;
        for(int i = 0, j = gameBoard.getSize()-1; i < gameBoard.getSize(); ++i, --j){
            if(gameBoard.getPlayerPiece(i, j) != playerPiece){
                isAntiDiagSame = false;
                break;
            }
        }

        return (isRowSame || isColSame || isDiagSame || isAntiDiagSame);
    }
}