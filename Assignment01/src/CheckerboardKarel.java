import stanford.karel.Karel;

public class CheckerboardKarel extends Karel {

    public void run() {
        checkerBoardKarel();
    }
    void checkerBoardKarel(){
        putBeeper();
        goStraightStreet();
        turnOver();
    }
    // for front clear and put beepers
    void goStraightStreet(){
        while(frontIsClear()){
            move();
            if(frontIsClear()){
                moveAndPut();
                beeperAfterFrontBlock();
            }else{
               noBeeperAfterFrontBlock();
            }
        }
    }
    void beeperAfterFrontBlock(){
        if(frontIsBlocked()){
            turnLeft();
            //before turning west
            if(frontIsClear()){
                move();
                turnLeft();
            }
            //after turning west
            while(frontIsClear()){
                move();
                if(frontIsClear()){
                    putAndMove();
                }
            }
        }
    }
    void noBeeperAfterFrontBlock(){
        turnLeft();
        //before turning west
        if(frontIsClear()){
            moveAndPut();
            turnLeft();
        }
        //after turning west
        while(frontIsClear()){
            move();
            if(frontIsClear()){
                moveAndPut();
            }
        }
    }
    //Reach second revenue and checking whether right is clear or not
    void turnOver() {
        if (rightIsClear()) {
            rightGotClear();
        }else{
            rightGotBlock();
        }
    }
    //for other worlds on second street
    void rightGotClear(){
        turnRight();
        if (frontIsClear()) {
            move();
            turnRight();
            checkerBoardKarel();
        }
    }
    //for 1x8 world on first street
    void rightGotBlock(){
        if(rightIsBlocked()){
            turnAround();
            if(facingWest()){
                turnRight();
                move();
                move();
                checkerBoardKarel();
            }
        }
    }
    void moveAndPut(){
        move();
        putBeeper();
    }
    void putAndMove(){
        putBeeper();
        move();
    }
    void turnAround(){
        turnLeft();
        turnLeft();
    }
    void turnRight(){
        turnLeft();
        turnLeft();
        turnLeft();
    }
}
