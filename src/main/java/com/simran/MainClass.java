package com.simran;

import com.simran.model.Board;
import com.simran.model.Crocodile;
import com.simran.model.Ladder;
import com.simran.model.Mine;
import com.simran.model.Player;
import com.simran.model.Snake;
import com.simran.service.SnakesAndLaddersGame;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) {
        SnakesAndLaddersGame game = loadGameConfiguration("src/main/resources/config.yml");
        game.playGame();
    }

    private static SnakesAndLaddersGame loadGameConfiguration(String configFile) {
        try (FileInputStream input = new FileInputStream(configFile)) {
            Yaml yaml = new Yaml();
            Map<String, Object> configMap = yaml.load(input);

            int numPlayers = (int) configMap.get("num_players");
            int boardSize = (int) configMap.get("board_size");
            int numDice = (int) configMap.get("num_dice");
            String movementStrategy = (String) configMap.get("movement_strategy");

            List<Snake> snakes = parseSnakes((List<Map<String, Integer>>) configMap.get("snakes"));
            List<Ladder> ladders = parseLadders((List<Map<String, Integer>>) configMap.get("ladders"));
            List<Crocodile> crocodiles = parseCrocodiles((List<Map<String, Integer>>) configMap.get("crocodiles"));
            List<Mine> mines = parseMines((List<Map<String, Integer>>) configMap.get("mines"));
            List<Player> players = parsePlayers((List<Map<String, Object>>) configMap.get("players"));

            Board board = new Board(boardSize, snakes, ladders, crocodiles, mines);

            SnakesAndLaddersGame game = new SnakesAndLaddersGame(numPlayers, board, numDice, movementStrategy);
            game.addPlayer(players);
            return game;

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    private static List<Snake> parseSnakes(List<Map<String, Integer>> snakeList) {
        List<Snake> snakes = new ArrayList<>();
        for (Map<String, Integer> snake : snakeList) {
            int head = snake.get("head");
            int tail = snake.get("tail");
            snakes.add(new Snake(head, tail));
        }
        return snakes;
    }

    private static List<Ladder> parseLadders(List<Map<String, Integer>> ladderList) {
        List<Ladder> ladders = new ArrayList<>();
        for (Map<String, Integer> ladder : ladderList) {
            int start = ladder.get("start");
            int end = ladder.get("end");
            ladders.add(new Ladder(start, end));
        }
        return ladders;
    }

    private static List<Crocodile> parseCrocodiles(List<Map<String, Integer>> crocodileList) {
        List<Crocodile> crocodiles = new ArrayList<>();
        for (Map<String, Integer> crocodile : crocodileList) {
            int position = crocodile.get("position");
            crocodiles.add(new Crocodile(position));
        }
        return crocodiles;
    }

    private static List<Mine> parseMines(List<Map<String, Integer>> mineList) {
        List<Mine> mines = new ArrayList<>();
        for (Map<String, Integer> mine : mineList) {
            int position = mine.get("position");
            mines.add(new Mine(position));
        }
        return mines;
    }

    private static List<Player> parsePlayers(List<Map<String, Object>> players) {
        List<Player> playerList = new ArrayList<>();
        for (Map<String, Object> player : players) {
            playerList.add(new Player((String) player.get("name"), (Integer) player.get("position")));
        }
        return playerList;
    }

}