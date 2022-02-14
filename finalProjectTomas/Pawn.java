import greenfoot.*; 
import java.util.ArrayList;


public class Pawn extends Piece
{
    private int player;
    private int[][] pieceList;
    private int number;
    private boolean isfirst = true;
    ArrayList<Pointer> point = new ArrayList<Pointer>();
    
    public Pawn(int player, int[][] pieceList){
        this.player = player;
        this.pieceList = pieceList;
        if(player == 1) number = 6;
        else number = 7;
        
    }
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
              int[] rowCol = getRowCol(getX(), getY());
              if(checkPlayer(super.globalPlayer, player))
              pawnPointer(pieceList,rowCol[0],rowCol[1],point,isfirst);
        }
        if(checkMate(pieceList)){
                      MyWorld.checkmated = true;
                        Greenfoot.stop();
                    }
        int[][] locations = Movement(this);
        if(locations[0][0] != -1){
        if(islegalforPawn(isfirst,locations[0][0],locations[0][1],locations[1][0],locations[1][1],pieceList)){ 
                if(checkPlayer(super.globalPlayer, this.player)){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, locations[0][0], locations[0][1],locations[1][0], locations[1][1]);
                 if(!checkKing(tempList)){
                 playBoardSound();  
                 setLocation(locations[2][0], locations[2][1]);
                 updateList(number, pieceList, locations[0][0], locations[0][1],locations[1][0], locations[1][1]);
                 isfirst = false;
                 if(isTouching(null)){
                     removeTouching(null);
                    }
                    changePawn((locations[2][1]-33)/59);
                    super.globalPlayer = switchPlayer(super.globalPlayer); 
                 }
        }
        }
        World currentWorld = getWorld();
        if (currentWorld instanceof MyWorld) {
            for(int i= 0; i < point.size();i++){   
                   currentWorld.removeObject(point.get(i));
                }
            }
    }

        
                
                }  
    
    public boolean islegalforPawn(boolean isfirst,int row1, int column1, int row2, int column2,int[][] pieceList){
        if(player == 2){
         if((column1 == column2) && (row1-1 == row2)){
            if(pieceList[row2][column2] == 0){
            return true;
        }
        }
        if(isfirst && (column1 == column2) && (row1-2 == row2)){
            if(pieceList[row1-1][column1] == 0)
            if(pieceList[row2][column2] == 0)
            return true;
        }
        //check if it is one diagonal move and one row move for the capture
        if((column2 == column1 -1 || column2 == column1+1) && row1-1 ==row2){
            //check if its the other player's piece and also check that its not empty
            //and capture;
            if(pieceList[row2][column2]< 7 && pieceList[row2][column2] != 0) 
            return true;
        }
    }
    else{
        if((column1 == column2) && (row1+1 == row2)){
             if(pieceList[row2][column2] == 0)
            return true;
    }
    if(isfirst && (column1 == column2) && (row1+2 == row2)){
            // this checks that the pawn does not jump over pieces in its first move
            if(pieceList[row1+1][column1] == 0)
             if(pieceList[row2][column2] == 0)
            return true;
        }   
        //this is for the capture
        if((column1 == column2-1 || column1 == column2+1) && row1+1 == row2){
            // we have to check if there is a piece to capture
            if(pieceList[row2][column2]> 6 && pieceList[row2][column2] != 0 ) 
            return true;
        }
}
return false;
}
public boolean checkifjump(int row1, int column1, int row2, int column2, int[][] piecesList){
    return true;
}
public boolean islegal(int x, int y, int x2, int y2){
    return false;
}
// this method switches the pawn when it reaches either end
public void changePawn(int row){
    if((globalPlayer == 1 && row == 7)|| (globalPlayer == 2 && row == 0)){
        int[] rowCol = getRowCol(this.getX(), this.getY());
        Greenfoot.setWorld(new world2(getWorld(),globalPlayer,pieceList,rowCol[0],rowCol[1]));
        getWorld().removeObject(this);
    }
    }
    //this is the pawnPointer
    public void pawnPointer(int[][] pieceList,int row, int column,ArrayList<Pointer> list,boolean isfirst){
        int increment;
    if(globalPlayer == 1){
         increment = 1;
    }   
    else{
        increment = -1;
    }
    if(pieceList[row+increment][column] == 0){
                 int[][] tempList = listCopy(pieceList);
                 updateList(number, tempList, row, column,row+increment, column);
                  if(!checkKing(tempList)){
                      Pointer point = new Pointer();
                      getWorld().addObject(point,row+increment,column);
                      list.add(point);
                  }
                }
                  //if its the pawn's first move check row+2 or row-2
                  if(isfirst && pieceList[row+increment][column] == 0){
                       int[][] tempList1 = listCopy(pieceList);
                 updateList(number, tempList1, row, column,row+increment+increment, column);
                  if(!checkKing(tempList1)){
                      Pointer point = new Pointer();
                     if(row+increment+increment >= 0 && row+increment+increment+increment < 8){
                      if(pieceList[row+increment+increment][column] == 0){
                      getWorld().addObject(point,row+increment+increment,column);
                      list.add(point);
                  }
                }
                }
                  }

        
    }
}

