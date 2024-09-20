import java.util.Scanner;

public class GameLogic {

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void startGame(){
        //Scanner userInput = new Scanner(System.in);
        int numPlayer;
        int boardSize;
        int userGame;
        System.out.println("Hello and Welcome!");

        // check which game the user wants to play
        userGame = gameChoice();

        // get the number of players
        // get the number of players, the user should enter an even number
        numPlayer = getNumPlayer();
        // use the numPlayer(number of players) to create a list of type player with size numplayer
        Player[] playerslst= new Player[numPlayer];

        if (userGame==1){
            // for tic-tac-toe

            // create numplayer player objects, which should be stored in playerslst
            // the numplayer would be fixed from this point on, until the players end the game. easier to keep track of players
            setPlayerMarks3t(playerslst, numPlayer, true);

            // Game play
            gamePlay3t( playerslst, numPlayer);


        }
        else if (userGame==2){
            // for order and chaos
            System.out.println("Order and chaos Launching");

            // TO-DO
            // do the board creation and other things in the Game Play
            // need a new player marks setter method
            //
            setPlayerMarks3t(playerslst, numPlayer,false);
            gamePlayOC(playerslst,numPlayer);
            // Game Play
        } else{
            System.out.println("You have choosen an option that is not listed.");
            System.out.println("The System is Closing.");
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
        String userIn;
        int numPlayer = 0;
        boolean isValid = false;
        while(!isValid){
            System.out.print("Please enter the number of players(Enter numeric values, the number of players should be an even number): ");
            userIn = userInput.nextLine();
            System.out.println();
            if (checkInt(userIn)){
                if (Integer.valueOf(userIn)%2==0 && Integer.valueOf(userIn)>=2){
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
        String userIn;
        boolean isValid = false;
        int boardSize = 3;
        while(!isValid){
            // the max could be changed later
            System.out.print("Enter the size of the board: (Enter one numeric value, min size is 3, max is 10):");
            userIn = userInput.nextLine();
            if (checkInt(userIn)){
                if (Integer.valueOf(userIn)<3){
                    isValid = false;
                }
                else if (Integer.valueOf(userIn)>=3 && Integer.valueOf(userIn)<=10){
                    isValid = true;
                    boardSize = Integer.parseInt(userIn);
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
                if (tmp[i][j].getMark()== Mark){
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
                if (tmp[j][i].getMark()== Mark){
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
                if (i==j && tmp[i][j].getMark()== Mark){
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
                if(tmp[j][i].getMark()== Mark){
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
    // helper function for the checkOcWin
    public static boolean checkDiagonal(Box[][] tmp, String Mark, int[][] diagonal, boolean type){
        int count =0;
        if (type){
            if (tmp[diagonal[0][0]][diagonal[0][1]].getMark()==Mark){
                for(int i=0;i<diagonal.length-1;i++){
                    if(tmp[diagonal[i][0]][diagonal[i][1]].getMark()==Mark){
                        count++;
                    }
                }
                if(count==5){
                    return true;
                }else{
                    count=0;
                }
            }else if(tmp[diagonal[1][0]][diagonal[1][1]].getMark()==Mark){
                for(int i=1;i<diagonal.length;i++){
                    if(tmp[diagonal[i][0]][diagonal[i][1]].getMark()==Mark){
                        count++;
                    }
                }
                if(count==5){
                    return true;
                }else{
                    count=0;
                }
            }
        } else{
            if (tmp[diagonal[0][0]][diagonal[0][1]].getMark()==Mark){
                for(int i=0;i<diagonal.length;i++){
                    if(tmp[diagonal[i][0]][diagonal[i][1]].getMark()==Mark){
                        count++;
                    }
                }
                if(count==5){
                    return true;
                }else{
                    count=0;
                }
            }
        }


        return false;
    }


    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // check the win condition for Order and Chaos game
    // five consecutive same marks
    public static boolean checkOcWin(Board board, String Mark){
        int count =0;
        Box[][] tmp = board.getBoardsur();
        // row check
        for (int i=0;i<board.getBoardSizeM();i++){
            if (tmp[i][0].getMark()==Mark){
                for (int j=0;j<board.getBoardSizeN()-1;j++){
                    // from index 0 to 4 if all the same mark, return true
                    if(tmp[i][j].getMark()== Mark){
                        count ++;
                    }
                }
            } else if(tmp[i][1].getMark()==Mark){
                for (int j=1;j<board.getBoardSizeN();j++){
                    // from index 1 to 5, if all the same mark, return true
                    if (tmp[i][j].getMark()== Mark){
                        count ++;
                    }
                }
            }
            if (count == 5){
                return true;
            }
            count=0;
        }

        // Column check
        for (int i=0;i<board.getBoardSizeN();i++){
            // column iteration
            if (tmp[0][i].getMark()==Mark){
                for(int j=0;j<board.getBoardSizeN()-1;j++){
                    if (tmp[j][i].getMark()== Mark){
                        count++;
                    }
                }
            } else if (tmp[1][i].getMark()==Mark){
                for(int j=1;j<board.getBoardSizeN();j++){
                    if (tmp[j][i].getMark()== Mark){
                        count++;
                    }
                }
            }
            if (count==5){
                return true;
            }
            count =0;
        }
        // the possible diagonal position, nto sure what loop or recursion to use to go through the board,
        // so hard coded the positions of the diagonals
        int[][] diagonal1={{0,0},{1,1},{2,2},{3,3},{4,4},{5,5}};
        int[][] diagonal2= {{0,1},{1,2},{2,3},{3,4},{4,5}};
        int[][] diagonal3={{1,0},{2,1},{3,2},{4,3},{5,4}};

        int[][] diagonal4={{0,5},{1,4},{2,3},{3,2},{4,1},{5,0}};
        int[][] diagonal5={{4,0},{3,1},{2,2},{1,3},{0,4}};
        int[][] diagonal6={{5,1},{4,2},{3,3},{2,4},{1,5}};

        return checkDiagonal(tmp, Mark,diagonal1,true)||checkDiagonal(tmp, Mark,diagonal2,false)||
                checkDiagonal(tmp, Mark,diagonal3,false) || checkDiagonal(tmp, Mark,diagonal4,true)||
                checkDiagonal(tmp, Mark,diagonal5,false) || checkDiagonal(tmp, Mark, diagonal6, false);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void setPlayerMarks3t(Player[] players, int numPlayers, boolean game){
        // assign player to X or O
        if (game){
            // game 1 tic-tac toe
            // need to preassign marks
            for (int i =0; i<numPlayers;i++){
                players[i]=new Player(i);
                if (i<= (numPlayers/2)-1){
                    players[i].setPlayerMark("X");
                }else{
                    players[i].setPlayerMark("O");
                }
            }
        }else{
            // order and chaos, no need to preassign marks
            for (int i =0; i<numPlayers;i++){
                players[i]=new Player(i);
            }
        }

    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void chooseMark(Player player){
        // for order and chaos where the players could choose their own marks
        Scanner scanner = new Scanner(System.in);
        String userIn;
        boolean isValid =false;

        while (!isValid){
            System.out.println(" Please choose your mark. X or O:");
            userIn = scanner.nextLine();
            if(userIn.equals("X")|| userIn.equals("x") ){
                player.setPlayerMark("X");
                isValid=true;

            } else if (userIn.equals("O")|| userIn.equals("o")){
                player.setPlayerMark("O");
                isValid=true;
            }
            else{
                System.out.println("Please Choose your mark. X or O, nothing else is accepted.");
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
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("[+]Player "+ player.getPlayerID()+ " make your move. Your mark is "+ player.getPlayerMark());
            board.printBoard();
            System.out.println("Enter a numeric value that represents the position on the board, that your want to place your mark:");
            userIn = scanner.nextLine();
            if (checkInt(userIn)){
                //it is an integer value, need to check if the entered position is within the board parameter
                position = Integer.parseInt(userIn);
                if (position<0 || position>maxPos){
                    // out of bounds
                    isValid = false;
                    System.out.println("Position is out of bounds. Try again.");
                }
                else if (position>=0 && position<=maxPos){
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
    // Increment the statistics of all players either win or loss
    public static void incrementPlayerStats(Player[] players,int numPlayers, int ref){
        // TO-DO
        int mid= numPlayers/2 -1;
        System.out.println("numPlayers: "+numPlayers+" mid "+mid+" ref: "+ref);
        if (0<=ref && ref<=mid){
            // first team
            for (int i=0; i<=mid; i++){
                players[i].setPlayerWin();
            }
            for(int j=mid+1; j<numPlayers;j++){
                players[j].setPlayerLoss();
            }
        }else if (mid<ref && ref<=numPlayers-1){
            // second team win, first team loss
            for(int j=mid+1; j<numPlayers;j++){

                players[j].setPlayerWin();
            }
            for (int i=0; i<=mid; i++){
                players[i].setPlayerLoss();
            }
        }

    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void printPlayerStats(Player[] players,int numPlayers){
        for(int i=0; i<numPlayers; i++){
            System.out.println("---------------------------------------------");
            System.out.println("[+] Player "+players[i].getPlayerID()+":");
            System.out.println("  [won] "+players[i].getPlayerWin()+" games");
            System.out.println("  [loss] "+players[i].getPlayerLoss()+" games");
            System.out.println("---------------------------------------------");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void printWinnerPlayersId(Player[] players, int numPlayers, int ref){
        int mid = numPlayers/2 -1;
        if (0<=ref && ref<=mid){
            for (int i=0; i<=mid; i++){
                System.out.println(" Player "+players[i].getPlayerID()+" wins!");
            }
        }else if (mid<ref && ref<=numPlayers-1){
            for (int i=mid+1; i<numPlayers; i++){
                System.out.println(" Player "+players[i].getPlayerID()+" wins!");
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

        int boardSize = 3; // the board size for 3t game starts from 3x3 to nxn
        int mid=0;
        // this is where the start of the game
        Scanner userInput = new Scanner(System.in);
        String userIn ;
        System.out.println("Tic-Tac-Toe Starting.");

        // distinguish different num of players, for 2 players one will be X other will be O
        // for more than 2 player, the first half of the players will be X, the other half will be O
        // number of Players should always be even, and more than one
        if (numPlayers>2){
            second = numPlayers/2;
            mid=numPlayers/2 -1;
        }

        System.out.println("Game Start!");


        // gameEnd is for when the player(s) do not want to play anymore, so ends the while loop
        // the loop will continue as long as the player continues to play
        while(!gameEnd){
            boolean winNext = false; // false board no winner yet, true winner

            boolean gameContinue = false; // continue the game, false board not filled and no winner and player want to continue; true board filled, player win, player ends game
            // get the size of the board in terms of nxn, by now, maybe in the future have mxn or every row has different column sizes
            boardSize = enterBoardSize();

            // set the board and pieces of the board
            // new board, new board size at each game
            Board tictac = new Board(boardSize, boardSize);
            tictac.createBoardPiece();
            tictac.printBoard();
            int maxNumBox=boardSize*boardSize ;
            System.out.println(" The Size of the board is "+boardSize+ " the max boardsize "+maxNumBox);
            System.out.println();
            // after creating the board, and pieces on the board.
            // have a while loop that keeps altering the turns, ask players for moves, ends when either the board is filled with no winner
            // or when there is a winner

            while(!winNext && !gameContinue){
                // when turn== true, the first team moves. when turn == false, the second team moves
                if (turn){
                    System.out.println();
                    // maxNumbox-1, positions start at 0, not 1
                    placeMark(tictac, players[first], maxNumBox-1);
                    winNext = CheckWin(tictac,players[first].getPlayerMark());
                    //System.out.println("WinNext "+winNext+" gameContinue "+gameContinue+" players[first] "+players[first].getPlayerID());
                    //System.out.println("!winNext || !gameContinue" + (!winNext || !gameContinue));
                    if (winNext == false){
                        // when the current player's move is not a win condition
                        turn = !turn; // change turn
                    }
                    if(tictac.getOccupancy() == maxNumBox){
                        // the board is filled out, end game
                        gameContinue =true;
                        // TO-DO (Done) need to distinguish between win and board filled out
                    }
                    if (numPlayers>2){
                        // TO-DO need something to change player in the same team
                        if (first+1==mid+1){
                            first = 0;
                        }
                        else if(first+1<mid){
                            first++;
                        }
                    }
                    // if there are only two players in the game, the first and second does not change

                }
                else{
                    // for the second player || the current player in the second team
                    placeMark(tictac, players[second], maxNumBox-1);
                    winNext = CheckWin(tictac,players[second].getPlayerMark());
                    //System.out.println("WinNext "+winNext+" players[first] "+players[first].getPlayerID());
                    if (!winNext){
                        // when the current player's move is not a win condition
                        turn = !turn; // change turn with a not condition
                    }
                    if(tictac.getOccupancy() == maxNumBox){
                        // the board is filled out, end game
                        gameContinue =true;
                        // TO-DO (Done) need to distinguish between win and board filled out
                    }
                    if (numPlayers>2){
                        // TO-DO need something to change player in the same team
                        if (second+1==numPlayers){
                            second= numPlayers/2;
                        }
                        else if(second+1<numPlayers-1){
                            second++;
                        }
                    }
                    // if there are only two players in the game, the first and second does not change
                }
            }

            if (winNext){
                // if there is a win increment the user statistics and display user statistics
                // while also increment the team's statistics
                // use the turn to distinguish between players
                tictac.printBoard();
                int tempRef; // temporary reference to the current team, if there is more than two players
                if (turn){
                    // first team
                    tempRef=first;
                }else{
                    // second team
                    tempRef=second;
                }
                System.out.println("Players in team "+tempRef+" Win.");

                incrementPlayerStats(players, numPlayers, tempRef);
                // there is a win for one of the teams, increment the win for winner
                // increment the loss of the lossing team.

                // print winner players id
                printWinnerPlayersId(players, numPlayers, tempRef);

            } else if (!winNext && gameContinue){
                System.out.println("The board is filled out. There is a Tie");
            }


            printPlayerStats(players, numPlayers); // print player stats

            // TO-DO win condition, board filled condition, ask the user if they want to continue.
            // only one player could decide if the game continues
            System.out.println("Do you want to play another game?");
            System.out.println("1. Yes, Enter Y/y");
            System.out.println("2. No, Enter anything else");
            userIn = userInput.nextLine();
            if (userIn.equals("Y") || userIn.equals("y")){
                System.out.println("Thank you for choosing to continue playing.");
                gameEnd=false;
            }
            else{
                System.out.println(" You have entered something other than Y/y");
                System.out.println(" The Game Ends ");
                System.out.println(" GoodBye ");
                gameEnd=true;
            }

        }
        // The user chooses to end the game
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static void gamePlayOC(Player[] players, int numPlayers){
        int first=0;
        int second=1;
        boolean gameEnd=false;
        boolean turn = false;
        int mid =0;

        if (numPlayers>2){
            second = numPlayers/2;
            mid=numPlayers/2 -1;
        }

        Scanner userInput = new Scanner(System.in);
        String userIn;

        // this is fixed
        int boardSize = 6;
        System.out.println("The size of the board is "+boardSize+", which is fixed for the current Game.");
        Board orderChaos=new Board(boardSize,boardSize);
        orderChaos.createBoardPiece();
        orderChaos.printBoard(); // initial printout of the board game
        int maxBoardSize = 35; // this is fixed


        //
        System.out.println("Order and Chaos Start.");
        System.out.println("----------------------------------------------------------------------------");
        while(!gameEnd){
            boolean winNext = false;
            boolean gameContinue = false;
            // clears the marks on the board for a new game
            orderChaos.emptyBoard();

            while(!winNext && !gameContinue){
                if (turn){
                    // players in the first team
                    chooseMark(players[first]);
                    placeMark(orderChaos, players[first], maxBoardSize);
                    winNext = checkOcWin(orderChaos,players[first].getPlayerMark());

                    if (winNext == false){
                        turn = !turn; // change turns
                    }
                    if(orderChaos.getOccupancy() == maxBoardSize){
                        gameContinue =true; // board filled out
                    }
                    if (numPlayers>2){
                        // TO-DO need something to change player in the same team
                        if (first+1>mid){
                            first = 0;
                        }
                        else if(first+1<=mid){
                            first++;
                        }
                    }
                    // if there are only two players in the game, the first and second does not change
                }
                else{
                    // for the second player || the current player in the second team
                    // the chaos team, where the win condition would be fill the board. Not sure how to do the not possible to win for order.
                    chooseMark(players[second]);
                    placeMark(orderChaos, players[second], maxBoardSize);
//                    winNext = checkOcWin(orderChaos,players[second].getPlayerMark());
//                    //System.out.println("WinNext "+winNext+" players[first] "+players[first].getPlayerID());
//                    if (!winNext){
//                        // when the current player's move is not a win condition
//                        turn = !turn; // change turn with a not condition
//                    }
                    if(orderChaos.getOccupancy() == maxBoardSize){
                        // the board is filled out, end game
                        gameContinue =true;
                        // TO-DO (Done) need to distinguish between win and board filled out
                    }
                    if (numPlayers>2){
                        // TO-DO need something to change player in the same team
                        if (second+1>numPlayers){
                            second= numPlayers/2 ;
                        }
                        else if(second+1<=numPlayers-1){
                            second++;
                        }
                    }
                }
            }

            if (winNext){
                // if there is a win increment the user statistics and display user statistics
                // while also increment the team's statistics
                // use the turn to distinguish between players
                orderChaos.printBoard();
                int tempRef= first; // temporary reference to the current team, if there is more than two players
                String ordC= "order";
                if (turn){
                    // first team
                    tempRef=first;
                    ordC ="Order";
                }
//                else{
//                    // second team
//                    tempRef=second;
//                    ordC ="Chaos";
//                }
                System.out.println("Players in team "+ordC+" Win.");

                incrementPlayerStats(players, numPlayers, tempRef);
                // there is a win for one of the teams, increment the win for winner
                // increment the loss of the lossing team.

                // print winner players id
                printWinnerPlayersId(players, numPlayers, tempRef);

            } else if (!winNext && gameContinue){
                System.out.println("The board is filled out. The Chaos team wins.");
                incrementPlayerStats(players, numPlayers, second);
                printWinnerPlayersId(players, numPlayers, second);
            }

            printPlayerStats(players, numPlayers); // print player stats

            // TO-DO win condition, board filled condition, ask the user if they want to continue.
            // only one player could decide if the game continues
            System.out.println("Do you want to play another game?");
            System.out.println("1. Yes, Enter Y/y");
            System.out.println("2. No, Enter anything else");
            userIn = userInput.nextLine();
            if (userIn.equals("Y") || userIn.equals("y")){
                System.out.println("Thank you for choosing to continue playing.");
                gameEnd=false;
            }
            else{
                System.out.println(" You have entered something other than Y/y");
                System.out.println(" The Game Ends ");
                System.out.println(" GoodBye ");
                gameEnd=true;
            }
        }



    }
}
