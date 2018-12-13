import java.awt.Color;

import nz.sodium.*;

public class fwoomph extends Shapes {
   public static void main(String[] args) {
      /**
       * An animation that represent a circle enlarging and shrinking with time.
       * Only when its create method is invoked, animation is activated.
       */
      Cell<Drawable> circle = circle(Color.LIGHT_GRAY);
      Animation animation = (sys, extents) -> {
         double maxSize = 200.0;
         Cell<Double> time = sys.time;
         Cell<Double> scale = time.map(t -> {
            /* extract the fractional part (Mantissa) */
            double frac = t - Math.floor(t);
            /* increase progressively until 0.5 and decrease
            again approaching zero */
            return (frac < 0.5 ? frac : 1.0 - frac) * maxSize;
         });
         return scale(circle, scale);
      };
      Animate.animate("fwoomph", animation);
   }
}

