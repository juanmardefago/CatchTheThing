package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import java.awt.Font
import com.uqbar.vainilla.appearances.Label
import java.awt.Color
import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.math.Vector2D

class BallAndComboCounter extends RichGameComponent[CatchTheThingScene] {

  val baseBallValue = 10;
  var score = 0;
  var currentCombo = 0;
  var labelText = "Score: ";
  val font = new Font(Font.SERIF, Font.BOLD, 40);
  var shouldFlash = false;
  val flashTime = 0.2;
  var flashTimer = 0.0;

  this.setAppearance(new Label(font, Color.BLACK, labelText));

  this.position_=(new Vector2D(600, 0));
  this.center();

  override def update(state: DeltaState) = {
    super.update(state)
    updateScore(state.getDelta());
    if (currentCombo % 5 == 0 && currentCombo != 0) {
      this.getScene.popup.show(currentCombo);
    }
  }

  def countBall(): Unit = {
    this.currentCombo += 1;
    this.score += (baseBallValue * comboMultiplier())
    this.shouldFlash = true;
  }

  def comboLost(): Unit = {
    this.currentCombo = 0;
  }

  private def comboMultiplier(): Int = {
    return currentCombo;
  }

  private def updateScore(delta : Double) = {
    var updatedAppearance = this.getAppearance().asInstanceOf[Label];
    updatedAppearance.setText(labelText + score);
    if(shouldFlash && flashTimer < flashTime){
      updatedAppearance.setColor(Color.WHITE);
      flashTimer += delta;
    } else {
      updatedAppearance.setColor(Color.BLACK); 
      flashTimer = 0.0;
      this.shouldFlash = false;
    }
    this.setAppearance(updatedAppearance);
  }
}