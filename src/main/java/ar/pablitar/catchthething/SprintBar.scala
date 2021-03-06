package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.math.Vector2D
import java.awt.Graphics2D
import java.awt.AlphaComposite
import java.awt.Color

class SprintBar(bg: BarBackground) extends RichGameComponent[CatchTheThingScene] {

  var value = 100.0;

  val background = bg;

  this.position = Vector2D(8, 568)

  val positiveColor = Color.GREEN
  val negativeColor = Color.ORANGE

  var fullWidth = 344;
  val bar = new ResizableRectangle(positiveColor, fullWidth, 24);

  var canSprint = true;

  this.setAppearance(bar)

  override def update(state: DeltaState) = {
    if (this.getScene.timer.isCounting) {
      doUpdate(state);
    }
    super.update(state)
  }

  def doUpdate(state: DeltaState) = {
    val newWidth = fullWidth * (value / 100)
    bar.resizeWidth(newWidth)
  }

  override def render(graphics: Graphics2D) = {
    if (this.getScene.timer.isCounting) {
      val before = graphics.getComposite();
      graphics.setComposite(AlphaComposite.getInstance(
        AlphaComposite.SRC_OVER, 0.9f));
      super.render(graphics);
      graphics.setComposite(before);
    }
  }

  def decreaseValue(deltaDecrease: Double): Unit = {
    if (value >= deltaDecrease) {
      value -= deltaDecrease;
    } else {
      canSprint = false;
      bar.changeColor(negativeColor)
    }
  }

  def increaseValue(deltaIncrease: Double): Unit = {
    if (value <= 100 - deltaIncrease) {
      value += deltaIncrease;
    }
    if (!canSprint && value > 50) {
      canSprint = true;
      bar.changeColor(positiveColor);
    }
  }
}