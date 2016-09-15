package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.DeltaState
import java.awt.Graphics2D
import java.awt.AlphaComposite
import java.awt.Color

class ExtraTimeBar(bg: BarBackground) extends RichGameComponent[CatchTheThingScene] {
  var value = 0.0;

  val background = bg;
  background.position = Vector2D(445, 565);

  this.position = Vector2D(792, 568)

  val initialX = position.x1;

  var fullWidth = 344;
  val bar = new ResizableRectangle(Color.CYAN, 0, 24);

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
    this.compensateWidthChange(newWidth)
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
    if (value >= 15 + deltaDecrease) {
      value -= deltaDecrease;
    } else if (value >= deltaDecrease) {
      value -= deltaDecrease;
      this.getAppearance.asInstanceOf[ResizableRectangle].changeColor(Color.CYAN);
    }
  }

  def increaseValue(deltaIncrease: Double): Unit = {
    if (value <= 100 - deltaIncrease) {
      value += deltaIncrease;
    }
    if (value >= 15) {
      this.getAppearance.asInstanceOf[ResizableRectangle].changeColor(Color.YELLOW);
    } else if (value > 100 - deltaIncrease) {
      value = 100.0
    }
  }

  def compensateWidthChange(width: Double): Unit = {
    var newXPosition = initialX - width;
    this.position = Vector2D(newXPosition, this.position.x2);
  }

  def canAddTime(): Boolean = {
    return value >= 15;
  }

  def chargeTime(): Unit = {
    var time = value * 0.2
    this.getScene.timer.addTime(time)
    this.value = 0.0
    this.getAppearance.asInstanceOf[ResizableRectangle].changeColor(Color.CYAN);
    this.getScene.soundManager.playTimeAddedSound();
  }
}