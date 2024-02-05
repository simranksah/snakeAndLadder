package com.simran;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainClassIntegrationTest {

    @Mock
    Scanner scanner;

    @Test
    public void testMainWithIntegration() {
        String yamlConfig = "num_players: 2\n" +
                "board_size: 10\n" +
                "num_dice: 1\n" +
                "movement_strategy: SUM\n" +
                "snakes:\n" +
                "  - head: 17\n" +
                "    tail: 7\n" +
                "  - head: 54\n" +
                "    tail: 34\n" +
                "ladders:\n" +
                "  - start: 4\n" +
                "    end: 14\n" +
                "  - start: 28\n" +
                "    end: 84\n" +
                "crocodiles:\n" +
                "  - position: 12\n" +
                "mines:\n" +
                "  - position: 20\n" +
                "players:\n" +
                "  - name: Player1\n" +
                "    position: 1\n" +
                "  - name: Player2\n" +
                "    position: 1";

        InputStream inputStream = new ByteArrayInputStream(yamlConfig.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        try (MockedStatic<Scanner> scannerMock = Mockito.mockStatic(Scanner.class)) {

            scannerMock.when(scanner::next).thenReturn("yes");
        }
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(System.out) {
            public void println(String message) {
                output.append(message).append("\n");
            }
        });
        MainClass.main(null);
        System.setIn(System.in);
        System.setOut(System.out);
        assertTrue(output.toString().contains("wins!"));
    }
}
