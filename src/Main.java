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

        if (userGame==1){
            // for tic-tac-toe
            // get the number of players
            // get the number of players, the user should enter an even number
            numPlayer = getNumPlayer();

            // get the size of the board in terms of nxn, by now
            boardSize = enterBoardSize();

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
                if (Integer.valueOf(userIn)%2==0){
                    isValid = true;
                    numPlayer = Integer.parseInt(userIn);
                }
            }
        }
        return numPlayer;

    }
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

}

