package com.example.lab3.logic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.lab3.entities.Player;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.inventory.Shop;
import com.example.lab3.mapping.MapHolder;

import java.security.SecureRandom;

public class Game extends SurfaceView implements SurfaceHolder.Callback{

    public static final SecureRandom rnd = new SecureRandom();
    private MainLoop loop;

    private GameDisplay gameDisplay;
    private Player player;
    private MapHolder mapHolder;
    private EntityFactory entityFactory;
    private Context context;
    private SurfaceHolder surfaceHolder;
    private Shop shop;

    //private final int displayX;
    //private final int displayY;
    private static boolean toUpdate = true;

    public Game(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;
        initGame();
        setFocusable(true);
    }


   /* @Override
    public boolean onTouchEvent(MotionEvent event) {

    }*/

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        loop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        // Draw Tilemap
        mapHolder.draw(canvas, gameDisplay);
        player.draw(canvas, gameDisplay);
        entityFactory.draw(canvas);
    }

    public void update(){
        player.update();
        if(toUpdate) {
            shop.update();
            entityFactory.update();
            player.updateHealth();
            toUpdate = false;
            if(player.isDead()){
                //loop.stopLoop();
                initGame();
            }
        }
        gameDisplay.update();
    }

    private void initGame(){
        /*if(loop != null && loop.isAlive()){
            loop.stopLoop();
        }*/
        mapHolder = new MapHolder(context);
        mapHolder.generateMapPlan();
        int startX = Game.rnd.nextInt(mapHolder.HEIGHT);
        int startY = Game.rnd.nextInt(mapHolder.WIDTH);
        while (!mapHolder.inBounds(startX, startY) || !(mapHolder.tileIsPassable(startX, startY))){
            startX = Game.rnd.nextInt(mapHolder.HEIGHT);
            startY = Game.rnd.nextInt(mapHolder.WIDTH);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        player = new Player(context, mapHolder, startX, startY, 10);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);
        entityFactory = new EntityFactory(context, mapHolder, player, gameDisplay);
        this.shop = new Shop(this);
        loop = new MainLoop(this, surfaceHolder);
    }

    public static boolean isToUpdate(){
        return toUpdate;
    }

    public static void sendUpdateRequest(){
        toUpdate = true;
    }

    public Player getPlayer() {
        return player;
    }

    public Shop getShop(){return shop;}
}
