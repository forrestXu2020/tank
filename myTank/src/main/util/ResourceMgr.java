package main.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Administrator
 **/
public class ResourceMgr {
    public static BufferedImage tankL,tankU,tankR,tankD,tankUL,tankUR,tankDL,tankDR,
            tankL1,tankU1,tankR1,tankD1,tankUL1,tankUR1,tankDL1,tankDR1,
            DtankL,DtankU,DtankR,DtankD,DtankUL,DtankUR,DtankDL,DtankDR,
            DtankL1,DtankU1,DtankR1,DtankD1,DtankUL1,DtankUR1,DtankDL1,DtankDR1,
    bulletL,bulletR,bulletU,bulletD,bulletUL,bulletUR,bulletDL,bulletDR;
    public static List<BufferedImage> explodes=new ArrayList<BufferedImage>();
    static{
        try {
            tankU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("main/Resource/images/GoodTank1.png"));
            tankR= ImageUtil.rotateImage(tankU,90);
            tankL= ImageUtil.rotateImage(tankU,-90);
            tankD= ImageUtil.rotateImage(tankU,180);
            tankUR= ImageUtil.rotateImage(tankU,45);
            tankUL= ImageUtil.rotateImage(tankU,-45);
            tankDR= ImageUtil.rotateImage(tankU,135);
            tankDL= ImageUtil.rotateImage(tankU,-135);

            tankU1= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("main/Resource/images/GoodTank2.png"));
            tankR1= ImageUtil.rotateImage(tankU,90);
            tankL1= ImageUtil.rotateImage(tankU,-90);
            tankD1= ImageUtil.rotateImage(tankU,180);
            tankUR1= ImageUtil.rotateImage(tankU,45);
            tankUL1= ImageUtil.rotateImage(tankU,-45);
            tankDR1= ImageUtil.rotateImage(tankU,135);
            tankDL1= ImageUtil.rotateImage(tankU,-135);


            DtankU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("main/Resource/images/BadTank1.png"));
            DtankL= ImageUtil.rotateImage(DtankU,-90);
            DtankR= ImageUtil.rotateImage(DtankU,90);
            DtankD= ImageUtil.rotateImage(DtankU,180);
            DtankUR= ImageUtil.rotateImage(DtankU,45);
            DtankUL= ImageUtil.rotateImage(DtankU,-45);
            DtankDR= ImageUtil.rotateImage(DtankU,135);
            DtankDL= ImageUtil.rotateImage(DtankU,-135);

            DtankU1= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("main/Resource/images/BadTank2.png"));
            DtankL1= ImageUtil.rotateImage(DtankU,-90);
            DtankR1= ImageUtil.rotateImage(DtankU,90);
            DtankD1= ImageUtil.rotateImage(DtankU,180);
            DtankUR1= ImageUtil.rotateImage(DtankU,45);
            DtankUL1= ImageUtil.rotateImage(DtankU,-45);
            DtankDR1= ImageUtil.rotateImage(DtankU,135);
            DtankDL1= ImageUtil.rotateImage(DtankU,-135);

            bulletU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("main/Resource/images/bulletU.png"));
            bulletR= ImageUtil.rotateImage(bulletU,90);
            bulletL= ImageUtil.rotateImage(bulletU,-90);
            bulletD= ImageUtil.rotateImage(bulletU,180);
            bulletUR= ImageUtil.rotateImage(bulletU,45);
            bulletUL= ImageUtil.rotateImage(bulletU,-45);
            bulletDR= ImageUtil.rotateImage(bulletU,135);
            bulletDL= ImageUtil.rotateImage(bulletU,-135);

            for(int i=1;i<=16;i++){
                explodes.add(ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("main/Resource/images/e"+i+".gif")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
