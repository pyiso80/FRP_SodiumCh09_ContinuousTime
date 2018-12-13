import java.awt.*;
import javax.swing.*;

public class Graphics2D_DrawingEg1 extends JPanel {

   private static Color color = Color.RED;
   private static int a1 = 150, b1 = 30, c1 = 100, d1 = 100;

   private static Color invert() {
      if(color.equals(Color.red))
         return Color.blue;

      if(color.equals(Color.blue))
         return Color.red;
      return Color.green;
   }

   public void paintComponent(Graphics g) {

      // draw oval
      /*
      g.setColor(color);
      g.fillOval(x-rad, (ht-1-y)-rad, rad*2, rad*2);
      g.setColor(Color.black);
      g.drawOval(x-rad, (ht-1-y)-rad, rad*2, rad*2);
      */

      color = invert();
      g.setColor(color);
      g.fillOval(a1, b1, c1, d1);
      g.setColor(Color.black);
      g.drawOval(a1, b1, c1, d1);
   }

   public static void main(String[] args) throws Exception {
      JFrame.setDefaultLookAndFeelDecorated(true);
      JFrame frame = new JFrame("Draw Oval and Circle");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setBackground(Color.white);
      frame.setSize(300, 200);

      Graphics2D_DrawingEg1 panel = new Graphics2D_DrawingEg1();

      frame.add(panel);

      frame.setVisible(true);

      Thread.sleep(3000);
      int a = 20, b = 30, c = 75, d = 100;


      a1 = a;
      b1 = b;
      c1 = c;
      d1 = d;
      frame.repaint();
   }
}