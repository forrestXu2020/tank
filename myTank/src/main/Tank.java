package main;

import main.util.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Author:Administrator
 **/
public class Tank {
    public int x,y;
    private Dir dir;
    private boolean moving=true;
    private static final int SPEED=6;
    public int WIDTH,HEIGHT;
    private tankFrame tf;
    public Group group;
    public boolean live=true;
    public Rectangle rect=new Rectangle(x,y,WIDTH,HEIGHT);
    Random random =new Random();
    public int count=0;
    Boolean change=true;


    public Tank(int x, int y, Dir dir,tankFrame tf,Group g) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
        this.group=g;
        if(Group.My.equals(g)){
            this.WIDTH= ResourceMgr.tankD.getWidth();
            this.HEIGHT=ResourceMgr.tankD.getHeight();
        }else{
            this.WIDTH= ResourceMgr.DtankD.getWidth();
            this.HEIGHT=ResourceMgr.DtankD.getHeight();
        }
    }

    private void move(){
        if(!moving){
            return;
        }
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
        checkBound();
        rect.x=this.x;
        rect.y=this.y;
        rect.width=WIDTH;
        rect.height=HEIGHT;
    }

    private void checkBound() {
        if(x>tf.getWidth()-this.WIDTH)x=tf.getWidth()-this.WIDTH;
        if(x<=0)x=0;
        if(y>=tf.getHeight()-this.HEIGHT)y=tf.getHeight()-this.HEIGHT;
        if(y<10)y=10;
    }

    public void paint(Graphics g) {
        if(!this.live){
            tf.enermyList.remove(this);
        }
        if(count>20){
            change=!change;
            count=0;
        }
        count++;
        if(change){
            switch (dir){
                case LEFT:g.drawImage(group.equals(Group.My)?ResourceMgr.tankL:ResourceMgr.DtankL, x, y, null);break;
                case RIGHT:g.drawImage(group.equals(Group.My)?ResourceMgr.tankR:ResourceMgr.DtankR, x, y, null);break;
                case UP:g.drawImage(group.equals(Group.My)?ResourceMgr.tankU:ResourceMgr.DtankU,x,y,null);break;
                case DOWN:g.drawImage(group.equals(Group.My)?ResourceMgr.tankD:ResourceMgr.DtankD,x,y,null);break;
                case DownLeft:g.drawImage(group.equals(Group.My)?ResourceMgr.tankDL:ResourceMgr.DtankDL,x,y,null);break;
                case DownRight:g.drawImage(group.equals(Group.My)?ResourceMgr.tankDR:ResourceMgr.DtankDR,x,y,null);break;
                case UpLeft:g.drawImage(group.equals(Group.My)?ResourceMgr.tankUL:ResourceMgr.DtankUL,x,y,null);break;
                case UpRight:g.drawImage(group.equals(Group.My)?ResourceMgr.tankUR:ResourceMgr.DtankUR,x,y,null);break;
            }
        }else {
            switch (dir){
                case LEFT:g.drawImage(group.equals(Group.My)?ResourceMgr.tankL1:ResourceMgr.DtankL1, x, y, null);break;
                case RIGHT:g.drawImage(group.equals(Group.My)?ResourceMgr.tankR1:ResourceMgr.DtankR1, x, y, null);break;
                case UP:g.drawImage(group.equals(Group.My)?ResourceMgr.tankU1:ResourceMgr.DtankU1,x,y,null);break;
                case DOWN:g.drawImage(group.equals(Group.My)?ResourceMgr.tankD1:ResourceMgr.DtankD1,x,y,null);break;
                case DownLeft:g.drawImage(group.equals(Group.My)?ResourceMgr.tankDL1:ResourceMgr.DtankDL1,x,y,null);break;
                case DownRight:g.drawImage(group.equals(Group.My)?ResourceMgr.tankDR1:ResourceMgr.DtankDR1,x,y,null);break;
                case UpLeft:g.drawImage(group.equals(Group.My)?ResourceMgr.tankUL1:ResourceMgr.DtankUL1,x,y,null);break;
                case UpRight:g.drawImage(group.equals(Group.My)?ResourceMgr.tankUR1:ResourceMgr.DtankUR1,x,y,null);break;
            }
        }

        move();
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public void changeMoving() {
        if(random.nextInt(10)>5){
            this.moving=this.moving?false:true;
        }
    }

    public void fire() {
        //如果是自己，可以发出声音
        if(Group.My.equals(group)){
            Audio.makeAudio("tank_fire");
        }
        int bx=this.x+this.WIDTH/2-Bullet.WIDTH/2;
        int by=this.y+this.HEIGHT/2-Bullet.HEIGHT/2;
        tf.bList.add(new Bullet(bx,by,this.dir,tf,group));
    }

    public void changeDir() {
        this.dir=Dir.values()[random.nextInt(8)];
    }

    public void setMoving(boolean b) {
        this.moving=b;
    }
}
