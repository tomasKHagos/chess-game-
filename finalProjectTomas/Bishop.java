import greenfoot.*;
import java.util.ArrayList;

public class Bishop extends Piece
{
    private int[][] pieceList;
    private int player;
    private int number;
    private ArrayList<Pointer> pointer = new ArrayList<Pointer>();
    
     public Bishop(int player,int[][] pieceList){
         this.pieceList = pieceList;
         this.player = player;
         if(player == 1) number = 3;
         else number = 10;
     }
    public void act()
    {
        
        if(Greenfoot.mouseClicked(this)){
              int[] rowCol = getRowCol(getX(), getY());
              if(checkPlayer(super.globalPlayer, player))
              bishopPointer(pieceList,rowCol[0],rowCol[1],pointer);
        }
        int[][] locations = Movement(this);
        if(checkMate(pieceList)){
                        MyWorld.checkmated = true;
                        Greenfoot.stop();
                    }
        if(locations[0][0] != -1){ 
        if(islegal(locations[0][0],locations[0][1],locations[1][0],locations[1][1])){
                if(checkPlayer(super.globalPlayer, player)){
                 if(checkifjump(locations[0][0],locations[0][1], 
                 locations[1][0], locations[1][1],pieceList)){
                  int[][] tempList = listCopy(pieceList);
                  updateList(number, tempList, locations[0][0], locations[0][1]
                 ,locations[1][0], locations[1][1]);
                  if(!checkKing(tempList)){
                  playBoardSound();
                  super.globalPlayer = switchPlayer(super.globalPlayer);    
                  updateList(number, pieceList, locations[0][0], locations[0][1]
                 ,locations[1][0], locations[1][1]);
                  setLocation(locations[2][0], locations[2][1]);
                 if(isTouching(null)){
                     removeTouching(null);
                 }
                }
                
                
            }
            }
        }
        for(int i= 0; i < pointer.size();i++){    
        getWorld().removeObject(pointer.get(i));
    }
        }
        }
        
    
    @Override
    public boolean islegal(int row1, int column1, int row2, int column2){  
        if(row1 != row2 && column1 != column2){
        if(Math.abs(row2 - row1) == Math.abs(column2 - column1)){
            return true;
        }
    }
        return false;
    }
    
public boolean checkifjump(int row1, int column1, int row2, int column2,
 int[][] piecesList)
 {
    return bishopjumpcheck(row1, column1, row2, column2, piecesList);
}
public void bishopPointer(int[][] pieceList, int row, int col,ArrayList<Pointer> list){
    int row1 = row+1;
    int col2 = col+1;
    while(row1 < 8 && col2 < 8){
        if(pieceList[row1][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row1, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row1,col2);
                      list.add(point);
                  }
                }
                else{
                    break;
                }
                  row1++;
                  col2++;
    }
    row1 = row+1;
    col2 = col-1;
    while(row1 < 8 && col2 >=0){
         if(pieceList[row1][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row1, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row1,col2);
                      list.add(point);
                  }
                }
                else{
                    break;
                }
                  row1++;
                  col2--;
    }
    
    row1 = row-1;
    col2 = col+1;
    while(row1 >= 0 && col2 < 8){
         if(pieceList[row1][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row1, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row1,col2);
                      list.add(point);
                  }
                }
                else{
                    break;
                }
                  row1--;
                  col2++;
    }
    row1 = row-1;
    col2 = col-1;
    while(row1 >= 0 && col2 >= 0){
         if(pieceList[row1][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row1, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row1,col2);
                      list.add(point);
                  }
                }
                else{
                    break;
                }
                  row1--;
                  col2--;
    }
    
    }
}
