public class Board {

    private Box[][] Boardsur; //store separate class for each box on the board
    private int boardSizeM; //store the row number of the board
    private int boardSizeN; //store the column number of the board
    private int occupancy; // if this value is equal to the number of boxes on the board, then the board is filled out

    private String[][] playerWinLoss; // maybe there is no need for this
    private String[][] moves; //
    private int Turn; // keep track of player trun


    public Board(int boardM, int boardN){
        this.boardSizeM=boardM;
        this.boardSizeN=boardN;
        this.Boardsur=new Box[this.boardSizeM][this.boardSizeN];
        this.occupancy=0; // no moves on the board yet
    }
    // creates boxes objects on the board with unique id
    public void createBoardPiece(){
        int count = this.getBoardSizeM() * this.getBoardSizeN();
        int current = 0; // the id assigned to the current box object
        for (int i=0;  i<this.getBoardSizeM();i++){
            for (int j=0; j<this.getBoardSizeN(); j++){
                if (current<count){
                    this.Boardsur[i][j]= new Box(current) ;
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
            System.out.print("|");
            for (int j=0; j<this.getBoardSizeN(); j++){
                if (this.Boardsur[i][j].getMark() == null){
                    System.out.print(this.Boardsur[i][j].getId());
                    System.out.print(". |");
                }
                else{
                    System.out.print(this.Boardsur[i][j].getId()+". "+this.Boardsur[i][j].getMark()+"|");
                }
            }
            System.out.println();
            for (int k=0; k<this.getBoardSizeN()*2; k++){
                System.out.print("_");
            }
            System.out.println();
        }
    }
    // return the entire list of boxes
    public Box[][] getBoardsur(){
        return this.Boardsur; // return the box list on the board.
    }

    // return the box object at position n, which is the numeric presentation of the box on the board
    // the box with id of position
    public Box getBoxApos(int pos){
        int row = pos / this.boardSizeM;
        int col = pos % this.boardSizeM;
        return this.Boardsur[row][col];
    }

    // update the occupancy
    public void increaseOccupancy(){
        this.occupancy++;
    }

    // get occupancy
    public int getOccupancy(){
        return this.occupancy;
    }
}
