public class Player {
    private String playerName;
    private char playerMark;
    private int[] playerLossWin;

    public Player(String playerName, char playerMark){
        this.playerName = playerName;
        this.playerMark = playerMark;
        this.playerLossWin = new int[2]; // array of int that represetns the win loss count of players
    }

    public String getPlayerName(){
        return this.playerName;
    }
    public char getPlayerMark(){
        return this.playerMark;
    }
    public void setPlayerWin(){
        this.playerLossWin[0] += 1;
    }
    public void setPlayerLoss(){
        this.playerLossWin[1] += 1;
    }
}
