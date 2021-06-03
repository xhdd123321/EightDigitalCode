package UI;

import Main.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View {
    public static void main(String[] agrs) {
        MyFrame frame= new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//使界面可视
    }
}
class MyFrame extends JFrame {
    ArrayList<int[][]> map;
    public static RightPanel panel1;
    public static CenterPanel panel2;
    public static LeftPanel panel3;
    //构造函数
    public MyFrame() {
        map=new ArrayList<>();
        Main code_8=new Main();
        setTitle("8数码");
        setSize(600,360);
        panel1=new RightPanel();
        add(panel1,"East");
        panel2=new CenterPanel();
        add(panel2,"Center");
        panel3=new LeftPanel();
        panel1.Button.button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String flag=(String) panel1.ComboBox.sizeCombo.getSelectedItem();
                code_8.setFlag(flag);
                code_8.setStartMap(getstartMap());
                code_8.setEndMap(getendMap());
                setMap(code_8.test());
                panel2.setMap(map);
            }
        });
    }
    void setMap(ArrayList<int[][]> res){
        map=res;
    }
    int[][] getstartMap(){
        return panel1.getstartMap();
    }
    int[][] getendMap(){
        return panel1.getendMap();
    }
}