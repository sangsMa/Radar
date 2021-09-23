package controller;


import bean.student;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class graphics{
    public static Image draw(String url,int i){
        int [] vn = new int[6];
        List<student> list= null;
        try {
            list = ioController.getBean(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        student student = list.get(i);
        vn[0] = student.getMarkChinese()*2/3;
        vn[1] = student.getMarkMath()*2/3;
        vn[2] = student.getMarkEnglish()*2/3;
        vn[3] = student.getMarkPhysics();
        vn[4] = student.getMarkChemistry();
        vn[5] = student.getMarkBiology();
        int width = 5000;
        int height = 5000;
        int num = 3;
        int excessiveVariable = 800;
        int margin = 300;
        Image image = drawPolygon(vn, width, height, num, excessiveVariable, margin);
        return image;
    }
    /**
     * 绘制多边形
     * @param vn
     * @param width 图宽（像素）
     * @param height    图高（像素）
     * @param num   内多边形个数
     * @param excessiveVariable 最内侧多边形大小（像素）
     * @param margin    累加尺寸大小（像素）
     * @throws IOException io异常
     */
    public static Image drawPolygon(int[] vn, int width, int height, int num,
                                    int excessiveVariable, int margin){
        int bian = vn.length;//边数
        Image bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        Color c=g.getColor();
        g.setColor(Color.getColor("#ffffff"));
        g.fillRect(0,0,width,height);
        g.setColor(c);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2F));
        int min = 30,deta=100,mark=50;
        int [][]px = new int[bian][num];  //一维：五个角中的那个角，二维：从里到外0-4
        int [][]py = new int[bian][num];
        Double centerX = width/2D - 50;//中心坐标
        Double centerY = height/2D;//中心坐标
        int fontSize = 300;
        double angle=0.0;
        //计算n个五边形的顶点。
        for(int i=0; i<bian; i++){
            angle = 360/bian*Math.PI*i/180;
            for(int j=0; j<num; j++){
                px[i][j]=(int) Math.round(centerX+(excessiveVariable+j*margin)*Math.sin(angle));
                py[i][j]=(int) Math.round(centerY-(excessiveVariable+j*margin)*Math.cos(angle));
            }
        }
        Color lineColor = new Color(230, 230, 230);
        Color propColor = new Color(255, 169, 151);
        drawLine(centerX, centerY, bian, num, g, px, py, lineColor, 15f); //画线
        drawProportion(vn, bian, num, excessiveVariable, margin, g, centerX, centerY, propColor, 15f);//画比例图
        drawDot(vn, bian, num, excessiveVariable, margin, g, centerX, centerY, new Color(255, 82, 46), propColor, ((int)(width*0.012))*2);//画点
//        drawString(g, centerX, centerY, fontSize);//画字
//        ImageIO.write(bi, "png", file);
        return bi;
    }

    private static void drawString(Graphics2D g, Double centerX,
                                   Double centerY, String _Name, int fontSize) {
        String str = _Name;
        g.setFont(new Font("宋体", Font.PLAIN, fontSize+4));// 设置字体
        g.setColor(new Color(127, 127, 127));
        g.drawString(str, centerX.intValue()-g.getFontMetrics().stringWidth(str)/2-6, centerY.intValue()+g.getFontMetrics().getHeight()/2-16);
        g.setFont(new Font("宋体", Font.PLAIN, fontSize-2));
        g.setColor(new Color(174, 174, 174));
        g.drawString(str, centerX.intValue()-g.getFontMetrics().stringWidth(str)/2-3, centerY.intValue()+g.getFontMetrics().getHeight()/2-8);
        g.setFont(new Font("宋体", Font.PLAIN, fontSize));
        g.setColor(new Color(255, 255, 255));
        g.drawString(str, centerX.intValue()-g.getFontMetrics().stringWidth(str)/2, centerY.intValue()+g.getFontMetrics().getHeight()/2);
    }
    /**
     * 画线
     * @param width 宽
     * @param height    高
     * @param bian  边数
     * @param num   个数
     * @param g
     * @param px
     * @param py
     */
    private static void drawLine(Double width, Double height, int bian, int num,
                                 Graphics2D g, int[][] px, int[][] py, Color lineColor, float lineSize) {
        int [] x = new int[bian*bian+bian];
        int [] y = new int[bian*bian+bian];
        g.setStroke(new BasicStroke(lineSize));
        g.setColor(lineColor);
        for(int j = 0;j<num;j++){
            for(int i = 0;i<bian;i++){
                x[i] = px[i][j];
                y[i] = py[i][j];
                g.drawLine(x[i], y[i], width.intValue(), height.intValue());
            }
            g.drawPolygon(x, y, bian);
        }
    }
    /**
     * 画比例
     * @param vn
     * @param bian
     * @param num
     * @param excessiveVariable
     * @param margin
     * @param g
     * @param centerX
     * @param centerY
     */
    private static void drawProportion(int[] vn, int bian, int num,
                                       int excessiveVariable, int margin, Graphics2D g, Double centerX,
                                       Double centerY, Color proColor, float lineSize) {
        double angle;
        int []minX = new int[bian];
        int []minY = new int[bian];
        for(int i = 0;i<bian;i++){
            angle = 360/bian*Math.PI*i/180;
            minX[i] = (int) Math.round(centerX+((excessiveVariable+(num-1)*margin)/100*vn[i])*Math.sin(angle));
            minY[i] = (int) Math.round(centerY-((excessiveVariable+(num-1)*margin)/100*vn[i])*Math.cos(angle));
        }

        //向定点画字
        double [] topPotTextX = new double[bian];
        double [] topPotTextY = new double[bian];
        String [] nameArray = new String[]{"语文", "数学", "英语", "物理", "化学", "生物"};
        for(int i = 0;i<bian;i++){
            angle = 360/bian*Math.PI*i/180;
            topPotTextX[i] = Math.round(centerX+((excessiveVariable+(num-1)*margin)/100*130)*Math.sin(angle));
            topPotTextY[i] = Math.round(centerY-((excessiveVariable+(num-1)*margin)/100*130)*Math.cos(angle));
            drawString(g, topPotTextX[i], topPotTextY[i], nameArray[i], 200);
        }

        g.setColor(proColor);
        g.setStroke(new BasicStroke(lineSize));
        g.drawPolygon(minX, minY, bian);
        g.setStroke(new BasicStroke());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.1f));
        g.fillPolygon(minX, minY, bian);
    }
    /**
     * 画比例定点
     * @param vn
     * @param bian
     * @param num
     * @param excessiveVariable
     * @param margin
     * @param g
     * @param centerX
     * @param centerY
     * @param dotColor
     * @param centerDotColor
     * @param dotSize
     */
    private static void drawDot(int[] vn, int bian, int num, int excessiveVariable, int margin, Graphics2D g, Double centerX, Double centerY, Color dotColor, Color centerDotColor, int dotSize){
        double angle;
        int dotWidth = dotSize;
        int dotHeight = dotSize;
        int []minX = new int[bian];
        int []minY = new int[bian];
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f));
        for(int i = 0;i<bian;i++){
            angle = 360/bian*Math.PI*i/180;
            minX[i] = (int) Math.round(centerX+((excessiveVariable+(num-1)*margin)/100*vn[i])*Math.sin(angle));
            minY[i] = (int) Math.round(centerY-((excessiveVariable+(num-1)*margin)/100*vn[i])*Math.cos(angle));
            g.setColor(dotColor);
            g.fillOval(minX[i]-dotWidth/2, minY[i]-dotHeight/2, dotWidth, dotHeight);
            g.setColor(centerDotColor);
            g.fillOval(minX[i]-(dotWidth/2)/2, minY[i]-(dotHeight/2)/2, dotWidth/2, dotHeight/2);//画内点
        }
    }
}