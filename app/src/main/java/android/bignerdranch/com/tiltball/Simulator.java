package android.bignerdranch.com.tiltball;


import android.graphics.PointF;

/**
 * This models the simulation of the ball.
 * @author Bill Hoff
 */
public class Simulator {
    /**
     * Width and height of screen.
     */
    private int mWidth, mHeight;

    /**
     * Ball position (in pixels).
     */
    private PointF mBallPos;

    /**
     * Ball radius (in pixels).
     */
    private int mBallRadius;

    /**
     * Ball velocity.
     */
    private PointF mBallVel;

    /**
     * Ball Acceleration
     */
    private PointF mBallAccel;

    /**
     * System Time
     */
    private long mTime;
    /**
     * Device acceleration (X,Y only).
     */
    private PointF mDeviceAccel;

    // Constructor for Simulator.
    public Simulator(int width, int height)   {
        mWidth = width;
        mHeight = height;

        // Create variables for ball position and velocity.
        mBallVel = new PointF(0, 0);
        mBallPos = new PointF(50, 50);

        // Initialize ball radius to something reasonable.
        mBallRadius = 10;

        // Create variable for device acceleration (X,Y only).
        mDeviceAccel = new PointF(0, 0);

        //Create variable for ball
        mBallAccel = new PointF(0,0);
    }


    public void runSimulation() {
        mBallAccel.x = -mDeviceAccel.x/1000;
        mBallAccel.y = mDeviceAccel.y/1000;
        //Time for equations and previous mTime
        long dt = System.currentTimeMillis() - mTime;
        mTime = System.currentTimeMillis();

        //Physics
        mBallVel.x +=  mBallAccel.x*dt;
        mBallVel.y +=  mBallAccel.y*dt;

        mBallPos.x += mBallVel.x*dt + 0.5*mBallAccel.x*dt*dt;
        mBallPos.y += mBallVel.y*dt + 0.5*mBallAccel.y*dt*dt;






        // If ball goes off screen, reposition to opposite side of screen
//        if (mBallPos.x > mWidth) mBallPos.x=0;
//        if (mBallPos.y > mHeight) mBallPos.y=0;
//        if (mBallPos.x < 0) mBallPos.x=mWidth;
//        if (mBallPos.y < 0) mBallPos.y=mHeight;
        //If ball hits screen edge reverse it's velocity
        if (mBallPos.x > mWidth){
            mBallPos.x=mWidth;
            mBallVel.x *= -1.0;
        }
        if (mBallPos.y > mHeight){
            mBallPos.y=mHeight;
            mBallVel.y *= -1.0;
        }
        if (mBallPos.x < 0){
            mBallPos.x=0;
            mBallVel.x *= -1.0;
        }
        if (mBallPos.y < 0){
            mBallPos.y=0;
            mBallVel.y *= -1.0;
        }
    }



    // Getters and setters.

    public PointF getBallPos() {
        return mBallPos;
    }

    public void setBallPos(PointF ballPos) {
        mBallPos = ballPos;
    }

    public PointF getBallVel() {
        return mBallVel;
    }

    public void setBallVel(PointF ballVel) {
        mBallVel = ballVel;
    }

    public int getBallRadius() {
        return mBallRadius;
    }

    public void setBallRadius(int ballRadius) {
        mBallRadius = ballRadius;
    }

    public PointF getDeviceAccel() {
        return mDeviceAccel;
    }

    public void setDeviceAccel(PointF deviceAccel) {
        mDeviceAccel = deviceAccel;
    }
}

