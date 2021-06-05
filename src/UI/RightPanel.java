package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RightPanel extends JPanel {
    public final static int N = 3;
    int[][] startMap;
    int[][] endMap;
    Inputtxt Ts;
    Inputtxt Te;
    ButtonPanel Button;
    ComboBoxPanel ComboBox;

    public RightPanel(){
        startMap=new int[3][3];
        endMap=new int[3][3];
        String[][] smap={{"2","1","6"},{"4","0","8"},{"7","5","3"}};
        String[][] emap={{"1","2","3"},{"8","0","4"},{"7","6","5"}};
        setLayout(new GridLayout(4,1));
        Ts=new Inputtxt("初始状态",smap);
        add(Ts);//将标签加入到面板上
        Te=new Inputtxt("目标状态",emap);
        add(Te);//将标签加入到面板上
        ComboBox=new ComboBoxPanel();
        add(ComboBox);
        Button=new ButtonPanel();
        add(Button);

    }
    int[][] getstartMap(){
        Button.getMap(startMap,Ts);
        return startMap;
    }
    int[][] getendMap(){
        Button.getMap(endMap,Te);
        return endMap;
    }
    class ComboBoxPanel extends JPanel{
        JComboBox sizeCombo;
        public ComboBoxPanel(){
            sizeCombo = new JComboBox();
            sizeCombo.setEditable(false);
            sizeCombo.addItem("1 宽度优先搜索算法");
            sizeCombo.addItem("2 深度优先搜索算法");
            sizeCombo.addItem("3 A*搜索算法");
            add(sizeCombo);
        }
    }
    class Inputtxt extends JPanel{
        String titel;
        JLabel label;
        private JTextField stext11;
        private JTextField stext12;
        private JTextField stext13;
        private JTextField stext21;
        private JTextField stext22;
        private JTextField stext23;
        private JTextField stext31;
        private JTextField stext32;
        private JTextField stext33;
        public Inputtxt(String str,String[][] mmap){
            titel=str;
            setLayout(new BorderLayout());
            label=new JLabel(titel,JLabel.LEFT);
            add(label,"North");
            JPanel Map=new JPanel();
            addtext1(Map,mmap);
            add(Map,"Center");
        }
        void addtext1(JPanel Map,String[][] mmap){//将文本域加入到面板上
            Map.setLayout(new GridLayout(3,3));
            stext11=new JTextField(mmap[0][0],2);
            Map.add(stext11);
            stext12=new JTextField(mmap[0][1],2);
            Map.add(stext12);
            stext13=new JTextField(mmap[0][2],2);
            Map.add(stext13);
            stext21=new JTextField(mmap[1][0],2);
            Map.add(stext21);
            stext22=new JTextField(mmap[1][1],2);
            Map.add(stext22);
            stext23=new JTextField(mmap[1][2],2);
            Map.add(stext23);
            stext31=new JTextField(mmap[2][0],2);
            Map.add(stext31);
            stext32=new JTextField(mmap[2][1],2);
            Map.add(stext32);
            stext33=new JTextField(mmap[2][2],2);
            Map.add(stext33);
        }
    }
    class ButtonPanel extends JPanel {
        JButton button1=new JButton("确认");
        JButton ExitButton=new JButton("退出");
        void getMap(int[][] map,Inputtxt T){
            int num;
            num = Integer.parseInt(T.stext11.getText());
            map[0][0]=num;
            num = Integer.parseInt(T.stext12.getText());
            map[0][1]=num;
            num = Integer.parseInt(T.stext13.getText());
            map[0][2]=num;
            num = Integer.parseInt(T.stext21.getText());
            map[1][0]=num;
            num = Integer.parseInt(T.stext22.getText());
            map[1][1]=num;
            num = Integer.parseInt(T.stext23.getText());
            map[1][2]=num;
            num = Integer.parseInt(T.stext31.getText());
            map[2][0]=num;
            num = Integer.parseInt(T.stext32.getText());
            map[2][1]=num;
            num = Integer.parseInt(T.stext33.getText());
            map[2][2]=num;
        }
        public ButtonPanel(){
            add(button1);
            add(ExitButton);
            ExitButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    System.exit(0);
                }
            });
        }
    }
}
