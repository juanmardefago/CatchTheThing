package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.SpeedyComponent
import java.awt.Font
import ar.pablitar.vainilla.commons.math.Vector2D
import java.awt.Color
import com.uqbar.vainilla.DeltaState
import java.awt.Graphics2D

class PopUp extends SpeedyComponent[CatchTheThingScene] {

  val maxTimeShowing = 2.0;
  var timer = 0.0;
  var showing = false;

  var text = "";
  val font = new Font(Font.SERIF, Font.BOLD, 40);
  val color = Color.WHITE;
  val initialLabel = new FadeableLabel(font, color, "");
  this.setAppearance(initialLabel);

  val initPos = Vector2D(0, 0);
  val initSpeed = Vector2D(0, 0);
  val accel = Vector2D(0, 0);
  override val acceleration = Some(accel)

  this.position = Vector2D(0, 0);

  override def update(state: DeltaState) = {
    super.update(state)
    if (showing) {
      this.showRoutine(state);
    }
  }

  override def render(graphics: Graphics2D) {
    if (showing) {
      super.render(graphics)
    }
  }

  def show(text: String = this.text): Unit = {
    if (!showing) {
      showing = true;
      this.text = text;
      this.easingInit();
    }
  }

  protected def showRoutine(state: DeltaState): Unit = {
    if (timer < maxTimeShowing) {
      timer += state.getDelta;
      var updatedAppearance = this.getAppearance().asInstanceOf[FadeableLabel];
      updatedAppearance.setText(text);
      this.onShowBegin()
    } else {
      showing = false;
      timer = 0.0;
      this.onShowEnd()
    }
    super.update(state)
  }

  protected def easingInit() {
    this.position = initPos;
    this.speed = initSpeed;
  }

  protected def onShowBegin(): Unit = {

  }

  protected def onShowEnd(): Unit = {

  }
}