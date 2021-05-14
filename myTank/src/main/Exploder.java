package main;

import main.util.ResourceMgr;

import java.awt.*;

/**
 * @Author:Administrator
 **/
public class Exploder {
        public int x,y;
        public int WIDTH= ResourceMgr.explodes.get(0).getWidth(),HEIGHT=ResourceMgr.explodes.get(0).getHeight();
        private tankFrame tf;
        public boolean live=true;
        private int count;
        public Exploder(int x, int y,tankFrame tf) {
            super();
            this.x = x;
            this.y = y;
            this.tf=tf;
        }

        public void paint(Graphics g) {
            if(count>15){
                count=0;
                tf.exploderList.remove(this);
            }
            g.drawImage(ResourceMgr.explodes.get(count),x,y,null);
            count++;
        }




}
