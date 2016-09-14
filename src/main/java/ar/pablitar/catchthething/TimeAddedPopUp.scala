package ar.pablitar.catchthething

import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import java.awt.Font
import java.awt.Color
import java.awt.Graphics2D

class TimeAddedPopUp extends SpeedyComponent[CatchTheThingScene] {

  val maxTimeShowing = 2;
  var timer = 0.0;
  var showing = false;

  val font = new Font(Font.SERIF, Font.BOLD, 40);
  val baseText = "TIME ADDED";
  override val acceleration = Some(Vector2D(-800, 0))

  val initialLabel = new FadeableLabel(font, Color.YELLOW, "");

  this.setAppearance(initialLabel);

  this.position_=(new Vector2D(-275, 250));

  override def update(state: DeltaState) = {
    super.update(state)
    if (showing) {
      this.showRoutine(state.getDelta());
    } else {
      this.setAppearance(new FadeableLabel(font, Color.YELLOW, ""));
    }
  }

  override def render(graphics: Graphics2D) {
    if (showing) {
      super.render(graphics)
    }
  }

  def show(): Unit = {
    if (!showing) {
      showing = true;
      this.easingInit();
    }
  }

  private def showRoutine(delta: Double): Unit = {
    if (timer < maxTimeShowing) {
      timer += delta;
      this.getAppearance().asInstanceOf[FadeableLabel].setText(baseText);
    } else {
      showing = false;
      timer = 0.0;
      this.setAppearance(new FadeableLabel(font, Color.YELLOW, ""));
    }
  }

  private def easingInit() {
    this.position_=(new Vector2D(-275, 250));
    this.speed = (Vector2D(700, 0));
  }
}