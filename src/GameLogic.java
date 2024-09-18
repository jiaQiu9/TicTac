import java.util.Scanner;

public class GameLogic {

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void startGame(){
        Scanner userInput = new Scanner(System.in);
        int numPlayer;
        int boardSize;
        int userGame;
        System.out.println("Hello and Welcome!");

        // check which game the user wants to play
        userGame = gameChoice();

        // get the number of players
        // get the number of players, the user should enter an even number
        numPlayer = getNumPlayer();
        // use the numplayer(number of players) to create a list of type player with size numplayer
        Player[] playerslst= new Player[numPlayer];

        if (userGame==1){
            // for tic-tac-toe

            // create numplayer player objects, which should be stored in playerslst
            // the numplayer would be fixed from this point on, until the players end the game. easier to keep track of players
            setPlayerMarks3t(playerslst, numPlayer);

            // Game play
            gamePlay3t( playerslst, numPlayer);


        }
        else{
            // for order and chaos
            System.out.println("Order and chaos Launching");
            boardSize = 6;
            System.out.println("The size of the board is "+boardSize+", which is fixed for the current Game.");
            Board orderChaos=new Board(boardSize,boardSize);
            orderChaos.createBoardPiece();
            orderChaos.printBoard(); // initial printout of the board game

            // Game Play
        }
    }


    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // this method will be used more than once
    public static boolean checkInt(String userIn){
        boolean isValid=false;
        try{
            int num = Integer.parseInt(userIn);
            isValid = true;
        }
        catch(NumberFormatException e){
            isValid=false;
        }
        return isValid;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static int gameChoice(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please choose the game you want to play.");
        System.out.println("1.Tic-Tac-Toe");
        System.out.println("2. Order or Chaos");
        String userIn="";
        boolean isValid = false; // for the while loop to run
        while(!isValid){
            System.out.print("Choose your game:(Enter numeric values)");
            userIn = userInput.nextLine();
            System.out.println();
            if (checkInt(userIn)){
                if (Integer.valueOf(userIn)==1||Integer.valueOf(userIn)==2){
                    isValid = true;
                }
            }
        }
        return Integer.valueOf(userIn);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static int getNumPlayer(){
        // the number of player based on the user input
        // some games have fixed player num, some have flexible player num
        // the number of player should be even number, for now
        Scanner userInput = new Scanner(System.in);
        String userIn="";
        int numPlayer = 0;
        boolean isValid = false;
        while(!isValid){
            System.out.print("Please enter the number of players(Enter numeric values, the number of players should be an even number): ");
            userIn = userInput.nextLine();
            System.out.println();
            if (checkInt(userIn)){
                if (Integer.valueOf(userIn)%2==0 && Integer.valueOf(userIn)>2){
                    isValid = true;
                    numPlayer = Integer.parseInt(userIn);
                }
            }
        }
        return numPlayer;

    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static int enterBoardSize(){
        Scanner userInput = new Scanner(System.in);
        String userIn="";
        boolean isValid = false;
        int boardSize = 3;
        while(!isValid){
            // the max could be changed later
            System.out.print("Enter the size of the board: (Enter one numeric value, min size is 3, max is 20):");
            userIn = userInput.nextLine();
            if (checkInt(userIn)){
                if (Integer.valueOf(userIn)<3){
                    isValid = false;
                }
                else if (Integer.valueOf(userIn)>=3 && Integer.valueOf(userIn)<=20){
                    isValid = true;
                }
            }
        }

        return boardSize;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static boolean CheckWin(Board board, String Mark){
        // check the win condition for the tic-tac-toe game, for the input mark of the current player after making a move
        // this could also be used to check the win condition for order and chaos game
        // check every row
        // check every column
        // check every diagonal
        Box[][] tmp = board.getBoardsur(); // this gets the boxes on the board
        // temporary store the box list on the board, to prevent multiple calling of the getBoardsur(), and waist time and resources

        int count=0;
        //
        //check every row
        for (int i = 0; i < tmp.length; i++){

            for (int j = 0; j < tmp[i].length; j++){
                if (tmp[i][j].getMark().equals(Mark)){
                    count++;
                }
            }
            if (count == board.getBoardSizeM()){
                return true;
            }
            count =0 ;
        }

        // check every column
        for (int i = 0; i < board.getBoardSizeN(); i++){

            for (int j = 0; j < board.getBoardSizeM(); j++){
                if (tmp[j][i].getMark().equals(Mark)){
                    count++;
                }
            }
            if (count == board.getBoardSizeN()){
                return true;
            }
            count=0;
        }

        // check diagonal forward
        for (int i = 0; i < board.getBoardSizeM(); i++){

            for (int j = 0; j < board.getBoardSizeN(); j++){
                if (i==j && tmp[i][j].getMark().equals(Mark)){
                    count++;
                }
                if (count == board.getBoardSizeM()){ // TO Do need to change to actual diagonal num
                    return true;
                }
            }

        }
        count =0;
        // check diagonal backward
        for (int i=board.getBoardSizeN()-1; i>0; i--){
            for (int j=0; j<board.getBoardSizeM(); j++){
                if(tmp[j][i].getMark().equals(Mark)){
                    count++;
                }
                if (count == board.getBoardSizeM()) { // TO DO need to change to actual diagonal num
                    return true;
                }
            }

        }
        return false;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void setPlayerMarks3t(Player[] players, int numPlayers){
        // assign player to X or O
        for (int i =0; i<numPlayers;i++){
            players[i]=new Player(i);
            if (i<= (numPlayers/2)-1){
                players[i].setPlayerMark("X");
            }else{
                players[i].setPlayerMark("O");
            }
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // need to check if it is an int value, check out of bounds, and check availability
    public static void placeMark(Board board, Player player, int maxPos){
        boolean isValid =false;
        Scanner scanner = new Scanner(System.in);
        String userIn;
        int position;
        while(!isValid){
            System.out.println("Player "+ player.getPlayerID()+ " make your move. Your mark is "+ player.getPlayerMark());
            System.out.print("Enter a numeric value that represents the position on the board, that your want to place your mark:");
            board.printBoard();

            userIn = scanner.nextLine();
            if (checkInt(userIn)){
                //it is an integer value, need to check if the entered position is within the board parameter
                position = Integer.parseInt(userIn);
                if (position<1 || position>=maxPos){
                    // out of bounds
                    isValid = false;
                    System.out.println("Position is out of bounds. Try again.");
                }
                else if (position>=1 && position<maxPos){
                    // in bounds
                    // need to check availability/ if empty, then assign it
                    if (board.getBoxApos(position).isOccupied()){
                        System.out.println("Position is occupied. Try again.");
                    }else{
                        //set the box at position on the board to be occupied
                        board.getBoxApos(position).setOccupied(true);
                        //set the mark on box at position on the board to be the current player's mark
                        board.getBoxApos(position).setMark(player.getPlayerMark());
                        board.increaseOccupancy();
                        isValid = true; // end the loop, outside the method need to check win condition
                    }
                }

            }
            else{
                System.out.println(" Please Enter a numeric value");
                isValid=false;
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    //Game Play for tic-tac-toe
    public static void gamePlay3t( Player[] players, int numPlayers){
        //pointers for the players, so there is no need for creating two array to store players of different teams
        int first =0; // the pointed for the first player, in the first team
        int second=1; // the pointer for the second player OR the first player of the other(second) team, initialize to 1, modified later
        boolean gameEnd=false;
        boolean turn = false; //when false team 1's term . when true team 2's turn
        String playerMove = ""; // not sure what this is for.

        int boardSize = 3; // the board size for 3t game starts from 3x3 to nxn

        // this is where the start of the game
        Scanner userInput = new Scanner(System.in);
        System.out.println("Tic-Tac-Toe Starting.");

        // distinguish different num of players, for 2 players one will be X other will be O
        // for more than 2 player, the first half of the players will be X, the other half will be O
        // number of Players should always be even, and more than one
        if (numPlayers>2){
            second = numPlayers/2 -1;
        }
        System.out.println("Game Start!");


        // gameEnd is for when the player(s) do not want to play anymore, so ends the while loop
        // the loop will continue as long as the player continues to play
        while(!gameEnd){
            boolean winNext = false; // false board not filled and no winner yet, true winner or the board is filled no winner

            // get the size of the board in terms of nxn, by now, maybe in the future have mxn or every row has different column sizes
            boardSize = enterBoardSize();

            // set the board and pieces of the board
            // new board, new board size at each game
            Board tictac = new Board(boardSize, boardSize);
            tictac.createBoardPiece();
            tictac.printBoard();
            int maxNumBox=boardSize*boardSize ;
            // after creating the board, and pieces on the board.
            // have a while loop that keeps altering the turns, ask players for moves, ends when either the board is filled with no winner
            // or when there is a winner

            while(!winNext){
                // when turn== true, the first team moves. when turn == false, the second team moves
                if (turn){
                    // maxNumbox-1, positions start at 0, not 1
                    placeMark(tictac, players[first], maxNumBox-1);
                    winNext = CheckWin(tictac,players[first].getPlayerMark());
                    if (winNext == false){
                        // when the current player's move is not a win condition
                        turn = !turn; // change turn
                    }
                    if(tictac.getOccupancy() == maxNumBox){
                        // the board is filled out, end game
                        winNext = true;
                        // TO-DO need to distinguish between win and board filled out
                    }
                    if (numPlayers>2){
                        // TO-DO need something to change player in the same team
                    }

                }
                else{
                    placeMark(tictac, players[second], maxNumBox-1);
                    winNext = CheckWin(tictac,players[second].getPlayerMark());
                }
            }

        }


        // TO-DO, need to display player results, this is where the players have done with the game


    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void gamePlayOC(Board board, int numPlayer){

    }
}
