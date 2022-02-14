import greenfoot.*; 
import java.util.ArrayList; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class King extends Piece
{
    private int player;
    private int[][]pieceList;
    private int number;
    ArrayList<Pointer> pointer = new ArrayList<Pointer>();
    public King(int player, int[][] pieceList){
        this.player = player;
        this.pieceList = pieceList;
        if(player ==1) number = 4;
        else number = 11;
    }
    public void act()
    {
         if(Greenfoot.mouseClicked(this)){
              int[] rowCol = getRowCol(getX(), getY());
              if(checkPlayer(super.globalPlayer, player))
              kingPointer(pieceList,rowCol[0],rowCol[1],pointer);
        }
        
         int[][] locations = super.Movement(this);
    if(checkMate(pieceList)){
                        MyWorld.checkmated = true;
                        Greenfoot.stop();
                    }
    if(locations[0][0] != -1){
        if(islegal(locations[0][0],locations[0][1],locations[1][0],locations[1][1])){
                if(checkPlayer(super.globalPlayer,player)){
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
            //this removes the pointers after a move is finished or abandoned
            for(int i = 0; i < pointer.size();i++){
                getWorld().removeObject(pointer.get(i));
            }
                }
    }
    public boolean islegal(int row1,int column1, int row2, int column2){
        if(row1+1 == row2 && column1+1 == column2){
        return true;
    }
        else if(row1+1 == row2 && column1-1 == column2){
        return true;
    }
        else if(row1-1 == row2 && column1+1 == column2){
            return true;
        }
        else if(row1-1 == row2 && column1-1 == column2){
            return true;
        }
        else if(row1 == row2){
            if(column1+1 == column2 || column1-1 == column2)
            return true;
        }
        else if(column1 == column2){
            if(row1+1 == row2 || row1-1 == row2) return true;
            
        }
        return false;
    }
    public boolean checkifjump(int x1, int y1, int x2, int y2, int[][] piecesList){
    return true;
}
@Override
public boolean checkifempty(int[][] list, int x, int y){
    
        // if its not empty then check for any captures available
        if(list[x][y] == 0) return true;
        else {
            //if player == 1 the capture must be player's 2 piece 
            if(globalPlayer == 1){
                if(list[x][y] > 6) return true;
            }
            else{
                if(list[x][y] < 7) return true;
            }
            return false;
        }
    }
    //this is the king's pointer method.
    //it predicts whether that move puts the king in check if it does, it doesn't point to it.
 public void kingPointer(int[][] pieceList, int row, int column, ArrayList<Pointer> point){
        int king;
     if(globalPlayer == 1){
          king = 4;  
        }
        else{
            king = 11;
        }
        int row1 = row+1;
        int column1 = column;
        if(row1 < 8){
        if(pieceList[row1][column1] == 0){
            int[][] templist = listCopy(pieceList);
            updateList(king,templist,row,column,row1,column1);
            if(!checkKing(templist)){
                Pointer Kingpointer = new Pointer();
                getWorld().addObject(Kingpointer, row1,column1);
                point.add(Kingpointer);
            }
    
        }  
        column1 = column-1;
        if(column1 >=0){
            if(pieceList[row1][column1] == 0){
            int[][] templist = listCopy(pieceList);
            updateList(king,templist,row,column,row1,column1);
            if(!checkKing(templist)){
                Pointer Kingpointer = new Pointer();
                getWorld().addObject(Kingpointer, row1,column1);
                point.add(Kingpointer);
            
    }
}
    column1 = column+1;
    if(column1 < 8){
    if(pieceList[row1][column1] == 0){
            int[][] templist = listCopy(pieceList);
            updateList(king,templist,row,column,row1,column1);
            if(!checkKing(templist)){
                Pointer Kingpointer = new Pointer();
                getWorld().addObject(Kingpointer, row1,column1);
                point.add(Kingpointer);
            
    }   
}
}
}
    }
     row1 = row-1;
      if(row1 >=0){
        if(pieceList[row1][column1] == 0){
            int[][] templist = listCopy(pieceList);
            updateList(king,templist,row,column,row1,column1);
            if(!checkKing(templist)){
                Pointer Kingpointer = new Pointer();
                getWorld().addObject(Kingpointer, row1,column1);
                point.add(Kingpointer);
            }
    } 
    //diagonal pointer check
    column1 = column+1;
    if(column1 < 8){
        if(pieceList[row1][column1] == 0){
            int[][] templist = listCopy(pieceList);
            updateList(king,templist,row,column,row1,column1);
            if(!checkKing(templist)){
                Pointer Kingpointer = new Pointer();
                getWorld().addObject(Kingpointer, row1,column1);
                point.add(Kingpointer);
            
    }   
}
    }
    column1 = column-1;
    if(column1 >= 0){
        if(pieceList[row1][column1] == 0){
            int[][] templist = listCopy(pieceList);
            updateList(king,templist,row,column,row1,column1);
            if(!checkKing(templist)){
                Pointer Kingpointer = new Pointer();
                getWorld().addObject(Kingpointer, row1,column1);
                point.add(Kingpointer);
            
    }   
}
    }
}
    row1 = row;
    column1 = column+1;
    if(column1 < 8){
         if(pieceList[row1][column1] == 0){
            int[][] templist = listCopy(pieceList);
            updateList(king,templist,row,column,row1,column1);
            if(!checkKing(templist)){
                Pointer Kingpointer = new Pointer();
                getWorld().addObject(Kingpointer, row1,column1);
                point.add(Kingpointer);
            
    }   
}   
}
column1 = column-1;
if(column1>=0){
    if(pieceList[row1][column1] == 0){
            int[][] templist = listCopy(pieceList);
            updateList(king,templist,row,column,row1,column1);
            if(!checkKing(templist)){
                Pointer Kingpointer = new Pointer();
                getWorld().addObject(Kingpointer, row1,column1);
                point.add(Kingpointer);
            
    }   
}
}
}
}


