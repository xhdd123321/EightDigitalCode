package UI;
import javax.swing.*;

public class View {
    public static void main(String[] args) {
        MyFrame frame= new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//使界面可视
    }
}
class MyFrame extends JFrame {
    int [][] cur={  {1,2,3},
            {4,5,6},
            {7,8,0}};
    public static RightPanel panel1;
    public static CenterPanel panel2;
    public static LeftPanel panel3;
    //构造函数
    public MyFrame() {
        setTitle("8数码");
        setSize(600,360);

        panel1=new RightPanel();
        add(panel1,"East");
        panel2=new CenterPanel(cur);
        add(panel2,"Center");
        panel3=new LeftPanel();
    }
}