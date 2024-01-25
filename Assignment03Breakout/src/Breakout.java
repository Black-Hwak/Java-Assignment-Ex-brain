import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {

    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT =600;
    final double PDL_WIDTH = 60;       // paddle width
    final double PDL_HEIGHT = 10;      // paddle height
    final double PDL_Y_OFFSET = 30;    // distance between bottom wall and bottom of baddle
    private final int BALL_RADIUS = 16;     // size of ball will be (2 * BALL_RADIUS), you can change this value according to your taste
    final int BRICKS_PER_ROW = 10;
    final int BRICK_ROWS = 10;
    final int BRICK_GAP = 4;        // horizontal and vertical gap between bricks rows and brick columns
    final int BRICK_WIDTH =
            (APPLICATION_WIDTH - (BRICKS_PER_ROW - 1) * BRICK_GAP) / BRICKS_PER_ROW;

    final int LEFT_MARGIN = (APPLICATION_WIDTH - (BRICK_WIDTH * BRICKS_PER_ROW + BRICK_GAP * (BRICKS_PER_ROW - 1))) / 2;
    final int BRICK_HEIGHT = 8;
    final int BRICK_Y_OFFSET = 70;  // distance from y = 0 (above wall) to top of the first row of bricks
    int TURNS = 3;            // Number of turns for one game
    private GOval ball = null;
    private GLabel label = null;
    private GLabel heartLabel = null;
    private GRect paddle = null;
    private int remainingBricks = 100;    // to store remaining brick count
    private double vx = 0;          // horizontal velocity
    private double vy = 0;          // vertical velocity

    String hearts = "Your Heart : ";
    String remaining = "Remaining : ";
    String clickStart = "Click To Start";
    String loseButChance = "You Lose! Click to Play Again";
    String gameOver = "Game Over! You Ruined All Hearts. Click to Restart";
    String winStatement = "Congrats! You WIN! Click to Play Again";
    boolean playing = true;
    boolean moving = true;

    public void run() {
        setGLabel(clickStart);
        waitForClick();
        playingGame();

    }

    // Prepare For Game
    void setGLabel(String str){
        label = new GLabel(str, 0, 0);
        label.setFont("Georgia-Italic-20");
        label.setColor(Color.RED);
        // to get label width
        double x = (getWidth() - label.getWidth()) / 2;
        label.setLocation(x, (double) getHeight() / 2);
        add(label);
    }

    void playingGame(){
        removeAll();
        for (int i = 0; i < 3; i++) {
            if(TURNS != 0){
               playUntilFalse();
                if(!playing){
                    haveChanceAndOver();
                }
                playing = true;
                moving = true;
            }
        }

    }

    void setLives(int turns){
        heartLabel = new GLabel(hearts + turns, 20, 20);
        heartLabel.setFont("Georgia-15");
        heartLabel.setColor(Color.BLACK);
        add(heartLabel);
    }

    void initVelocity() {
        RandomGenerator randomGen = RandomGenerator.getInstance();
        vy = 3;
        vx = randomGen.nextDouble(1.0, 3.0);
        if (randomGen.nextBoolean(0.5)) vx = -vx;

    }

    // Under Playing Scope
    void playUntilFalse(){
        addMouseListeners();
        setLives(TURNS);
        remainingBricksFun(remainingBricks);
        initVelocity();
        setBricks();
        setupBall();
        setupPaddle();
        moveBall();
    }

    void haveChanceAndOver(){
        remainingBricks = 100;
        TURNS--;
        if (TURNS == 0) {
            setGLabel(gameOver);
            waitForClick();
            TURNS = 3;
            playing = true;
            moving = true;
            playingGame();

        } else {
            setGLabel(loseButChance);
            waitForClick();
        }
        removeAll();
    }


    // Bricks
    void setBricks(){
        int y = BRICK_Y_OFFSET;
        for (int i = 1; i <= BRICK_ROWS; i++) {
            int x = LEFT_MARGIN;
            for (int j = 0; j < BRICKS_PER_ROW; j++) {
                GRect rect = new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
                if(i == 1 || i == 2){
                    rect.setFillColor(Color.RED);
                }else if(i == 3 || i == 4){
                    rect.setFillColor(Color.ORANGE);
                }else if(i == 5 || i == 6){
                    rect.setFillColor(Color.YELLOW);
                }else if(i == 7 || i == 8){
                    rect.setFillColor(Color.GREEN);
                }else{
                    rect.setFillColor(Color.CYAN);
                }
                rect.setFilled(true);
                add(rect);
                x += BRICK_WIDTH + BRICK_GAP;
            }
            y += BRICK_HEIGHT + BRICK_GAP;
        }
    }

    void remainingBricksFun(int remainingBricks){
        label = new GLabel( remaining + remainingBricks, 370, 20);
        label.setFont("Georgia-15");
        label.setColor(Color.BLACK);
        add(label);
    }


    // Ball
    void setupBall() {
        int x = (getWidth() - BALL_RADIUS) / 2;
        int y = (getHeight() - BALL_RADIUS) / 2;
        ball = new GOval(x, y, BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        add(ball);
    }
    void moveBall() {
        final int delay = 13;
        while (moving) {
            ball.move(vx, vy);
            checkBallUnderPaddle();
            handlePaddleCollision();
            handleBrickCollision();
            bounceBack();

            pause(delay);
        }
    }

    void checkBallUnderPaddle(){
        if(ball.getY() >= paddle.getY()){
            moving = false;
            playing =false;
            removeAll();
        }
    }
    void bounceBack(){
        if (ball.getY() + BALL_RADIUS >= getHeight() || ball.getY() <= 0) {
            vy = -vy;
        }

        if (ball.getX() + BALL_RADIUS >= getWidth() || ball.getX() <= 0) {
            vx = -vx;
        }
    }


    // Paddle
    void setupPaddle() {
        double x = (double) getWidth() / 2 - PDL_WIDTH / 2;
        double y = getHeight() - PDL_Y_OFFSET - PDL_HEIGHT;
        paddle = new GRect(x, y, PDL_WIDTH, PDL_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
    }

    public void mouseMoved(MouseEvent e) {
        int eX = e.getX();
        double rightEnd = paddle.getX()+PDL_WIDTH;
        double leftEnd = paddle.getX();
        int dx = 1;
        while ((paddle.getX() + PDL_WIDTH / 2) >= eX && (leftEnd != 0)) {
            paddle.move(-dx, 0);
            leftEnd--;
        }
        while ((paddle.getX() + PDL_WIDTH / 2) <= eX && (rightEnd!=getWidth())) {
            paddle.move(dx, 0);
            rightEnd++;
        }
    }


    // Collision
    GObject getCollidingObj() {
        double x = ball.getX();
        double y = ball.getY();

        double circleTopX = x + BALL_RADIUS;
        double circleTopY = y - 1;

        double circleBotX = x + BALL_RADIUS;
        double circleBotY = y + 2 * BALL_RADIUS + 1;

        double circleLftX = x - 1;
        double circleLftY = y + BALL_RADIUS;

        double circleRgtX = x + 2 * BALL_RADIUS + 1;
        double circleRgtY = y + BALL_RADIUS;

        GObject obj = getElementAt(circleTopX, circleTopY);
        if (obj != null) return obj;

        obj = getElementAt(circleBotX, circleBotY);
        if (obj != null) return obj;

        obj = getElementAt(circleLftX, circleLftY);
        if (obj != null) return obj;

        obj = getElementAt(circleRgtX, circleRgtY);
        return obj;

    }
    void handlePaddleCollision() {
        if (getCollidingObj() == paddle) {
            vy = -vy;
            if(vy == 3){
                vy = -vy;
                System.out.println("hit");
            }
        }
    }
    void handleBrickCollision(){
        if (getCollidingObj() != null && getCollidingObj() != paddle && getCollidingObj() != label && getCollidingObj() != heartLabel){
            remainingBricks--;
            if(remainingBricks == 0){
                winCondition();
            }
            vy = -vy;
            remove(getCollidingObj());
            // updating remaining
            remove(getElementAt(370,20));
            remainingBricksFun(remainingBricks);
        }
    }

    // Winning
    void winCondition(){
        removeAll();
        playing = false;
        setGLabel(winStatement);
        waitForClick();
        TURNS = 3;
        remainingBricks = 100;
        playing = true;
        moving = true;
        playingGame();

    }

}
