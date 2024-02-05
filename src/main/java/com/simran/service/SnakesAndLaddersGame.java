package com.simran.service;

import com.simran.model.Board;
import com.simran.model.Crocodile;
import com.simran.model.Ladder;
import com.simran.model.Mine;
import com.simran.model.Player;
import com.simran.model.Snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SnakesAndLaddersGame {
    private int numPlayers;
    private Board board;
    private int numDice;
    private String movementStrategy;
    private List<Player> players;
    private Map<Integer, Player> playerPositions;

    public SnakesAndLaddersGame(int numPlayers, Board board, int numDice, String movementStrategy) {
        this.numPlayers = numPlayers;
        this.board = board;
        this.numDice = numDice;
        this.movementStrategy = movementStrategy;
        this.players = new ArrayList<>();
        this.playerPositions = new HashMap<>();
    }

    public void addPlayer(List<Player> playerList) {
        players.addAll(playerList);
    }

    private boolean globalManualCheck(Scanner scanner) {
        System.out.println("Want manual override on dice roll ?(yes/no): ");
        String input = scanner.next();
        return input.equalsIgnoreCase("yes");
    }

    private boolean checkManualDice(String name, Scanner scanner) {
        System.out.print(name + ", enter dice roll (yes/no): ");
        return scanner.next().equalsIgnoreCase("yes");
    }

    public void playGame() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean globalManualCheck = globalManualCheck(scanner);

        while (true) {
            for (Player player : players) {

                if (player.getSkipTurns() > 0) {
                    System.out.println(player.getName() + " skips a turn due to a mine");
                    player.setSkipTurns(player.getSkipTurns() - 1);
                    continue;
                }

                StringBuilder str = new StringBuilder();
                str.append(player.getName());
                int diceResult = rollDice(random, scanner, globalManualCheck && checkManualDice(player.getName(),
                        scanner));
                str.append(" rolled a ").append(diceResult);
                int lastPosition = player.getPosition();
                int newPosition = calculateNewPosition(lastPosition, player, diceResult, str);
                newPosition = handleSpecialObjects(player, newPosition, str);

                str.append(" and moved from ").append(player.getPosition()).append(" to ").append(newPosition);
                System.out.println(str);

                if (newPosition == board.getSize() * board.getSize()) {
                    System.out.println(player.getName() + " wins!");
                    System.exit(0);
                }

                playerPositions.remove(lastPosition);
                playerPositions.put(newPosition, player);
                player.setPosition(newPosition);
            }
        }
    }

    private int rollDice(Random random, Scanner scanner, boolean manualCheck) {
        return manualCheck ? rollDiceManual(scanner) : rollDiceAutomatic(random);
    }

    private int rollDiceAutomatic(Random random) {
        int result = 0;
        for (int i = 0; i < numDice; i++) {

            int dieValue = random.nextInt(6) + 1;

            switch (movementStrategy) {
                case "SUM" -> result += dieValue;
                case "MAX" -> result = Math.max(result, dieValue);
                case "MIN" -> result = (i == 0) ? dieValue : Math.min(result, dieValue);
            }
        }

        return result;
    }

    private int rollDiceManual(Scanner scanner) {

        int result = 0;
        System.out.print("Enter dice values separated by space: ");
        scanner.nextLine();
        String[] rollsStr = scanner.nextLine().split(" ");

        List<Integer> rolls = new ArrayList<>();
        for (String rollStr : rollsStr) {
            rolls.add(Integer.parseInt(rollStr));
        }
        switch (movementStrategy) {
            case "SUM" -> result = rolls.stream().mapToInt(Integer::intValue).sum();
            case "MAX" -> result = Collections.max(rolls);
            case "MIN" -> result = Collections.min(rolls);
        }
        return result;
    }

    private int calculateNewPosition(int lastPosition, Player player, int diceResult, StringBuilder str) {
        int newPosition = lastPosition + diceResult;

        if (newPosition > board.getSize() * board.getSize()) {
            newPosition = lastPosition;
        }
        if (playerPositions.containsKey(newPosition)) {
            Player collidedPlayer = playerPositions.get(newPosition);
            if (!player.getName().equalsIgnoreCase(collidedPlayer.getName())) {
                str.append(" collides with ").append(collidedPlayer.getName());
                collidedPlayer.setPosition(1);  // collided player starts again from 1
            }
        }

        for (Snake snake : board.getSnakes()) {
            if (newPosition == snake.getHead()) {
                player.setPosition(snake.getHead());
                newPosition = snake.getTail();
                str.append(" and bitten by snake at ").append(snake.getHead());
                break;
            }
        }

        for (Ladder ladder : board.getLadders()) {
            if (newPosition == ladder.getStart()) {
                player.setPosition(ladder.getStart());
                newPosition = ladder.getEnd();
                str.append(" and climbed the ladder at ").append(ladder.getStart());
                break;
            }
        }

        return newPosition;
    }

    private int handleSpecialObjects(Player player, int newPosition, StringBuilder str) {
        int finalPosition = newPosition;
        for (Crocodile crocodile : board.getCrocodiles()) {
            if (newPosition == crocodile.getPosition()) {
                str.append(" and encounters a crocodile at ").append(crocodile.getPosition());
                finalPosition = Math.max(1, newPosition - 5);
                break;
            }
        }

        for (Mine mine : board.getMines()) {
            if (newPosition == mine.getPosition()) {
                str.append(" and encounters a mine at ").append(mine.getPosition());
                player.setSkipTurns(2); // Set the number of turns to skip
                break;
            }
        }
        return finalPosition;
    }
}