import greenfoot.*;  
import java.util.ArrayList;


public class Rook extends Piece
{
    private int[][] pieceList;
    private int player;
    private int number;
    ArrayList<Pointer> point = new ArrayList<Pointer>();
    public Rook(int player,int[][] pieceList){
    this.pieceList = pieceList;
    this.player = player;
    if(player == 1) number = 1;
    else number = 8;
    
}
    public void act() 
    {
        int[][] locations = super.Movement(this);
        if(checkMate(pieceList)){
              MyWorld.checkmated = true;
                        Greenfoot.stop();
                    }
         if(Greenfoot.mouseClicked(this)){
              int[] rowCol = getRowCol(getX(), getY());
              if(checkPlayer(super.globalPlayer, player)){
              rookPointer(pieceList,rowCol[0],rowCol[1],point);
            }
        }
    if(locations[0][0] != -1){
        if(islegal(locations[0][0],locations[0][1],locations[1][0],locations[1][1])){
                  if(checkPlayer(super.globalPlayer, player)){
                  if(checkifjump(locations[0][0],locations[0][1],locations[1][0],locations[1][1],pieceList)){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, locations[0][0], locations[0][1],locations[1][0], locations[1][1]);
                 if(!checkKing(tempList)){
                 playBoardSound();
                 super.globalPlayer = switchPlayer(super.globalPlayer);     
                 updateList(number, pieceList, locations[0][0], locations[0][1],locations[1][0], locations[1][1]);
                 setLocation(locations[2][0], locations[2][1]);
                 if(isTouching(null)){
                     removeTouching(null);
                 }
                }
            }
            }
        }
        for(int i=0; i < point.size();i++){
                 getWorld().removeObject(point.get(i));
        }
        
                }
    
}
    
    public boolean islegal(int row1, int column1, int row2, int column2){
         if(column1 == column2 && row1 != row2){
             return true;
           }
         if(row1 == row2 && column1 != column2) return true;  
    
    return false;
        
    }
    //this method is an abstract method from the piece clas
    // it is used to check that if the move is attainable(it doesn't jump over pieces, and goes to an empty space)
    public boolean checkifjump(int row1, int column1, int row2, int column2, int[][] piecesList){
        return RookJumpCheck(row1, column1, row2, column2, piecesList);
    }
    public void rookPointer(int[][] pieceList, int row, int column, ArrayList<Pointer> list){
        int row1 = row+1;
        while(row1 < 8){
            if(pieceList[row1][column] == 0){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, column,row1, column);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row1,column);
                      list.add(point);
                  }
            }
            else{
                break;
            }
            row1++;
        }
        row1 = row-1;
        while(row1>= 0){
            if(pieceList[row1][column] == 0){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, column,row1, column);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row1,column);
                      list.add(point);
                  }
            }
            else{
                break;
            }
            row1--;
        }
       int column1 = column+1;
        while(column1 < 8){
            if(pieceList[row][column1] == 0){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, column,row, column1);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row,column1);
                      list.add(point);
                  }
            }
            else{
                break;
            }
             column1++;
        }
        column1 = column-1;
        while(column1 >=0){
             if(pieceList[row][column1] == 0){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, column,row, column1);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row,column1);
                      list.add(point);
                  }
            }
            else{
                break;
            }
             column1--;
        }
        
    }
}
