package Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public MainFrame()
    {
        setTitle("八数码算法");    //设置显示窗口标题
        setSize(1280,720);    //设置窗口显示尺寸
        this.setLocationRelativeTo(null);//窗体居中显示
        setLayout(new BorderLayout());    //为Frame窗口设置布局为BorderLayout
        JPanel topPanel = new JPanel();
        JButton button1=new JButton ("上");
        JButton button2=new JButton("左");
        JButton button3=new JButton("中");
        JButton button4=new JButton("右");


        add(button1,BorderLayout.NORTH);
        add(button3,BorderLayout.CENTER);
        add(button2,BorderLayout.WEST);
        add(button4,BorderLayout.EAST);


        setVisible(true);    //设置窗口是否可见
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置用户在此窗体上发起 "close" 时默认执行的操作
    }
}
