package UI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Window.*;

public class CenterPanel extends JPanel{
    private ArrayList<int[][]> map;
    int index;
    Show show;
    ButtonPanel Button;
    public CenterPanel(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        map=new ArrayList<>();
        index=0;
        if(map.isEmpty()){
            int[][] e={{1,2,3},{4,5,6},{7,8,0}};
            map.add(e);
        }
        setSize(200,300);
        setLayout(new BorderLayout());
        show=new Show();
        add(show,BorderLayout.CENTER);
        Button=new ButtonPanel();
        add(Button,"South");
    }
    void setMap(ArrayList<int[][]> res){
        index=0;
        map=res;
        if(map==null){
            map=new ArrayList<>();
            int[][] e={{1,2,3},{4,5,6},{7,8,0}};
            map.add(e);
        }
        show.setArr(map.get(index++));
        show.repaint();
    }
    public class Show extends JPanel{
        int[][] arr;
        public Show(){
            setLayout(new BorderLayout());
            arr=map.get(index);
        }
        void setArr(int[][] res){
            arr=res;
        }
        void Repaint(){
            repaint();
        }
        public void paintComponent(Graphics g) {
            PictruePanel image=new PictruePanel();
            image.drawimage(g,arr);
        }
    }
    public class PictruePanel extends JPanel{
        private final Image[] pictures;//存储图片
        int W=100;
        public PictruePanel() {
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
            for(int i=0;i<3*3;i++) {
                int x=i%3*w;
                int y=i/3*h;
                pictures[i]=bufferedImage.getSubimage(x,y,w,h);
            }
        }
        void drawimage(Graphics g,int[][] arr){
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
    }
    class ButtonPanel extends JPanel {
        JButton button1=new JButton("下一步");
        JButton button2=new JButton("上一步");
        public ButtonPanel(){
            add(button2);
            add(button1);
            button2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    if(index>1){
                        index--;
                        show.setArr(map.get(index-1));
                        show.Repaint();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"已到第一步");
                    }
                }
            });
            button1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    if(index<map.size()){
                        if(index==0) index++;
                        show.setArr(map.get(index++));
                        show.Repaint();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"已完成");
                    }
                }
            });
        }
    }
}
