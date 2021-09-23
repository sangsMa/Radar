package view;

import bean.student;
import controller.graphics;
import controller.ioController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainView extends JFrame{
    JSplitPane jsp1;
    JList jlist1;
    JScrollPane jspane;
    JLabel jlb1;
    int studentNo;

    ImageIcon imageIcon;

    Image image;
    public static void main(String[] args) {
        try {
            new mainView("D:\\oneDrive\\OneDrive - malu2335\\文档\\result.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public mainView(String url) throws IOException
    {
        List<student> studentList;
        List<String> names = new ArrayList<>();
        studentList = ioController.getBean(url);
        System.out.println(studentList);
        for (int i = 0; i < studentList.size(); i++) {
            student student = studentList.get(i);
            names.add(student.getName());
        }
        Object[] arryname = names.toArray();
        jlist1=new JList(arryname);
        TitledBorder borderFactory = BorderFactory.createTitledBorder("请选择学生姓名");
        borderFactory.setTitleFont(new java.awt.Font("宋体",java.awt.Font.PLAIN,18));
        jlist1.setBorder(borderFactory);
        jlist1.setFont(new java.awt.Font("宋体",java.awt.Font.PLAIN,18));

        jspane=new JScrollPane(jlist1);
        jlb1=new JLabel();

        jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspane,jlb1);
        jsp1.setDividerLocation(150);
        jlist1.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            studentNo = jlist1.getSelectedIndex();
            System.out.println(studentNo);
            image = graphics.draw(url,studentNo);
            imageIcon = new ImageIcon(image.getScaledInstance(500, 500, Image.SCALE_SMOOTH));
            jlb1.setIcon(imageIcon);
            jsp1.repaint();
        }
    });
        this.add(jsp1);

        this.setTitle("雷达图");
        this.setSize(845,654);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.setVisible(true);
    }
}
