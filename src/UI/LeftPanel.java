package UI;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class LeftPanel extends JPanel {
    String stepnum="最短路径长度";
    String Alogorithm_name="算法名";
    String information="输出信息";
    String runtime="运行时间";
    JLabel label0;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    public LeftPanel(){
        setSize(200,200);
        setLayout(new GridLayout(4,1));
        label0=new JLabel(stepnum,JLabel.LEFT);
        label0.setPreferredSize(new Dimension(200, 50));
        label0.setBorder(new LineBorder(Color.BLACK));
        add(label0);
        label1=new JLabel(Alogorithm_name,JLabel.LEFT);
        label1.setPreferredSize(new Dimension(200, 50));
        label1.setBorder(new LineBorder(Color.BLACK));
        add(label1);
        label2=new JLabel(information,JLabel.LEFT);
        label2.setPreferredSize(new Dimension(200, 50));
        label2.setBorder(new LineBorder(Color.BLACK));
        add(label2);
        label3=new JLabel(runtime,JLabel.LEFT);
        label3.setPreferredSize(new Dimension(200, 50));
        label3.setBorder(new LineBorder(Color.BLACK));
        add(label3);
    }
    void setString(String str0,String str1,String str2,String str3){
        stepnum=str0;
        Alogorithm_name=str1;
        information=str2;
        runtime=str3;
        label0.setText(stepnum);
        label1.setText(Alogorithm_name);
        label2.setText(information);
        label3.setText(runtime);
        repaint();
    }
}
