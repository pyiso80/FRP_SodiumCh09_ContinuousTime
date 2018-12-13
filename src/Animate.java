import nz.sodium.*;
import nz.sodium.time.SecondsTimerSystem;

import javax.swing.*;
import java.awt.*;

/**
 * Represent a panel on which animation graphic being played
 */
public class Animate extends JPanel {

   private final Cell<Drawable> drawable;
   private final Dimension windowSize;

   private Animate(Animation anim, Dimension windowSize) {
      Point e = new Point(windowSize.width/2.0, windowSize.height/2.0);
      Cell<Drawable> a = anim.create(new SecondsTimerSystem(), e);
      this.drawable = Transaction.run (() -> Shapes.translate(a, new Cell<>(e)));
      this.windowSize = windowSize;
   }

   @Override
   public Dimension getPreferredSize() { return windowSize; }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      drawable.sample().draw(g, windowSize.height, new Point(0,0), 1.0);
      Toolkit.getDefaultToolkit().sync();
   }

   /**
    * Create a window that display animation.
    * @param title Animation window's title
    * @param anim Animation
    */
   static void animate(String title, Animation anim) {
      JFrame frame = new JFrame(title);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel view = new Animate(anim, new Dimension(500, 350));
      frame.setContentPane(view);
      frame.pack();
      frame.setVisible(true);
      long tLast = System.currentTimeMillis();
      while (true) {
         long t = System.currentTimeMillis();
         long tIdeal = tLast + 15;
         long toWait = tIdeal - t;
         if (toWait > 0) {
            try {
               Thread.sleep(toWait);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         Transaction.runVoid(() -> {});
         view.repaint(0);
         tLast = tIdeal;
      }
   }
}

