import main.Audio;
import main.tankFrame;

import java.util.Random;

/**
 * @Author:Administrator
 **/
public class T {
    public static void main(String[] args) throws InterruptedException {
        tankFrame f=new tankFrame();
        new Thread(()->new Audio("main/Resource/audio/war1.wav").loop()).start();
        while(true){
            f.repaint();
            Thread.sleep(50);
        }
    }
}
