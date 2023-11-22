package com.example.oop_lab3.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.oop_lab3.GameDisplay;
import com.example.oop_lab3.GameLoop;
import com.example.oop_lab3.gamepanel.HealthBar;
import com.example.oop_lab3.gamepanel.Joystick;
import com.example.oop_lab3.R;
import com.example.oop_lab3.Utils;
import com.example.oop_lab3.graphics.Animator;
import com.example.oop_lab3.graphics.Sprite;

import java.util.Iterator;
import java.util.List;

/**
 * Player is the main character of the game, which the user can control with a touch joystick.
 * The player class is an extension of a Circle, which is an extension of a GameObject
 */
public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 5;
    private Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints = MAX_HEALTH_POINTS;
    private Animator animator;
    private PlayerState playerState;
    private double range = 100.0;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, Animator animator) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context, this);
        this.animator = animator;
        this.playerState = new PlayerState(this);
    }


    public void shoot(List<Bullet> bulletList, List<Enemy> enemies, int numberOfBullets, Context context){
        Iterator<Enemy> iter = enemies.iterator();
        double minDist = range;
        Circle closestEnemy = null;
        while (iter.hasNext()) {
            Circle enemy = iter.next();
            double dist = Utils.getDistanceBetweenPoints(positionX, positionY, enemy.positionX, enemy.positionY);
            if (dist <= minDist) {
                minDist = dist;
                closestEnemy = enemy;
            }
        }
        while (numberOfBullets > 0) {
            if(closestEnemy != null) {
                bulletList.add(new Bullet(context, positionX, positionY, closestEnemy.positionX/minDist, closestEnemy.positionY/minDist));
            }else{
                bulletList.add(new Bullet(context, positionX, positionY, 1, 1));
            }
            numberOfBullets--;
        }
    }
    public void update() {

        // Update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        // Update position
        positionX += velocityX;
        positionY += velocityY;

        // Update direction
        if (velocityX != 0 || velocityY != 0) {
            // Normalize velocity to get direction (unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }

        playerState.update();
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        animator.draw(canvas, gameDisplay, this);

        healthBar.draw(canvas, gameDisplay);
    }

    public int getHealthPoint() {
        return healthPoints;
    }

    public void setHealthPoint(int healthPoints) {
        // Only allow positive values
        if (healthPoints >= 0)
            this.healthPoints = healthPoints;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}