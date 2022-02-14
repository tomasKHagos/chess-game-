import greenfoot.*;  

public class world2 extends World
{

    public  World returnWorld;
    private int player;
    private MouseInfo mouse;
    private int[][] piecesPosition;
    int row;
    int column;
    private int QueenNum;
    private int knightNum;
    private int bishopNum;
    private int RookNum;
    /**
     this world is used when a pawn changes to another piece and it goes back to the original screen after its changed
     */
    public world2( World returnWorld, int player, int[][] piecesPosition, int row, int column)
    {    
        super(400,104, 1);
        this.returnWorld = returnWorld;
        this.player = player;
        this.piecesPosition = piecesPosition;
        this.row = row;
        this.column = column;
        if(player == 1){
              setBackground(new GreenfootImage("pawnChange11.jpg"));
        }
        else{
              setBackground(new GreenfootImage("pawnChange22.jpg"));
        }
    }
    boolean changed = false;
    public void act(){
               if(Greenfoot.mouseClicked(null)){
                    mouse = Greenfoot.getMouseInfo();
                    if(mouse.getX() > 6 && mouse.getX() < 90){
                        Queen addQueen = new Queen(player,piecesPosition);
                        if(player == 1){
                            addQueen.setImage("whiteQueen.png");
                            QueenNum = 5;
                        }
                        else{
                            QueenNum = 12;
                        }
                        returnWorld.addObject(addQueen,row,column);
                        piecesPosition[row][column] = QueenNum;
                        changed = true;
                    }
                    else if(mouse.getX() > 118 && mouse.getX() < 192){
                        Bishop addbishop = new Bishop(player,piecesPosition);
                        if(player == 1){
                            bishopNum = 3;
                             addbishop.setImage("whiteBishop.png");
                        }
                        else{
                            bishopNum = 10;
                        }
                        returnWorld.addObject(addbishop,row,column);
                        piecesPosition[row][column] = bishopNum;
                        changed = true;
                        
                    }
                    else if(mouse.getX() > 222 && mouse.getX() < 285){
                        Rook addRook = new Rook(player,piecesPosition);
                        if(player == 1){
                            RookNum = 1;
                            addRook.setImage("whiteRook.png");
                        }
                        else{
                            RookNum = 8;
                        }
                        returnWorld.addObject(addRook,row,column);
                        piecesPosition[row][column] = RookNum;
                        changed = true;
                        }
                    else if(mouse.getX() > 318 && mouse.getX() < 392){
                        Knight addKnight = new Knight(player,piecesPosition);
                        if(player == 1){
                            knightNum = 2;
                             addKnight.setImage("whiteKnight.png");
                        }
                        else{
                            knightNum = 9;
                        }
                        returnWorld.addObject(addKnight,row,column);
                        piecesPosition[row][column] = knightNum;
                        changed = true;
                        
                    }
                }
              if(changed){
              Greenfoot.setWorld(returnWorld);
            }
        
    }
}
