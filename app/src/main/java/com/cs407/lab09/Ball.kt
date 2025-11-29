package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        // prev acc and vel
        val ax0 = accX
        val ay0 = accY
        val vx0 = velocityX
        val vy0 = velocityY

        // new acc and vel
        val ax1 = xAcc
        val ay1 = yAcc
        val vx1 = vx0 + .5f * (ax0 + ax1) * dT
        val vy1 = vy0 + .5f * (ay0 + ay1) * dT


        // delta y delta x, distance traveled
        val dx = ( vx0 * dT ) + ( 1f/6f * dT*dT ) * ( 3*ax0 + ax1 )
        val dy = ( vy0 * dT ) + ( 1f/6f * dT*dT ) * ( 3*ay0 + ay1 )


        // apply new values to create movement
        velocityX = vx1
        velocityY = vy1
        accX = ax1
        accY = ay1
        posX += dx
        posY += dy

        // ensure movement doesn't exceed boundaries
        checkBoundaries()

    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // : implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)

        // if ball goes to left of screen
        if (posX  < 0 ){

            if (velocityX < 0) velocityX= 0f
            accX = 0f
            posX = 0f

        }

        // if ball goes to right of screen
        if (posX + ballSize > backgroundWidth){
            velocityX = 0f
            accX = 0f
            posX = backgroundWidth - ballSize
        }

        // if ball goes to top of screen
        if (posY  < 0 ){
            velocityY = 0f
            accY = 0f
            posY = 0f
        }

        // if ball goes to bottom of screen
        if ( posY + ballSize > backgroundHeight){
            velocityY = 0f
            accY = 0f
            posY = backgroundHeight - ballSize
        }


    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // : implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)

        posX = (backgroundWidth - ballSize) / 2
        posY = (backgroundHeight - ballSize)/ 2
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true

    }
}