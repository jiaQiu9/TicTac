
# CS611-Assignment 1
## Tic Tac Toe // Order and Chaos
---------------------------------------------------------------------------
- Name: Jiasheng Qiu
- Email: jiaq9@bu.edu
- Student ID: U80030504

## Files
---------------------------------------------------------------------------
Main.java: The Main class which creates a new gamelogic object, and call the game.startGame which starts the game program.
GameLogic.java: This the class that takes care of all the game logic for both games and takes care of user input. The class does not have a constructor, and it consists of static methods only.
                For all user inputs, there are associated fault tolerant parts taking care of the input. The Gamelogic class also creates board, box, and player objects for the gameplay, while
                also links everything together for the associated games. 
Board.java: Has most attributes of the board object, and associated getter and modifier methods for the board. 
Box.java: The box class is what stores the marks made by the player and indicates if a box is occupied or not.
Player.java: The player objects. keep track of player id, the mark associated with the player, and the Win and Loss statistics of the associated player. 

## Notes
---------------------------------------------------------------------------
1. The board size for tic-tac-toe could be extended from 3x3 to 10x10, for each continuing game play of tic-tac-toe. The max board size is limited 10 because in the terminal anything bigger than 10 would look too
   overwhelming and hard to view.
2. At the start of game, the user could choose the number(even number) of players. The team is divided by the list of players in halve. The first half is the first team, the second half is the second team.


## How to compile and run
---------------------------------------------------------------------------
