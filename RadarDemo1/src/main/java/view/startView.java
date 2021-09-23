package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import util.chooseUtil;

import java.io.File;
import java.io.IOException;

public class startView extends JFrame implements ActionListener{
    private JPanel contentPane;
    private JTextPane textPane;
    public static void main(String[] args) {
        new startView();
    }
    public startView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 471);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton Button = new JButton("\u9009\u62E9\u6587\u4EF6");
        Button.setActionCommand("open");
        Button.addActionListener(this);
        Button.setBounds(480, 310, 117, 34);
        contentPane.add(Button);

        JLabel Help = new JLabel("\u4F7F\u7528\u65B9\u6CD5\uFF1A\r\n\u70B9\u51FB\u9009\u62E9\u6587\u4EF6\u540E\u9009\u62E9\u8981\u6D4F\u89C8\u7684excel\u6587\u4EF6");
        Help.setFont(new Font("宋体", Font.PLAIN, 16));
        Help.setBounds(157, 200, 396, 66);
        contentPane.add(Help);

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBounds(61, 310, 397, 34);
        contentPane.add(textPane);

        JLabel Title = new JLabel("           \u6B22\u8FCE\u4F7F\u7528");
        Title.setFont(new Font("宋体", Font.BOLD, 24));
        Title.setBounds(142, 59, 364, 66);
        contentPane.add(Title);
        this.setTitle("RadarDemo");
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

    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("open")){
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(this);//显示打开的文件对话框
            File f =  jf.getSelectedFile();//使用文件类获取选择器选择的文件
            String s = f.getAbsolutePath();//返回路径名
            //JOptionPane弹出对话框类，显示绝对路径名
            boolean flag;
            flag = chooseUtil.choose(s);
            if (flag) {
                JOptionPane.showMessageDialog(this, "文件选择正确", "正确",JOptionPane.WARNING_MESSAGE);
                textPane.setText(s);
                try {
                    new mainView(s);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(this, "选择文件有误", "错误",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}