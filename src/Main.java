import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

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
        // use the numplayer to create numplayer Player objects
        Player[] players= new Player[numPlayer];

        if (userGame==1){
            // for tic-tac-toe

            setPlayerMarks3t(players, numPlayer);

            // get the size of the board in terms of nxn, by now
            boardSize = enterBoardSize();

            // set the board and pieces of the board
            Board tictac = new Board(boardSize, boardSize);
            tictac.createBoardPiece();
            tictac.printBoard();

            // Game play
            gamePlay3t(tictac, players, numPlayer);


        }
        else{
            // for order and chaos
            System.out.println("Order and chaos Launching");
            boardSize = 6;
            System.out.println("The size of the board is "+boardSize);
            Board orderChaos=new Board(boardSize,boardSize);
            orderChaos.createBoardPiece();
            orderChaos.printBoard(); // initial printout of the board game

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
            System.out.print("Enter the size of the board: (Enter one numeric value, min size is 3x3):");
            userIn = userInput.nextLine();
            if (checkInt(userIn)){
                if (Integer.valueOf(userIn)<3){
                    isValid = false;
                }
                else if (Integer.valueOf(userIn)>=3){
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

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    //Game Play for tic-tac-toe
    public static void gamePlay3t(Board board, Player[] players, int numPlayers){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Tic-Tac-Toe Starting.");
        //pointers for the players, so there is no need for creating two array to store players of different teams
        int first =0; // the pointed for the first player, in the first team
        int second=1; // the pointer for the second player OR the first player of the other(second) team
        boolean gameEnd=false;
        boolean turn = false;
        String playerMove = "";
        // distinguish different num of players, for 2 players one will be X other will be O
        // for more than 2 player, the first half of the players will be X, the other half will be O
        // number of Players should always be even, and more than one
        if (numPlayers>2){
            second = numPlayers/2 -1;
        }

        while(!gameEnd){
            System.out.println("Game Start!");
            board.printBoard();
            if (turn){
                System.out.println("Player "+ players[first].getPlayerID()+ " make your move. Your mark is "+ players[first].getPlayerMark());
                System.out.print("Enter a numeric value that represents the position on the board, that your want to place your mark:");


            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void gamePlayOC(Board board, int numPlayer){

    }

}

