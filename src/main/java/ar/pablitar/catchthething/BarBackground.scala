package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.appearances.Rectangle
import java.awt.Color
import com.uqbar.vainilla.DeltaState
import java.awt.Graphics2D
import java.awt.AlphaComposite

class BarBackground extends RichGameComponent[CatchTheThingScene] {

  this.position = Vector2D(5, 565);

  this.setAppearance(new Rectangle(Color.BLACK, 350, 30))

  override def render(graphics: Graphics2D) = {
    if (this.getScene.timer.isCounting) {
      val before = graphics.getComposite();
      graphics.setComposite(AlphaComposite.getInstance(
        AlphaComposite.SRC_OVER, 0.5f));
      super.render(graphics);
      graphics.setComposite(before);
    }
  }
}