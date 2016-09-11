package ar.pablitar.catchthething

import java.awt.Font
import com.uqbar.vainilla.DeltaState
import java.awt.Color
import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.appearances.Invisible
import ar.pablitar.vainilla.commons.components.SpeedyComponent

class ComboPopUp extends SpeedyComponent[CatchTheThingScene] {

  val maxTimeShowing = 2;
  var timer = 0.0;
  var showing = false;
  var combo = 0;

  val font = new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 40);
  val baseText = " COMBO";
  override val acceleration = Some(Vector2D(800, 0))

  val initialLabel = new FadeableLabel(font, Color.RED, "");

  this.setAppearance(initialLabel);

  this.position_=(new Vector2D(875, 250));

  override def update(state: DeltaState) = {
    super.update(state)
    if (showing) {
      this.showRoutine(state.getDelta());
    } else {
      this.setAppearance(new FadeableLabel(font, Color.RED, ""));
    }
  }

  def show(combo: Int): Unit = {
    if (!showing) {
      showing = true;
      this.combo = combo;
      this.easingInit();
    }
  }

  private def showRoutine(delta: Double): Unit = {
    if (timer < maxTimeShowing) {
      timer += delta;
      var updatedAppearance = this.getAppearance().asInstanceOf[FadeableLabel];
      updatedAppearance.setText(combo + baseText);
      this.setAppearance(updatedAppearance);
    } else {
      showing = false;
      timer = 0.0;
      this.setAppearance(new FadeableLabel(font, Color.RED, ""));
    }
  }

  private def easingInit() {
    this.position_=(new Vector2D(875, 250));
    this.speed = (Vector2D(-700, 0));
  }
}