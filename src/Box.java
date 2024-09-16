public class Box {
    private int id;
    private Boolean occupied;
    private String mark; // The marking of X or O

    public Box(int id){
        this.occupied = false;
        this.id = id;
        this.mark = null; // set the mark to null initially, because no player made a move yet
    }
    public void setOccupied(Boolean occupied){
        this.occupied = occupied;
    }
    public void setMark(String mark){
        this.mark = mark;
    }

    public boolean isOccupied(){
        return this.occupied;
    }

    public void emptyMark(){
        this.mark = null;
    }
    public void emptyOccupied(){
        this.occupied = false;
    }

    public int getId(){
        return this.id;
    }

    public String getMark(){
        return this.mark;
    }

}
