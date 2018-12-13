import nz.sodium.*;
import nz.sodium.time.*;

public interface Animation {
   /**
    * Create an animation represented with drawable cell. Animation can be seen as a
    * transforming graphic images as a function of time. The cell can be sampled to get
    * the graphics data to be displayed at an instant in time.
    * @param sys System time when an animated scene begins
    * @param extents extents
    * @return Drawable cell that represent animated scene
    */
   Cell<Drawable> create(TimerSystem<Double> sys, Point extents);
}

