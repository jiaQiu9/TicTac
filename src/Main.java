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
        System.out.println("Hello and welcome!");

        // check which game the user wants to play
        userGame = gameChoice();

        if (userGame==1){
            // for tic-tac-toe
            // get the number of players

            System.out.print("Please enter the number of players(Enter numeric values): ");
            String userIn = userInput.nextLine();
            System.out.println();
            while(checkInt(userIn)){
                System.out.print("Please enter the number of players(Enter a numeric value): ");
                userIn = userInput.nextLine();
                System.out.println();
            }
            numPlayer = Integer.parseInt(userIn);
            System.out.println("The number of players are "+numPlayer);

            // get the size of the board in terms of nxn, by now
            System.out.print("Please enter the size(nxn size) of the board :(Enter a numeric value)  ");
            userIn = userInput.nextLine();
            while(checkInt(userIn)){
                System.out.print("Please enter the size of the board :(Enter a numeric value)  ");
                userIn = userInput.nextLine();
                System.out.println();
            }
            boardSize = Integer.parseInt(userIn);
            System.out.println("The size of the board is "+boardSize);

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
    // this method will be used more than once
    public static boolean checkInt(String userIn){
        boolean isValid=false;
        try{
            int num = Integer.parseInt(userIn);
        }
        catch(NumberFormatException e){
            isValid=false;
        }
        return isValid;
    }
    public static int gameChoice(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please choose the game you want to play.");
        System.out.println("1.Tic-Tac-Toe");
        System.out.println("2. Order or Chaos");
        //System.out.print("Choose your game:(Enter numeric values)");
        String userIn="";
        boolean isValid = true; // for the while loop to run
        while(isValid){
            System.out.print("Choose your game:(Enter numeric values)");
            userIn = userInput.nextLine();
            System.out.println();
            if (checkInt(userIn)){
                if (Integer.valueOf(userIn)==1||Integer.valueOf(userIn)==2){
                    isValid = false;
                }
            }
        }

        return Integer.valueOf(userIn);
    }

    public static int getNumPlayer(){
        // the number of player based on the user input
        // some games have fixed player num, some have flexible player num
        Scanner userInput = new Scanner(System.in);

        return 0;

    }

}

