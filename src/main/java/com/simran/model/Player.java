package com.simran.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private String name;
    private int position;
    private int skipTurns;

    public Player(String name) {
        this.name = name;
        this.position = 1;
    }

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
    }
}