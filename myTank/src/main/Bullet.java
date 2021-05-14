package main;

import main.util.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author:Administrator
 **/
public class Bullet {
    private static final int SPEED=12;
    public int x,y;
    private Dir dir;
    public static int WIDTH= ResourceMgr.bulletD.getWidth(),HEIGHT=ResourceMgr.bulletD.getHeight();
    public Group group;
    private tankFrame tf;
    public boolean live=true;
    public Rectangle rect=new Rectangle(x,y,WIDTH,HEIGHT);

    public Bullet(int x, int y, Dir dir,tankFrame tf,Group g) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
        this.group=g;
    }

    private void move(){
        switch(dir){
            case LEFT:x-=SPEED;break;
            case RIGHT:x+=SPEED;break;
            case UP:y-=SPEED;break;
            case DOWN:y+=SPEED;break;
            case UpLeft:y-=SPEED;x-=SPEED;  break;
            case UpRight:y-=SPEED;x+=SPEED;break;
            case DownLeft:y+=SPEED;x-=SPEED;break;
            case DownRight:y+=SPEED;x+=SPEED;break;
            default:break;
        }
        rect.x=this.x;
        rect.y=this.y;
        rect.width=WIDTH;
        rect.height=HEIGHT;
        if(x<0||y<0||x>tankFrame.GAME_WIDTH||y>tankFrame.GAME_HEIGHT){
            live=false;
        }
    }

    public void paint(Graphics g) {
        if(!live){
            tf.bList.remove(this);
            return;
        }
        switch (dir){
            case LEFT:g.drawImage(ResourceMgr.bulletL, x, y, null);break;
            case RIGHT:g.drawImage(ResourceMgr. bulletR, x, y, null);break;
            case UP:g.drawImage( ResourceMgr. bulletU,x,y,null);break;
            case DOWN:g.drawImage( ResourceMgr. bulletD,x,y,null);break;
            case DownLeft:g.drawImage( ResourceMgr. bulletDL,x,y,null);break;
            case DownRight:g.drawImage( ResourceMgr. bulletDR,x,y,null);break;
            case UpLeft:g.drawImage( ResourceMgr. bulletUL,x,y,null);break;
            case UpRight:g.drawImage( ResourceMgr. bulletUR,x,y,null);break;
        }
        move();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}
