import greenfoot.*; 
public class MyWorld extends World
{
    //white pieces 
    private Rook whiteRook;
    private Rook whiteRook2;
    private Knight whiteKnight1, whiteKnight2;
    private King whiteKing;
    private Queen whiteQueen;
    private Bishop whiteBishop1, whiteBishop2;
    //black pieces
    private Rook blackRook1, blackRook2;
    private Knight blackKnight1, blackKnight2;
    private King blackKing;
    private Queen blackQueen;
    private Bishop blackBishop1, blackBishop2;
    private Pawn pawnwhite;
    public static boolean checkmated = false;
    private Pointer point;

    //this is the starting position 
    int[][] piecesPosition = {{1,2,3,4,5,3,2,1},{6,6,6,6,6,6,6,6},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {7,7,7,7,7,7,7,7},{8,9,10,11,12,10,9,8}};
    public MyWorld()
    {   
        super(750, 543, 1); 
        setBackground(new GreenfootImage("woodenboardwithsides.jpg"));
        //white pieces 
        whiteRook = new Rook(1,piecesPosition);
        whiteRook2 = new Rook(1,piecesPosition);
        whiteRook.setImage("whiteRook.png");
        whiteRook2.setImage("whiteRook.png");
        addObject(whiteRook, 0,0);
        addObject(whiteRook2,0,7);
        whiteKnight1 = new Knight(1,piecesPosition);
        whiteKnight2 = new Knight(1,piecesPosition);
        whiteKnight1.setImage("whiteKnight.png");
        whiteKnight2.setImage("whiteKnight.png");
        addObject(whiteKnight1,0,6);
        addObject(whiteKnight2, 0,1);
        whiteKing = new King(1,piecesPosition);
        whiteKing.setImage("whiteKing.png");
        addObject(whiteKing,0,3);
        whiteQueen = new Queen(1,piecesPosition);
        whiteQueen.setImage("whiteQueen.png");
        addObject(whiteQueen,0,4);
        whiteBishop1 = new Bishop(1,piecesPosition);
        whiteBishop2 = new Bishop(1,piecesPosition);
        whiteBishop1.setImage("whiteBishop.png");
        whiteBishop2.setImage("whiteBishop.png");
        addObject(whiteBishop1,0,2);
        addObject(whiteBishop2,0,5);
        
       for(int i = 0; i < 8; i++){
           Pawn pawnwhite = new Pawn(1,piecesPosition);
           pawnwhite.setImage("whitePawn.png");
           this.addObject(pawnwhite,1,i);
        } 
        
        //black pieces 
        blackRook1 = new Rook(2, piecesPosition);
        blackRook2 = new Rook(2, piecesPosition);
        this.addObject(blackRook1,7,7);
        this.addObject(blackRook2,7,0);
        //knights 
        blackKnight1 = new Knight(2, piecesPosition);
        blackKnight2 = new Knight(2, piecesPosition);
        this.addObject(blackKnight1,7,1);
        this.addObject(blackKnight2,7,6);
        //black bishops
        blackBishop1 = new Bishop(2, piecesPosition);
        blackBishop2 = new Bishop(2, piecesPosition);
        this.addObject(blackBishop1, 7, 5);
        this.addObject(blackBishop2, 7,2);
        
        //queen
        blackQueen = new Queen(2, piecesPosition);
        this.addObject(blackQueen, 7,4);
        //black king
        blackKing = new King(2, piecesPosition);
        this.addObject(blackKing, 7,3);
        point = new Pointer();
        addObject(point,1,9);
        
        //for loop for the bishops
        for(int i = 0; i < 8; i++){
           Pawn pawnBlack = new Pawn(2,piecesPosition);
           this.addObject(pawnBlack,6,i);
        }
        }
    public void started(){
    Piece.globalPlayer = 1;
}    
    public void act(){
     showText("Player"+Piece.globalPlayer, 650, 30);
    
     showText("=empty space", 685,124);
}
 //overloading the addobject method
 //so that I can put rows and columns when setting locations
 public void addObject(Actor piece,int row,int column){
     super.addObject(piece,(33+column*59)+30, (35+row*59)+30);
 }
 public void stopped(){
        int player;
        if(checkmated){
        showText("Game Over by CheckMate", 620,200);
        if(Piece.globalPlayer == 1){
            player = 2;
        }
        else{
            player = 1;
        }
        showText("Player"+ player +" Won", 620, 250);
       
    }
}
    
 
 
}



        
    

