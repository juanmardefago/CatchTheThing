package ar.pablitar.catchthething

import java.awt.Font
import com.uqbar.vainilla.DeltaState
import java.awt.Color
import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.appearances.Invisible
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import java.awt.Graphics2D

class ComboPopUp extends PopUp {

  override val color = Color.RED;
  override val initPos = new Vector2D(875, 250)
  override val initSpeed = Vector2D(-700, 0)
  override val accel = Vector2D(800, 0)
  override val font = new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 40);

  this.position_=(initPos.x1, initPos.x2);

  override def onShowEnd() : Unit = {
    this.getScene.counter.comboShowing = false;
  }
}