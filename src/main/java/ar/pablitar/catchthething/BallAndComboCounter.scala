package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import java.awt.Font
import java.awt.Color
import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.math.Vector2D

class BallAndComboCounter extends RichGameComponent[CatchTheThingScene] {

  val baseBallValue = 10;
  var score = 0;
  var currentCombo = 0;
  var labelText = "Score: ";
  val font = new Font("Trebuchet", Font.BOLD, 40);
  var shouldFlash = false;
  val flashTime = 0.2;
  var flashTimer = 0.0;
  var comboShowing = false;

  this.setAppearance(new FadeableLabel(font, Color.BLACK, "", false));

  this.position = Vector2D(300, 10);

  override def update(state: DeltaState) = {
    if (this.getScene.timer.isCounting) {
      doUpdate(state);
    }
  }

  def doUpdate(state: DeltaState) {
    super.update(state)
    updateScore(state.getDelta());
    if (currentCombo % 5 == 0 && currentCombo != 0) {
      playComboSoundAndShow()
    }
  }

  def countBall(): Unit = {
    this.currentCombo += 1;
    this.score += (baseBallValue * comboMultiplier())
    this.shouldFlash = true;
    this.addExtraTimeBarValue(baseBallValue * comboMultiplier());
  }

  def comboLost(): Unit = {
    this.currentCombo = 0;
    this.removeExtraTimeBarValue();
  }

  private def comboMultiplier(): Int = {
    return currentCombo;
  }

  private def updateScore(delta: Double) = {
    var updatedAppearance = this.getAppearance().asInstanceOf[FadeableLabel];
    updatedAppearance.setText(labelText + score);
    if (shouldFlash && flashTimer < flashTime) {
      updatedAppearance.setColor(Color.WHITE);
      flashTimer += delta;
    } else {
      updatedAppearance.setColor(Color.BLACK);
      flashTimer = 0.0;
      this.shouldFlash = false;
    }
    this.setAppearance(updatedAppearance);
  }

  def displayScoreScene(): Unit = {
    this.getScene.soundManager.shutdown();
    this.getGame.setCurrentScene(new ScoreScene(score, this.getScene.timer.totalTimePlayed));
  }

  def addExtraTimeBarValue(value: Int): Unit = {
    var newValue = value.toDouble / 100.0
    this.getScene.extraTimeBar.increaseValue(newValue)
  }

  def removeExtraTimeBarValue(): Unit = {
    this.getScene.extraTimeBar.decreaseValue(0.3)
  }

  def playComboSoundAndShow(): Unit = {
    if (!comboShowing) {
      this.getScene.soundManager.playComboSound();
      this.getScene.popup.show(currentCombo + " COMBO");
      comboShowing = true;
    }
  }
}