import java.awt.Graphics;

public class Drawable {

   /**
    *
    * @param g graphics to be drawn
    * @param ht height
    * @param orig origin
    * @param scale scale factor for drawing
    */
   public void draw(Graphics g, int ht, Point orig, double scale) {}

   /**
    *
    * @param second 2nd graphics to be drawn
    * @return Drawable
    */
   public final Drawable append(Drawable second) {
      Drawable first = this;
      return new Drawable() {
         public void draw(Graphics g, int ht, Point orig, double sc) {
            first.draw(g, ht, orig, sc);
            second.draw(g, ht, orig, sc);
         }
      };
   }
}

