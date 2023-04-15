



package Com.Chat_application;

import org.w3c.dom.events.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public  class server2 implements ActionListener{
    static   JTextField tf;
    static JPanel p1;
    static JPanel p3;
  static   Box ver=Box.createVerticalBox();
  static   JFrame f;
  static DataOutputStream dos;
    server2() {
        f = new JFrame();
        f.setLayout(null);
        JPanel p = new JPanel();
        p.setBackground(new Color(7, 94, 84));
        p.setBounds(0, 0, 450, 70);
//p.setSize(450,80);
        p.setLayout(null);
        f.add(p);

        //arrows
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("icon/3.png"));
        Image I1a = I1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon I1b = new ImageIcon(I1a);
        JLabel l = new JLabel(I1b);
        l.setBounds(5, 25, 25, 25);
        p.add(l);

        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });


        //profile
        ImageIcon I2 = new ImageIcon(ClassLoader.getSystemResource("icon/2.png"));
        Image I2a = I2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon I2b = new ImageIcon(I2a);
        JLabel profile = new JLabel(I2b);
        profile.setBounds(40, 15, 50, 50);
        p.add(profile);

        //call
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/phone.png"));
        Image i3a = i3.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i3b = new ImageIcon(i3a);
        JLabel call = new JLabel(i3b);
        call.setBounds(360, 20, 35, 30);
        p.add(call);

        //videocall
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icon/video.png"));
        Image i4a = i4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i4b = new ImageIcon(i4a);
        JLabel vc = new JLabel(i4b);
        vc.setBounds(300, 20, 30, 30);
        p.add(vc);

        //setting
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/3icon.png"));
        Image i5a = i5.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i5b = new ImageIcon(i5a);
        JLabel setting = new JLabel(i5b);
        setting.setBounds(415, 20, 10, 25);
        p.add(setting);
//name
        JLabel name=new JLabel("AMAR2.0");
        name.setFont(new Font("tamoha",Font.BOLD,18));
        name.setBounds(110,15,100,18);
        name.setForeground(Color.white);
        p.add(name);

//online
        JLabel status=new JLabel("online");
        status.setFont(new Font("tamoha",Font.BOLD,10));
        status.setBounds(110,40,75,10);
        status.setForeground(Color.white);
        p.add(status);

        p1 = new JPanel();
        p1.setBounds(5, 75, 440, 570);
        // p1.setBackground(Color.red);
        f.add(p1);

        //textfield
        tf = new JTextField();
        tf.setBounds(5, 655, 310, 40);
        tf.setFont(new Font("tamoha", Font.PLAIN, 13));
        p1.setLayout(null);
        f.add(tf);

        //send button
        JButton b = new JButton("send");
        b.setForeground(Color.white);
        b.setBackground(new Color(7, 94, 84));
        b.setBounds(320, 655, 123, 40);
        b.setFont(new Font("tamo", Font.BOLD, 16));
        b.addActionListener(this);
        f.add(b);


        f.setBounds(0, 0, 450, 700);
        f.setLocation(0, 10);
        f.setUndecorated(true);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
try{
        String out=tf.getText();
//JLabel output=new JLabel(out);
        p3=fl(out);
        //   p3.add(output);

        JPanel p4=new JPanel(new BorderLayout());
        p4.add(p3,BorderLayout.LINE_END);
        ver.add(p4);
        ver.add(Box.createVerticalStrut(13));

        p1.setLayout(new BorderLayout());
        p1.add(ver, BorderLayout.PAGE_START);
        dos.writeUTF(out);

        f.repaint();
        f.invalidate();
        f.validate();}catch (Exception ex){
    ex.printStackTrace();
        }
    }
    public static JPanel fl(String out){
        JPanel jp=new JPanel();
        JLabel output=new JLabel("<html><p style=\"width:150 px\">"+out+"</p></html>");
        jp.add(output);

        jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));

        output.setFont( new Font("Tahoma,",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));

        jp.add(time);

        return jp;
    }
    public static void main(String[] args) {
        new server2();
try{
    Socket st=new Socket("127.0.0.1",2055);
    while (true){
        DataInputStream din=new DataInputStream(st.getInputStream());
         dos=new DataOutputStream(st.getOutputStream());
        while(true){
            String msg=din.readUTF();
            JPanel jp=fl(msg);


            JPanel left=new JPanel(new BorderLayout());
            left.add(jp,BorderLayout.LINE_START);
            ver.add(left);
        //    ver.add(Box.createVerticalStrut(15));
            p1.setLayout(new BorderLayout());
            p1.add(ver,BorderLayout.PAGE_START);
            f.validate();

        }
    }



}catch (Exception e){
    e.printStackTrace();
}

        new server2();
    }


}
