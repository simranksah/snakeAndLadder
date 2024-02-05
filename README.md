# Machine-Coding-Round

## problem statment
You have to design & implement a Snakes and Ladders game that supports the following
functionality:
Requirements:
Mandatory Requirements:
You have to take a configuration (can be a yml/json file) with the following parameters.
● Number of players: N
● Board Size: BS (BS x BS)
● Number of Snakes: S
● Number of Ladders: L
● Number of Dies: D
● Movement Strategy: MS

## Basic rules implemented
● Snake always takes you to the cell where its tail is, and has to be a number less than where you are at currently.
● Ladder takes you up (strictly).
● If a player (A) comes to a cell where another player (B) is placed already, the previously placed player (B) has to start again from 1.
● Using the configuration you have to generate a random valid board & devise proper rules for placing objects on the board.
● Addition of special objects:
  ○ Crocodile, which takes you exactly 5 steps back.
  ○ Mine which holds you for 2 turns.

## config file to inset the input is at "src/main/resources/config.yml"

## for manual override of data 
A manual override must exist for the interviewer to verify the edge cases/ to write unit
tests. The program should take the following as input:
○ Starting location of each player.
  - You can insert the starting point in the config file
○ The D die values that each player rolled in a turn. (For the current config.yml input)
  - Option given at runtime
    - 
  - For automatic sample output

        Want manual override on dice roll ?(yes/no):
        no
        Gaurav rolled a 3 and climbed the ladder at 4 and moved from 4 to 14
        Sagar rolled a 2 and moved from 1 to 3
        Gaurav rolled a 5 and moved from 14 to 19
        Sagar rolled a 2 and moved from 3 to 5
        Gaurav rolled a 2 and moved from 19 to 21
        Sagar rolled a 6 and moved from 5 to 11
        Gaurav rolled a 5 and moved from 21 to 26
        Sagar rolled a 6 and bitten by snake at 17 and moved from 17 to 7
        Gaurav rolled a 5 and moved from 26 to 31
        Sagar rolled a 6 and moved from 7 to 13
        Gaurav rolled a 6 and moved from 31 to 37
        Sagar rolled a 3 and moved from 13 to 16
        Gaurav rolled a 5 and moved from 37 to 42
        Sagar rolled a 2 and moved from 16 to 18
        Gaurav rolled a 5 and moved from 42 to 47
        Sagar rolled a 3 and moved from 18 to 21
        Gaurav rolled a 6 and moved from 47 to 53
        Sagar rolled a 2 and moved from 21 to 23
        Gaurav rolled a 6 and moved from 53 to 59
        Sagar rolled a 4 and moved from 23 to 27
        Gaurav rolled a 5 and moved from 59 to 64
        Sagar rolled a 6 and moved from 27 to 33
        Gaurav rolled a 4 and moved from 64 to 68
        Sagar rolled a 5 and moved from 33 to 38
        Gaurav rolled a 3 and moved from 68 to 71
        Sagar rolled a 4 and moved from 38 to 42
        Gaurav rolled a 4 and moved from 71 to 75
        Sagar rolled a 2 and moved from 42 to 44
        Gaurav rolled a 4 and moved from 75 to 79
        Sagar rolled a 4 and moved from 44 to 48
        Gaurav rolled a 6 and moved from 79 to 85
        Sagar rolled a 5 and moved from 48 to 53
        Gaurav rolled a 4 and moved from 85 to 89
        Sagar rolled a 6 and moved from 53 to 59
        Gaurav rolled a 5 and moved from 89 to 94
        Sagar rolled a 6 and moved from 59 to 65
        Gaurav rolled a 3 and moved from 94 to 97
        Sagar rolled a 5 and moved from 65 to 70
        Gaurav rolled a 5 and moved from 97 to 97
        Sagar rolled a 6 and moved from 70 to 76
        Gaurav rolled a 6 and moved from 97 to 97
        Sagar rolled a 4 and moved from 76 to 80
        Gaurav rolled a 1 and moved from 97 to 98
        Sagar rolled a 5 and moved from 80 to 85
        Gaurav rolled a 4 and moved from 98 to 98
        Sagar rolled a 5 and moved from 85 to 90
        Gaurav rolled a 4 and moved from 98 to 98
        Sagar rolled a 4 and moved from 90 to 94
        Gaurav rolled a 4 and moved from 98 to 98
        Sagar rolled a 3 and moved from 94 to 97
        Gaurav rolled a 3 and moved from 98 to 98
        Sagar rolled a 1 collides with Gaurav and moved from 97 to 98
        Gaurav rolled a 4 and moved from 1 to 5
        Sagar rolled a 5 and moved from 98 to 98
        Gaurav rolled a 6 and moved from 5 to 11
        Sagar rolled a 4 and moved from 98 to 98
        Gaurav rolled a 4 and moved from 11 to 15
        Sagar rolled a 5 and moved from 98 to 98
        Gaurav rolled a 6 and moved from 15 to 21
        Sagar rolled a 5 and moved from 98 to 98
        Gaurav rolled a 4 and moved from 21 to 25
        Sagar rolled a 4 and moved from 98 to 98
        Gaurav rolled a 3 and climbed the ladder at 28 and moved from 28 to 84
        Sagar rolled a 3 and moved from 98 to 98
        Gaurav rolled a 4 and moved from 84 to 88
        Sagar rolled a 6 and moved from 98 to 98
        Gaurav rolled a 6 and moved from 88 to 94
        Sagar rolled a 6 and moved from 98 to 98
        Gaurav rolled a 6 and moved from 94 to 100
        Gaurav wins!


  - For Manual Sample output:


    Want manual override on dice roll ?(yes/no):
    yes
    Gaurav, enter dice roll (yes/no): yes
    Enter dice values separated by space: 2 6
    Gaurav rolled a 6 and moved from 1 to 7
    Sagar, enter dice roll (yes/no): no
    Sagar rolled a 2 and moved from 1 to 3
    Gaurav, enter dice roll (yes/no): 
