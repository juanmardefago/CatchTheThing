package ar.pablitar.catchthething

import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import java.awt.Font
import java.awt.Color
import java.awt.Graphics2D

class TimeAddedPopUp extends PopUp {
  
  override val color = Color.YELLOW;
  override val initPos = Vector2D(-275, 250)
  override val initSpeed = Vector2D(700, 0)
  override val accel = Vector2D(-800, 0)
  text = "TIME ADDED"
  
  this.position_=(initPos.x1, initPos.x2);


}