package main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author:Administrator
 **/
public class tankFrame extends Frame {
    List<Bullet> bList=new ArrayList<Bullet>();
    Tank myTank=new Tank(200,200,Dir.DOWN,this,Group.My);
    List<Tank> enermyList=new ArrayList<Tank>();
    List<Exploder> exploderList=new ArrayList<Exploder>();
    Random random=new Random();
    static final int GAME_WIDTH=1200,GAME_HEIGHT=800;
    public tankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setVisible(true);
        setTitle("tank war");
        setResizable(false);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        makeEnermy();
    }


    public void makeEnermy(){
        Dir dir=Dir.UP;
        int x=35;int y=455;
        for(int i=0;i<8;i++){
            enermyList.add(new Tank(x+i*80,y,dir,this,Group.Enermy));
        }
    }


    Image offScreenImage=null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            offScreenImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen=offScreenImage.getGraphics();
        Color c=gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.drawString("子弹数量："+bList.size(),5,40);
        g.drawString("敌方数量："+enermyList.size(),5,60);
        myTank.paint(g);
        for(int i=0;i<bList.size();i++){
            bList.get(i).paint(g);
        }
        for(int i=0;i<enermyList.size();i++){
            if(random.nextInt(10)>8){
                enermyList.get(i).changeMoving();
                enermyList.get(i).changeDir();
                enermyList.get(i).fire();
            }
            enermyList.get(i).paint(g);
        }
        for(int i=0;i<exploderList.size();i++){
            exploderList.get(i).paint(g);
        }
        //碰撞检测
        for(Bullet b: bList){
            for(Tank t :enermyList){
                if(checkFace(b,t)){
                    t.live=false;
                    b.live=false;
                    exploderList.add(new Exploder(t.x,t.y,this));
                    new Thread(()->{
                        new Audio("main/Resource/audio/explode.wav").play();
                    }).start();
                }
            }
            //检测自己
//            if(checkFace(b,myTank)) {
//                System.exit(0);
//            }
        }
    }

    private boolean checkFace(Bullet b, Tank t) {
        if(b.group.equals(t.group)) return false;
        return b.rect.intersects(t.rect);
    }

    class MyKeyListener extends KeyAdapter{
        boolean bl=false,br=false,bu=false,bd=false;
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    bl=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br=true;
                    break;
                case KeyEvent.VK_UP:
                    bu=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd=true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:bl=false;break;
                case KeyEvent.VK_RIGHT:br=false;break;
                case KeyEvent.VK_UP:bu=false;break;
                case KeyEvent.VK_DOWN:bd=false;break;
                default:break;
            }
            setMainTankDir();
        }

        private void setMainTankDir( ){
            Dir dir=null;
            if(bu&&br){
                dir=Dir.UpRight;
            }else if(bu&&bl){
                dir=Dir.UpLeft;
            }else if(bd&&bl){
                dir=Dir.DownLeft;
            }else if(bd&&br){
                dir=Dir.DownRight;
            }else if(bl){
                dir=Dir.LEFT;
            }else if(br){
                dir=Dir.RIGHT;
            }else if(bu){
                dir=Dir.UP;
            }else if(bd){
                dir=Dir.DOWN;
            }

            if(dir==null){
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                myTank.setDir(dir);
            }
        }
    }
}
