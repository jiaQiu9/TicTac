public class Player {

    private int playerID;
    private String playerMark;
    private int[] playerLossWin;

    public Player(int playerID){
        this.playerID = playerID;
        this.playerMark = "";
        this.playerLossWin = new int[2]; // array of int that represetns the win loss count of players
    }

    public int getPlayerID(){
        return playerID;
    }
    public String getPlayerMark(){
        return this.playerMark;
    }

    // this could also be used for changing player mark in the order and chaos game
    // need an input checker
    public void setPlayerMark(String mark){
        this.playerMark = mark;
    }

    public void setPlayerWin(){
        this.playerLossWin[0] += 1;
    }
    public void setPlayerLoss(){
        this.playerLossWin[1] += 1;
    }

    public int getPlayerWin(){
        return this.playerLossWin[0];
    }
    public int getPlayerLoss(){
        return this.playerLossWin[1];
    }



}
