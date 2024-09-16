public class Board {
    private int Turn; // keep track of player trun
    private Box[][] Board; //store separate class for each box on the board
    private String[][] playerWinLoss;
    private String[][] moves;
    private int boardSizeM; //store the row number of the board
    private int boardSizeN; //store the column number of the board

    public Board(int boardM, int boardN){
        this.boardSizeM=boardM;
        this.boardSizeN=boardN;
        this.Board=new Box[this.boardSizeM][this.boardSizeN];

    }
    // creates boxes objects on the board with unique id
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


    // Method used printout the board
    public void printBoard(){

        for (int i=0;i<this.getBoardSizeM();i++){
            for (int k=0; k<this.getBoardSizeN()*2; k++){
                System.out.print("_");
            }
            System.out.println();
            for (int j=0; j<this.getBoardSizeN(); j++){

                if (this.Board[i][j].getMark() == null){
                    System.out.print(" |");
                }
                else{
                    System.out.print(this.Board[i][j].getMark()+"|");
                }
            }
            System.out.println();
        }
    }
}
