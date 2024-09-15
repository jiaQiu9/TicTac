public class Board {
    private int Turn;
    private Box[][] Board; //Maybe a seperate class for each box on the board
    private int[] pieces;
    private String[][] playerWinLoss;
    private String[][] moves;
    private int boardSizeM; //store the row number of the board
    private int boardSizeN; //store the column number of the board

    public Board(int boardM, int boardN){
        this.boardSizeM=boardM;
        this.boardSizeN=boardN;

        this.Board=new Box[this.boardSizeM][this.boardSizeN];

    }
    public void createBoardPiece(){
        int count = this.getBoardSizeM() * this.getBoardSizeN();
        int current = 0;
        for (int i=0;  i<this.getBoardSizeM();i++){
            for (int j=0; j<this.getBoardSizeN(); j++){
                if (current<count){
                    this.Board[i][j]= new Box(current) ;
                    current++;
                }
            }
        }
    }
    public int getBoardSizeM(){
        return this.boardSizeM;
    }
    public int getBoardSizeN(){
        return this.boardSizeN;
    }
}
