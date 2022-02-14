import greenfoot.*;  
import java.util.ArrayList;

public class Queen extends Piece
{
    private int[][] pieceList;
    private int player;
    private ArrayList<Pointer> pointer = new ArrayList<Pointer>();
    
    //this is a variable used for each piece, it updates the array.
    //Each piece has a different number as long as they are different 
    private int number;
    public Queen(int player,int[][] pieceList){
        this.pieceList = pieceList;
        this.player = player;
        if(player == 1)
        number = 5;
        else 
        number = 12;
    }
    public void act()
    {
    //method inherited from the superClass
     if(Greenfoot.mouseClicked(this)){
              int[] rowCol = getRowCol(getX(), getY());
              if(checkPlayer(super.globalPlayer, player))
              QueenPointer(pieceList,rowCol[0],rowCol[1],pointer);
        }
        if(checkMate(pieceList)){
                        MyWorld.checkmated = true;
                        Greenfoot.stop();
                    }
    int[][] locations = super.Movement(this);
    if(locations[0][0] != -1){
        if(islegal(locations[0][0],locations[0][1],locations[1][0],locations[1][1])){
                if(checkPlayer(super.globalPlayer, player)){
                 if(checkifjump(locations[0][0],locations[0][1], locations[1][0], locations[1][1]
                 ,pieceList)){
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
     for(int i= 0; i < pointer.size();i++){    
        getWorld().removeObject(pointer.get(i));
        }
                }
                
    
        
    }
    public boolean islegal(int row1, int column1, int row2, int column2){
        //this are the movement rules for the queen
        if(Math.abs(row2 - row1) == Math.abs(column2 - column1)){
            return true;
        }
        else if(row2 == row1){
        if(column1 != column2)    
        return true;
    }
    else if(column1 == column2){
        if(row1 != row2)
        return true;
    }
        return false;
    }
    
    //the queen inherits the jump checks of both rook and bishop
    public boolean checkifjump(int row1, int column1, int row2, 
    int column2, int[][] piecesList){
    if(row1 == row2 || column1 == column2){
        return RookJumpCheck(row1, column1, row2, column2, piecesList);
    }
    return bishopjumpcheck(row1, column1, row2, column2, piecesList);
   }
   public void QueenPointer(int[][] pieceList, int row,int col,ArrayList<Pointer> list){
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
        row1 = row+1;
        while(row1 < 8){
            if(pieceList[row1][col] == 0){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row1, col);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row1,col);
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
            if(pieceList[row1][col] == 0){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row1, col);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row1,col);
                      list.add(point);
                  }
            }
            else{
                break;
            }
            row1--;
        }
       int column1 = col+1;
        while(column1 < 8){
            if(pieceList[row][column1] == 0){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row, column1);
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
        column1 = col-1;
        while(column1 >=0){
             if(pieceList[row][column1] == 0){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, col,row, column1);
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
        

