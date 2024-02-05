package com.simran.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private List<Crocodile> crocodiles;
    private List<Mine> mines;
}