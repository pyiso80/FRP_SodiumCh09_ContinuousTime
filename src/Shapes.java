import nz.sodium.Cell;

import java.awt.*;

/**
 * Module for manipulation of shapes.
 */
class Shapes {
   /**
    * Create an instance of drawable cell that represent a circle.
    * @param color Fill color
    * @return An instance of drawable cell that represent a circle.
    */
   static Cell<Drawable> circle(Color color) {
      /* Create and return a constant cell */
      return new Cell<>(new Drawable() {
         @Override
         public void draw(Graphics g, int ht, Point offset, double sc) {
            int rad = (int)sc;
            int x = (int)offset.x;
            int y = (int)offset.y;
            g.setColor(color);
            g.fillOval(x-rad, (ht-1-y)-rad, rad*2, rad*2);
            g.setColor(Color.black);
            g.drawOval(x-rad, (ht-1-y)-rad, rad*2, rad*2);
         }
      });
   }

   /**
    * Form a new drawable cell by combining a source drawable cell and scale factor cell.
    * @param dr Source drawable cell
    * @param sc Scale factor cell
    * @return A new drawable cell which is scaled continuously with time
    */
   static Cell<Drawable> scale(Cell<Drawable> dr, Cell<Double> sc) {
      Cell<Drawable> newDr = dr.lift(sc, (dr_, newSc) -> new Drawable() {
         @Override
         public void draw(Graphics g, int ht, Point offset, double sc) {
            /*
            Call the source drawable's draw method with current scale value queried
            from scale factor cell.
            */
            dr_.draw(g, ht, offset, sc * newSc);
         }
      });
      return newDr;
   }

   static Cell<Drawable> translate(Cell<Drawable> drawable,
                                   Cell<Point> offset) {
      return drawable.lift(offset, (dr, o) -> new Drawable() {
         public void draw(Graphics g, int ht, Point offset, double sc) {
            dr.draw(g, ht, offset.add(o.multiply(sc)), sc);
         }
      });
   }

   static Cell<Drawable> over(Cell<Drawable> a, Cell<Drawable> b) {
      return a.lift(b, (dra, drb) -> new Drawable() {
         public void draw(Graphics g, int ht, Point offset, double sc) {
            drb.draw(g, ht, offset, sc);
            dra.draw(g, ht, offset, sc);
         }
      });
   }
}

