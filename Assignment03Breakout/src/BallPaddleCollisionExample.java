import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.event.MouseEvent;

public class BallPaddleCollisionExample extends GraphicsProgram {

        public static final int APPLICATION_WIDTH = 400;
        public static final int APPLICATION_HEIGHT = 600;

        final int PDL_WIDTH = 60;
        final int PDL_HEIGHT = 10;
        final int PDL_Y_OFFSET = 30;

        private final int BALL_RADIUS = 10;

        private GOval ball = null;
        private GRect paddle = null;

        private double vx = 0;
        private double vy = 0;

        public void run() {
                addMouseListeners();
                setupPaddle();
                setupBall();
                initVelocity();
                moveBall();
        }

        void setupBall() {
                ball = new GOval(200, 300, BALL_RADIUS * 2, BALL_RADIUS * 2);
                ball.setFilled(true);
                add(ball);

        }

        void moveBall() {
                int delay = 15;
                while (true) {
                        ball.move(vx, vy);
                        if ((ball.getY() + BALL_RADIUS * 2) >= getHeight() || ball.getY() <= 0) {
                                vy = -vy;
                        }

                        if ((ball.getX() + BALL_RADIUS * 2) >= getWidth() || ball.getX() <= 0) {
                                vx = -vx;
                        }
                        handlePaddleCollision();
                        pause(delay);
                }
        }

        void handlePaddleCollision() {
                if (getCollidingObj() == paddle) {
                        vy = -vy;
                }
        }


        // !!! this is just an illustration of how ball and paddle collision
        // can be detected. Breakout game လို အပ်ချက်အတွက် ကိုယ့်နည်းကိုယ့်ဟန်နဲ့
        // စဉ်းစားပါ
        GObject getCollidingObj() {
                double x = ball.getX();
                double y = ball.getY();

//                double blx = x;
//                double bly = y + 2 * BALL_RADIUS;
//
//                double brx = x + 2 * BALL_RADIUS;
//                double bry = y + 2 * BALL_RADIUS;

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
                if (obj != null) return obj;

                return null;

        }


        public void mouseMoved(MouseEvent e) {
                double eX = e.getX();
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

        void initVelocity() {
                RandomGenerator rgen = RandomGenerator.getInstance();
                vy = 3;
                vx = rgen.nextDouble(1.0, 3.0);
                if (rgen.nextBoolean(0.5)) vx = -vx;
        }


        void setupPaddle() {
                double x = getWidth() / 2 - PDL_WIDTH / 2;
                double y = getHeight() - PDL_Y_OFFSET - PDL_HEIGHT;
                paddle = new GRect(x, y, PDL_WIDTH, PDL_HEIGHT);
                paddle.setFilled(true);
                add(paddle);
        }
}
