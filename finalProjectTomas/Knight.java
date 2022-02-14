import greenfoot.*; 
import java.util.ArrayList;

public class Knight extends Piece
{
    private int[][] pieceList;
    private int player;
    private int number;
    private ArrayList<Pointer> pointers = new ArrayList<Pointer>();
    public Knight(int player, int[][] pieceList){
        this.pieceList = pieceList;
        this.player = player;
        if(player == 1) number = 2;
        else number = 9;
    }
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
              int[] rowCol = getRowCol(getX(), getY());
              if(checkPlayer(super.globalPlayer, player))
              pointer(pieceList,rowCol[0],rowCol[1],pointers);
        }
        int[][] locations = super.Movement(this);
        if(checkMate(pieceList)){
                        MyWorld.checkmated = true;
                        Greenfoot.stop();
                    }
        if(locations[0][0] != -1){
        if(islegal(locations[0][0],locations[0][1],locations[1][0],locations[1][1])){
                if(checkPlayer(super.globalPlayer, player)){
                if(checkifempty(pieceList, locations[1][0], locations[1][1])){
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
             for(int i= 0; i < pointers.size();i++){    
        getWorld().removeObject(pointers.get(i));
    }
                }
        
        
    }    
    
    public boolean islegal(int row1, int column1, int row2, int column2){
        if((row1+1 == row2 && column1 + 2 == column2) || (row1-1 == row2 && column1 -2 == column2)){
            return true;
        }
        else if((row1 -1 == row2 && column1+2 == column2) || (row1+1 == row2 && column1-2 == column2)){
            return true;
        }
        else if((row1+2 == row2 && column1 + 1== column2) || (row1-2 == row2 && column1-1 == column2)){
            return true;
            }
        else if((row1+2 == row2 && column1 -1 == column2) || (row1-2 == row2 && column1+1 == column2)){
            return true;
        }
        return false;
    }
    public boolean checkifjump(int x1, int y1, int x2, int y2, int[][] piecesList){
    return true;
}

@Override
public boolean checkifempty(int[][] list, int x, int y){
        if(list[x][y] == 0) return true;
        else{
         if(globalPlayer == 1){
                if(list[x][y] > 6) return true;
            }
            else{
                if(list[x][y] < 7) return true;
            }
            return false;
        }
    }
//this is the pointer for the knight piece
public void pointer(int[][] pieceList, int row, int col, ArrayList<Pointer> list){
    int row2;
    int col2;
    row2 = row+1;
    if(row2 < 8){
        col2 = col+2;
        if(col2 < 8){
              if(pieceList[row2][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row2, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row2,col2);
                      list.add(point);
                  }
                }
        }
        col2 = col-2;
        if(col2 >= 0){
              if(pieceList[row2][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row2, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row2,col2);
                      list.add(point);
                  }
                }
        }
    }
    row2 = row-1;
    if(row2 >= 0){
          col2 = col+2;
        if(col2 < 8){
              if(pieceList[row2][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row2, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row2,col2);
                      list.add(point);
                  }
                }
        }
        col2 = col-2;
        if(col2 >= 0){
              if(pieceList[row2][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row2, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row2,col2);
                      list.add(point);
                  }
                }
        }
    }
    row2 = row+2;
    if(row2 < 8){
          col2 = col+1;
        if(col2 < 8){
              if(pieceList[row2][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row2, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row2,col2);
                      list.add(point);
                  }
                }
        }
        col2 = col-1;
        if(col2 >= 0){
              if(pieceList[row2][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row2, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row2,col2);
                      list.add(point);
                  }
                }
        }
    }
    row2 = row-2;
    if(row2>=0){
          col2 = col+1;
        if(col2 < 8){
              if((pieceList[row2][col2] == 0) ||(globalPlayer == 1 && pieceList[row2][col2] > 6)){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row2, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row2,col2);
                      list.add(point);
                  }
                }
        }
        col2 = col-1;
        if(col2 >= 0){
              if(pieceList[row2][col2] == 0){
        int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row2, col2);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row2,col2);
                      list.add(point);
                  }
                }
        }
    }
    }
    
}
