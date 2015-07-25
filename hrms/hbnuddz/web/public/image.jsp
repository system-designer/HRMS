<%@page import="java.io.OutputStream"%>
<%@ page contentType="image/JPEG"   
         import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"   
         pageEncoding="GBK"%>

<%!Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }%><%

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);//禁止js缓存
        
        int width = 70, height = 25;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(150, 250));//颜色，是方框的颜色//调用上面定义的函数
        g.fillRect(0, 0, width, height);//矩形

        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        //字体

        g.setColor(getRandColor(50, 150));//是点的颜色
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(2);
            int yl = random.nextInt(2);
            g.drawLine(x, y, x + xl, y + yl);
            //直线（x，y）到（x+x1，y+y1）
        }

        String sRand = "";
        for (int i = 0; i < 4; i++) {//设置字的个数
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;

            g.setColor(new Color(10 + random.nextInt(80), 20 + random.nextInt(110), 20 + random.nextInt(110)));//字的颜色
            g.drawString(rand, 15 * i + 6, 18);//x指的是字间距，y指的是离上边距的距离
        }

        session.setAttribute("code", sRand);//在后台使用

        g.dispose();//调用 dispose 之后，就不能再使用 Graphics 对象。 
        ImageIO.write(image, "JPEG", response.getOutputStream());
        out.clear();
        out = pageContext.pushBody();
%>