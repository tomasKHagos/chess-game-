import greenfoot.*;  
import java.util.List;
import java.util.ArrayList;


public abstract class Piece extends Actor
{
    MouseInfo mouse;
    private boolean clicked = false;
    private int counter = 0;
    // this static variable is to represent which player is playing
    public static int globalPlayer = 1;
    int[][] piecesPosition = {{1,2,3,4,5,3,2,1},{6,6,6,6,6,6,6,6},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {7,7,7,7,7,7,7,7},{8,9,10,11,12,10,9,8}};
    //this is a method for the piece movements 
    public int[][] Movement(Actor piece){
        int[] originalPos = getRowCol(piece.getX(), piece.getY());
         if(Greenfoot.mouseClicked(piece)){
            counter = 1;
            clicked = true;
        }
        if(counter > 1){
        if(Greenfoot.mouseClicked(null)){
            mouse = Greenfoot.getMouseInfo();
            int[] attemptedPos = getRowCol(mouse.getX(), mouse.getY());
            int[][] returnedPos = {{originalPos[0],originalPos[1]},{attemptedPos[0],attemptedPos[1]},
            {(35+attemptedPos[1]*59)+30,(35+attemptedPos[0]*59)+30}};
            counter = 0;
            clicked = false;
            return returnedPos;
        }
    }
    if(clicked)
    counter++;
    //this returned value is to tell that a complete move is not yet attempted. 
    //it is used inside the piece's class 
    int[][] returnedPos = {{-1,-1},{-1,-1}};
    return returnedPos;
    }
    // this methods get the row and column of a clicked space and returns it 
    public int[] getRowCol(int x, int y){
        int row = (y-33)/59;
        int column = (x-35)/59;
        int[] rowCol = {row, column}; 
        return rowCol;
    }
    //this method is implemented in all pieces to check if the move is legal
    public abstract boolean islegal(int row1, int column1, int row2, int column2);
    
    public boolean checkifempty(int[][] list, int x, int y){
        if(list[x][y] == 0) return true;
        return false;
    }
    
    public void updateList(int num,int[][] list, int initialx, int initialY, int currentX, int currentY){
        list[initialx][initialY] = 0;
        list[currentX][currentY] = num;
    }
     //this is a method that checks when a piece moves that it doesn't jump a piece
public abstract boolean checkifjump(int x1, int y1, 
int x2, int y2, int[][] pieceList);

// this is a method that checks the bishop does not jump any pieces while moving
// i placed this here because i wanted the queen to inherited this method
public boolean bishopjumpcheck(int row1, int column1, int row2, int column2, 
int[][] pieceList){
    int startingValue; 
    int endingValue;
    int increment;
    if(row1 < row2){
        startingValue = row1+1;
        endingValue = row2;
        if(column1 > column2) increment = -1;
        else{
            increment = 1;
        }
        for(int i = startingValue; i <= endingValue; i++){
            column1+= increment;
            if(pieceList[i][column1] != 0){
            if(i == endingValue){
                return captureCheck(i, column1,pieceList);
            }
            else
            return false;
        }
        }
        
    }
    else {
        startingValue = row1-1;
        endingValue = row2;
        if(column1 < column2) increment = 1;
        else increment = -1;
        for(int i = startingValue; i >= endingValue; i--){
            column1+= increment;
            if(pieceList[i][column1] != 0){
                if(i == endingValue)
                return captureCheck(i,column2,pieceList);
                else
                return false;
            }
            
        }
            }
    return true;
}

// this method verifies that the rook does not jump over any pieces
// it is also inherited by the queen
public boolean RookJumpCheck(int row1, int column1, int row2, int column2, int[][] piecesList){
     int starting_value;
    int ending_value;
    if(row1 == row2){
        if(column1 < column2){
            starting_value = column1+1;
            ending_value = column2;
            for(int i = starting_value; i <= ending_value; i++){
            if(piecesList[row1][i] != 0){
                if(i == ending_value){
                    return captureCheck(row1,i,piecesList);
                }
                else
                return false;
            }
        }
        
        }
        else{
            starting_value = column1-1;
            ending_value = column2;
            for(int i = starting_value; i >= ending_value; i--){
            if(piecesList[row1][i] != 0){
                if(i == ending_value){
                    return captureCheck(row1,i,piecesList);
                }
            else    
            return false;
        }
            
        }
        
    }
}
    else{
        if(row1 < row2){
            starting_value = row1+1;
            ending_value = row2;
            for(int i = starting_value; i <= ending_value; i++){
            if(piecesList[i][column1] != 0){
            if(i == ending_value){
                    return captureCheck(i,column1,piecesList);
                }
            else    
            return false;
        }
        }
            }
        else{
            starting_value = row1-1;
            ending_value = row2;
            for(int i = starting_value; i >= ending_value; i--){
            if(piecesList[i][column1] != 0){
            if(i == ending_value){
                    return captureCheck(i,column1,piecesList);
                }    
            else    
            return false;
        }
        }
        }
          
    } 
    return true;
   }

// this method checks if the right player is moving the right chess piece
// the first parameter takes in the static variable and the second 
//takes in the player variable assigned within
// each objects(pieces)
//and it checks if the player's turn matches' the player 
public boolean checkPlayer(int Superplayer, int player){
    if(Superplayer == player) return true;
    return false;
}
//this switches player's turn
public int switchPlayer(int superPlayer){
    if(superPlayer == 1) return 2;
    return 1;
}
public void playBoardSound(){
Greenfoot.playSound("chessAudio.wav");
}
public boolean captureCheck(int index1,int index2,int[][] pieceList){
    if(globalPlayer== 1){
            if(pieceList[index1][index2] > 6)
                   return true;
        }
        else{
            if(pieceList[index1][index2] < 7){
              return true;  
            }
            
        }
    return false;
}

//this method checks if the king is in check
public boolean checkKing(int[][] pieceList){
    int[] rowCol = findKingsPosition(pieceList);
    
    if((boolean)pawnCheck(pieceList, rowCol).get(0))
        return true;
    if((boolean)bishopCheck(pieceList,rowCol,0).get(0)) 
        return true;
    if((boolean)RookCheck(pieceList,rowCol,0).get(0)) 
        return true;   
    if((boolean)QueenCheck(pieceList,rowCol).get(0))
        return true;
    if((boolean)(knightCheck(pieceList,rowCol)).get(0) == true)
         return true;
    if(kingtokingCheck(pieceList,rowCol)) 
        return true;
    return false;
    }
public ArrayList<Object> pawnCheck(int[][] pieceList,int[] rowCol){
    int row,column;
    ArrayList<Object> returned = new ArrayList <Object>();
    if(globalPlayer == 2){
        row = rowCol[0] -1;
        column = rowCol[1]+1;
        if(row >=0 && column < 8){
            if(pieceList[row][column] == 6){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
            }
        }
        column = rowCol[1] -1;
        if(row >=0 && column >= 0){
        if(pieceList[row][column] == 6){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
        }
      
    }
       
       }
    
 else{
        row = rowCol[0] +1;
        column = rowCol[1]+1;
        if(row < 8 && column < 8){
        if(pieceList[row][column] == 7){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
        }
        
    }
       column = rowCol[1]-1;
       if(row < 8 && column >=0){
      if(pieceList[row][column] == 7){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
            }
 
    }
      }
                returned.add(false);
                returned.add(0);
                returned.add(0);
                return returned;

}
public ArrayList<Object> bishopCheck(int[][] pieceList, int[] rowCol,int queenUsingit){
   
    //this is a variable for the bishop
    //it represents the number in which the bishop is represented in the pieceList
      ArrayList<Object> returned = new ArrayList <Object>();
    int bishop;
    // i used this method for the queen check too so if the queen is not using it the queen using it variable will be 0
    if(queenUsingit==0){
    if(globalPlayer == 1) bishop = 10;
    else bishop = 3;
}
else{
    bishop = queenUsingit;
}
    int row = rowCol[0]-1;
    int column = rowCol[1]-1;
    while(row >= 0 && column >=0){
        if(pieceList[row][column] != 0){
            if(pieceList[row][column] == bishop){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
            }
               
            else
               break;
        }
        row--;
        column--;
    }
    //if nothing is found in that direction check the other way
    row = rowCol[0]-1;
    column = rowCol[1]+1;
    while(row >= 0 && column < 8){
           if(pieceList[row][column] != 0){
            if(pieceList[row][column] == bishop){
               returned.add(true);
               returned.add(row);
               returned.add(column);
               return returned;
            }
            else
               break;
        }
        row--;
        column++;
    }
    //if check not found in that direction try the other way
    row = rowCol[0] +1;
    column = rowCol[1]-1;
    while(row < 8 && column >= 0){
        if(pieceList[row][column] != 0){
            if(pieceList[row][column] == bishop){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
            }
            else break;
        }
        row++;
        column--;
    }
    
    //last direction
    row = rowCol[0] +1;
    column = rowCol[1] +1;
    while(row < 8 && column < 8){
              if(pieceList[row][column] != 0){
            if(pieceList[row][column] == bishop){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
            }
            else break;
        }
        row++;
        column++;        
    }
    
    //if none found return false
    returned.add(false);
    returned.add(0);
    returned.add(0);
    return returned;
}
public ArrayList<Object> RookCheck(int[][] pieceList, int[] rowCol,int queenUsingit){
    //this variable represents the rook in the pieceList
    ArrayList<Object> returned = new ArrayList <Object>();
    int rook;
    //i also used this method for the queen. So when the rook uses this method the queenUsing it will be 0
    if(queenUsingit==0){
    if(globalPlayer == 1) rook = 8;
    else rook = 1;
}
else{
    rook = queenUsingit;
}
    int row = rowCol[0];
    int column = rowCol[1]+1;
    //determine if  horizontal "check" is initiated
    while(column < 8){
        if(pieceList[row][column] != 0){
            if(pieceList[row][column] == rook){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
            }
            else break;    
        }
        column++;
    }
    column = rowCol[1]-1;
    while(column >= 0){
        if(pieceList[row][column] != 0){
            if(pieceList[row][column] == rook){
               returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
        }
            else 
            break;
        }
        column--;
    }
     //check vertically
    column = rowCol[1];
    row = rowCol[0] +1;
    while(row < 8){
          if(pieceList[row][column] != 0){
            if(pieceList[row][column] == rook){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
        }
            else{    
            break;
        }
        }
        row++;
    }
    row = rowCol[0] -1;
        while(row >= 0){
          if(pieceList[row][column] != 0){
            if(pieceList[row][column] == rook){
                returned.add(true);
                returned.add(row);
                returned.add(column);
                return returned;
        }
            else 
            break;
        }
        row--;
    }
     returned.add(false);
     returned.add(0);
     returned.add(0);;
     return returned;
    
}
public ArrayList<Object> QueenCheck(int[][] pieceList, int[] rowCol){
    
    int queen;
    ArrayList<Object> returned = new ArrayList <Object>();
    if(globalPlayer == 1){
        queen = 12;
    }
    else{
        queen = 5;
    }
    returned = RookCheck(pieceList,rowCol,queen);
    if((boolean)returned.get(0)){
        return returned;
    }
    returned = bishopCheck(pieceList,rowCol, queen);
    if((boolean)returned.get(0)){
        return returned;
    }
    returned.add(false);
    returned.add(0);
    returned.add(1);
    return returned;
}
public ArrayList<Object> knightCheck(int[][] pieceList,int[] rowCol){
    int knight;
    if(globalPlayer == 1) knight = 9;
    else knight = 2;
    int row = rowCol[0]+1;
    int column = rowCol[1];
    ArrayList<Object> returned = new ArrayList <Object>();
    
    
    if(row < 8){
        column = column-2;
        if(column>=0){
            if(pieceList[row][column] == knight){
               returned.add(true);
               returned.add(row);
               returned.add(column);
               return returned;
            }
        }
        column = rowCol[1]+2;
        if(column < 8){
             if(pieceList[row][column] == knight){
                returned.add(true);
               returned.add(row);
               returned.add(column);
               return returned;
            }
        }
        
    }
    row = rowCol[0] -1;
    if(row >= 0){
        column = rowCol[1]-2;
        if(column >=0){
            if(pieceList[row][column] == knight){
                returned.add(true);
               returned.add(row);
               returned.add(column);
               return returned;
            }
        }
        column = rowCol[1]+2;
        if(column < 8){
           if(pieceList[row][column] == knight){
               returned.add(true);
               returned.add(row);
               returned.add(column);
               return returned;
            }
        }
        
    }
    row = rowCol[0] -2;
    if(row >=0){
        column = rowCol[1] +1;
        if(column <8){
             if(pieceList[row][column] == knight){
                returned.add(true);
               returned.add(row);
               returned.add(column);
               return returned;
            }
        }
        column = rowCol[1]-1;
        if(column >=0){
             if(pieceList[row][column] == knight){
               returned.add(true);
               returned.add(row);
               returned.add(column);
               return returned;
            }
        }
    }
    row = rowCol[0]+2;
    if(row < 8){
        column = rowCol[1]-1;
        if(column >=0){
             if(pieceList[row][column] == knight){
              returned.add(true);
               returned.add(row);
               returned.add(column);
               return returned;
            }
        }
        column = rowCol[1]+1;
        if(column < 8){
             if(pieceList[row][column] == knight){
               returned.add(true);
               returned.add(row);
               returned.add(column);
               return returned;
            }
        }
    }
             returned.add(false);
               returned.add(0);
               returned.add(0);
    return returned;
}
public boolean kingtokingCheck(int[][] pieceList, int[] rowCol){
    int king;
    if(globalPlayer == 1) king = 11;
    else king = 4;
    int row = rowCol[0];
    int column = rowCol[1]-1;
    //check sides 
    if(column >=0){
        if(pieceList[row][column] == king){
            return true;
        }
    }
    column = rowCol[1]+1;
    if(column  < 8){
        if(pieceList[row][column] == king){
            return true;
        }
    }
     // check vertically
    row = rowCol[0] +1;
    column = rowCol[1];
    if(row < 8){
        if(pieceList[row][column] == king){
            return true;
        }
    }
    row = rowCol[0] -1;
    if(row >=0){
        if(pieceList[row][column] == king){
            return true;
        }
    }
    //check diagonally 
    row = rowCol[0] +1;
    if(row < 8){
        column = rowCol[1]+1;
        if(column < 8){
            if(pieceList[row][column] == king){
            return true;
        }
        column = rowCol[1]-1;
        if(column >=0){
            if(pieceList[row][column] == king){
            return true;
        }
        }
        }
    }
    row = rowCol[0]-1;
    if(row >=0){
        column = rowCol[1]+1;
        if(column < 8){
            if(pieceList[row][column] == king){
            return true;
        }
        column =  rowCol[1]-1;
        if(column >=0){
            if(pieceList[row][column] == king){
            return true;
        }
        }
        }
    }
    return false;
    }
// this keeps track of where the kings are at. Used for check and checkMate.
public int[] findKingsPosition(int[][] pieceList){
    int king;
    int[] kingPos = new int[2];
    if(globalPlayer == 1)  king = 4;
    else king = 11;
    for(int i = 0; i < 8; i++){
        for(int j= 0; j < 8; j++){
            if(pieceList[i][j] == king){
               kingPos[0] = i;
               kingPos[1] = j;
               return kingPos;
            }
        }
    }
    return kingPos;
}
//copying the arrayList
//this is used to store the original pieceList
//when a player attempts to move first store the positions before the move
public int[][] listCopy(int[][] pieceList){
    int[][] copiedList = new int[8][8];
    for(int i = 0; i < 8; i++){
       for(int j=0; j < 8; j++){
           copiedList[i][j] = pieceList[i][j];
        }
    }
    return copiedList;
}

//checkmate alogrithm explanation
//Check every piece that's there and check if they can move
//if one can
//player still not checkmated
//if none of the pieces can't move, checkMate
public boolean checkMate(int[][] pieceList){
     if(canPawnMove(pieceList)){
         return false;
     }
     if(canRookMove(pieceList,false)){
         return false;
     }
     if(knightMove(pieceList)){
     return false;
    }
    if(canBishopMove(pieceList,false)){
        return false;
    }
    if(canQueenMove(pieceList)){
        return false;
    }
    int[] rowCol = findKingsPosition(pieceList);
    if(canKingMove(rowCol[0],rowCol[1],pieceList)){
        return false;
    }
    return true;
}

public boolean checkCanBeCaptured(int row, int column,int[][] pieceList){
    //this checks if the checking Piece can be captured
    int[] rowCol = {row,column};
      if((boolean)pawnCheck(pieceList, rowCol).get(0)){
        return true;
    }
    if((boolean)bishopCheck(pieceList,rowCol,0).get(0)) 
        return true;
    if((boolean)RookCheck(pieceList,rowCol,0).get(0)) 
        return true;   
    if((boolean)QueenCheck(pieceList,rowCol).get(0)){
        return true;
    }
    if((boolean)knightCheck(pieceList,rowCol).get(0))
         return true;
    return false;
    }
public boolean canKingMove(int row,int column, int[][] pieceList){
   int row1 = row+1;
   int column1 = column;
   if(row1 < 8){
       if(checkifKingCanEscape(row,column,row1,column1,pieceList)){
           return true;
       }
   }
   row1 = row-1;
   if(row1 >= 0){
       if(checkifKingCanEscape(row,column,row1,column1,pieceList)){
           return true;
       }
   }
   row1 = row;
   column1 = column+1;
   if(column1 < 8){
       if(checkifKingCanEscape(row,column,row1,column1,pieceList)){
           return true;
       }
   }
   column1 = column-1;
   if(column1 >= 0){
        if(checkifKingCanEscape(row,column,row1,column1,pieceList)){
           return true;
       }
   }
   row1 = row+1;
   if(row1 < 8){
       column1 = column+1;
       if(column1 < 8){
        if(checkifKingCanEscape(row,column,row1,column1,pieceList)){
           return true;
       }
    }
       column1 = column-1;
       if(column1 >= 0){
            if(checkifKingCanEscape(row,column,row1,column1,pieceList)){
           return true;
       }
       }
       
   }
   row1 = row-1;
   if(row1 >= 0){
         column1 = column+1;
       if(column1 < 8){
        if(checkifKingCanEscape(row,column,row1,column1,pieceList)){
           return true;
       }
    }
       column1 = column-1;
       if(column1 >= 0){
            if(checkifKingCanEscape(row,column,row1,column1,pieceList)){
           return true;
       }
       }
   }
   return false;
   
}    

public boolean checkifKingCanEscape(int row, int column, int row2, int column2, int[][] pieceList){
    int number;
    if(globalPlayer == 1){
        number = 4;
    }
    else{
        number = 11;
    }
    if(checkifempty(pieceList, row2, column2)){
        int[][] tempList = listCopy(pieceList);
        updateList(number, tempList, row, column,row2, column2);
         if(!checkKing(tempList)){
             return true;
         }
    }
    else{
         
        if((globalPlayer == 1 && pieceList[row2][column2] > 6) || (globalPlayer == 2 && pieceList[row2][column2] < 7)){
             int[][] tempList = listCopy(pieceList);
        updateList(number, tempList, row, column,row2, column2);
         if(!checkKing(tempList)){
             return true;
         }
        }
    }
    
    return false;
}

public boolean movementCheckPawn(int row, int col, int row2, int column2,int[][] pieceList, int num){
    if(col == column2){
    if(pieceList[row2][column2] == 0){
        int[][] listCopied = listCopy(pieceList);
        updateList(num, listCopied, row, col, row2, column2);
        if(!checkKing(listCopied)){
            return true;
        }
        else{
            return false;
        }
    }
}
    else{
        if(pieceList[row2][column2] != 0){
        if((globalPlayer == 1 && pieceList[row2][column2] > 6) ||(globalPlayer == 2 && pieceList[row2][column2] < 7)){
            int[][] listCopied = listCopy(pieceList);
             updateList(num, listCopied, row, col, row2, column2);
             if(!checkKing(listCopied)){
            return true;
        }
        else{
            return false;
        }
        }
        
    }
}
    return false;
}
public boolean movementCheck(int row, int col, int row2, int column2,int[][] pieceList, int num){
    if(pieceList[row2][column2] == 0){
        int[][] listCopied = listCopy(pieceList);
        updateList(num, listCopied, row, col, row2, column2);
        if(!checkKing(listCopied)){
            return true;
        }
        else{
            return false;
        }
    }
    else{
        if((globalPlayer == 1 && pieceList[row2][column2] > 6) ||(globalPlayer == 2 && pieceList[row2][column2] < 7)){
            int[][] listCopied = listCopy(pieceList);
             updateList(num, listCopied, row, col, row2, column2);
             if(!checkKing(listCopied)){
            return true;
        }
        else{
            return false;
        }
        }
        
    }
    return false;
}
public boolean canPawnMove(int[][] pieceList){
    int pawn;
    int increment;
    if(globalPlayer == 1){
        pawn = 6;
        increment = 1;
    }
    else{
        pawn = 7;
        increment = -1;
    }
    for(int i= 0; i < 8; i++){
        for(int j=0; j < 8; j++){
            if(pieceList[i][j] == pawn){
                int row = i;
                int col = j;
                if(movementCheckPawn(i,j,i+increment,j,pieceList,pawn)){
                    return true;
                }
                col = j-1;
                if(col >=0){
                  if(movementCheckPawn(i,j,i+increment,col,pieceList,pawn)){
                    return true;
                }
                col = j+1;
                if(col < 8){
                   if(movementCheckPawn(i,j,i+increment,col,pieceList,pawn)){
                    return true;
                } 
                }
                }
            }
        }
    }
    return false;
}
public boolean canRookMove(int[][] pieceList, boolean queenUsing){
    int rook;
    if(globalPlayer == 1){
        if(queenUsing) rook = 5;
        else
        rook = 1;
    }
    else{
        if(queenUsing) rook = 12;
        else
        rook = 8;
    }
    for(int i= 0; i < 8; i++){
        for(int j=0; j < 8; j++){
            if(pieceList[i][j] == rook){
                int row = i;
                int col = j;
                int row2 = i+1;
                while(row2 < 8){
                    if(movementCheck(row,col,row2,col,pieceList,rook)){
                        return true;
                    }
                    if(pieceList[row2][col] != 0) break;
                    row2++;
                }
                row2 = i-1;
                while(row2>=0){
                   if(movementCheck(row,col,row2,col,pieceList,rook)){
                        return true;
                    }
                     if(pieceList[row2][col] != 0) break;
                    row2--; 
                }
                int col2 = j-1;
                while(col2 >= 0){
                   if(movementCheck(row,col,row,col2,pieceList,rook)){
                        return true;
                    }
                    if(pieceList[row][col2] != 0)break;
                    col2--; 
                }
                col2 = j+1;
                while(col2 < 8){
                    if(movementCheck(row,col,row,col2,pieceList,rook)){
                        return true;
                    }
                     if(pieceList[row][col2] != 0)break;
                    col2++; 
                }
                }
                }
            }
    return false;
}
public boolean canBishopMove(int[][] pieceList,boolean queenUsing){
    int bishop;
    if(globalPlayer == 1){
        if(queenUsing) bishop = 5;
        else
        bishop = 3;
    }
    else{
        if(queenUsing) bishop = 12;
        else
        bishop = 10;
    }
    for(int i=0; i < 8; i++){
        for(int j=0; j < 8; j++){
            if(pieceList[i][j] == bishop){
                int row = i;
                int col = j;
                int row2 = row+1;
                int col2 = col+1;
                while(row2 < 8 && col2 < 8){
                   
                    if(movementCheck(row,col,row2,col2,pieceList,bishop)){
                        return true;
                    }
                     if(pieceList[row2][col2] != 0) break;
                    row2++;
                    col2++;
                }
                row2 = row+1;
                col2 = col-1;
                while(row2 < 8 && col2 >=0){
                    if(movementCheck(row,col,row2,col2,pieceList,bishop)){
                        return true;
                    }
                    if(pieceList[row2][col2] != 0) break;
                    row2++;
                    col2--;
                }
                row2 = row-1;
                col2 = col+1;
                while(row2 >=0 && col2 < 8){
                    if(movementCheck(row,col,row2,col2,pieceList,bishop)){
                        return true;
                    }
                     if(pieceList[row2][col2] != 0) break;
                    row2--;
                    col2++;
                }
                row2 = row-1;
                col2 = col-1;
                while(row2>=0 && col2>=0){
                    if(movementCheck(row,col,row2,col2,pieceList,bishop)){
                        return true;
                    }
                    if(pieceList[row2][col2] != 0) break;
                    row2--;
                    col2--;
                }
            }
        }
    }
    return false;
}
public boolean knightMove(int[][] pieceList){
    int knight;
    if(globalPlayer == 1){
        knight = 2;
    }
    else{
        knight = 9;
    }
    for(int i = 0; i < 8; i++){
        for(int j=0; j < 8; j++){
            if(pieceList[i][j] == knight){
                int row = i;
                int col = j;
                if(row+1 < 8){
                if(col+2 < 8)    
                if(movementCheck(i,j,i+1,j+2,pieceList,knight)) return true;
                if(col-2 >=0)
                if(movementCheck(i,j,i+1,j-2,pieceList,knight)) return true;
            }
                if(row-1 >=0){
                if(col+2 < 8)    
                if(movementCheck(i,j,i-1,j+2,pieceList,knight)) return true;
                if(col-2 >= 0)
                if(movementCheck(i,j,i-1,j-2,pieceList,knight)) return true;
            }
                if(row+2 < 8){
                   if(col+1 < 8) 
                if(movementCheck(i,j,i+2,j+1,pieceList,knight)) return true;
                if(col-1 >=0)
                if(movementCheck(i,j,i+2,j-1,pieceList,knight)) return true;
            }
                if(row-2 >=0){
                if(col+1 < 8)    
                if(movementCheck(i,j,i-2,j+1,pieceList,knight)) return true;
                if(col-1 >= 0)
                if(movementCheck(i,j,i-2,j-1,pieceList,knight)) return true;
            }

            }
        }
    }
    return false;
}
public boolean canQueenMove(int[][] pieceList){
    if(canBishopMove(pieceList,true)) return true;
    if(canRookMove(pieceList,true)) return true;
    return false;
}

}









    

    
    