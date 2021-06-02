package UI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CenterPanel extends JPanel{
    private final int[][] arr;
    pictruePanel picture;
    public CenterPanel(int [][] cur){
        arr=cur;
        setSize(300,300);
        setLayout(new BorderLayout());
        picture=new pictruePanel();
        add(picture,"Center");
    }
    public class pictruePanel extends JPanel{
        private final Image[] pictures;//存储图片
        int W=100;
        public pictruePanel() {
            BufferedImage bufferedImage=null;
            int width=0,height=0;
            try {
                bufferedImage=ImageIO.read(new File("src/picture/0.png"));
                width = bufferedImage.getWidth();
                height = bufferedImage.getHeight();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            pictures=new Image[3*3];
            int w=width/3,h=height/3;//arr中存储的图片的大小
            //裁剪小图
            for(int i=0;i<3*3-1;i++) {
                int x=i%3*w;
                int y=i/3*h;
                pictures[i]=bufferedImage.getSubimage(x,y,w,h);
            }
        }
        void drawimage(Graphics g){
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    if(arr[i][j]==0){
                        g.drawImage(pictures[8], j*W, i*W, W, W,this);
                    }
                    else{
                        g.drawImage(pictures[arr[i][j]-1], j*W, i*W, W, W,this);//按顺序显示
                    }
                }
            }
        }
        public void paintComponent(Graphics g) {
            drawimage(g);
        }
    }
}
