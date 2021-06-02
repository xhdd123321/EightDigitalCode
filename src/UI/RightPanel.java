package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel {
    public final static int N = 3;
    Inputtxt Ts;
    Inputtxt Te;
    ButtonPanel Button;
    public RightPanel(){
        setLayout(new GridLayout(3,1));
        Ts=new Inputtxt("初始");
        add(Ts);//将标签加入到面板上
        Te=new Inputtxt("目标");
        add(Te);//将标签加入到面板上
        Button=new ButtonPanel();
        add(Button);

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
        public Inputtxt(String str){
            titel=str;
            setLayout(new BorderLayout());
            label=new JLabel(titel,JLabel.LEFT);
            add(label,"North");
            JPanel Map=new JPanel();
            addtext1(Map);
            add(Map,"Center");
        }
        void addtext1(JPanel Map){//将文本域加入到面板上
            Map.setLayout(new GridLayout(3,3));
            stext11=new JTextField(2);
            Map.add(stext11);
            stext12=new JTextField(2);
            Map.add(stext12);
            stext13=new JTextField(2);
            Map.add(stext13);
            stext21=new JTextField(2);
            Map.add(stext21);
            stext22=new JTextField(2);
            Map.add(stext22);
            stext23=new JTextField(2);
            Map.add(stext23);
            stext31=new JTextField(2);
            Map.add(stext31);
            stext32=new JTextField(2);
            Map.add(stext32);
            stext33=new JTextField(2);
            Map.add(stext33);
        }
    }
    class ButtonPanel extends JPanel {
        JButton button1=new JButton("确认");
        JButton ExitButton=new JButton("退出");
        public ButtonPanel(){
            add(button1);
            button1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {

                }
            });
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
