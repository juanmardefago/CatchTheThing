package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import com.uqbar.vainilla.DeltaState
import java.awt.Font
import java.awt.Color
import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.events.constants.Key
import java.text.DecimalFormat
import java.math.RoundingMode
import java.text.DecimalFormatSymbols

class Timer extends RichGameComponent[CatchTheThingScene] {

  var isCounting = false;
  var playTime = 30.0;
  var ended = false;
  val decimalFormat = new DecimalFormat("##.##");
  decimalFormat.setRoundingMode(RoundingMode.DOWN);
  var endDelay = 3.0;
  var timeIsRunningOut = false;

  val fontTitle = new Font(Font.SERIF, Font.BOLD, 50);
  val fontTimer = new Font("Trebuchet", Font.BOLD, 40);
  this.setAppearance(new PulsingLabel(fontTitle, Color.WHITE, "Press ENTER to play", true));
  this.position = Vector2D(180, 275);

  // Si cuando updateo el timer esta contando, entonces el juego tiene que estar "running"
  // Sino, significa que, si no termino, tiene que verificarse que se toque el enter, para empezar el game
  // Sino, significa que o no se toco el enter, o ya termino, y si termino se tiene que mostrar el
  // fade to black, que luego cambia a la pantalla del score
  override def update(state: DeltaState) = {
    super.update(state);
    if (isCounting) {
      runGame(state);
    } else if (state.isKeyPressed(Key.ENTER) && !ended) {
      this.isCounting = true;
      setLabelForTimer();
    } else if (ended) {
      endingFade(state);
    }
  }

  def runGame(state: DeltaState) {
    if (playTime > 5 + state.getDelta()) {
      playTime -= state.getDelta();
      updateAppearanceText(decimalFormat.format(playTime));
    } else if (playTime >= 0 + state.getDelta()) {
      playTime -= state.getDelta();
      updateAppearanceText(decimalFormat.format(playTime));
      setTimerUnder5();
    } else {
      isCounting = false;
      ended = true;
      timeIsRunningOut = false;
      this.getScene.addComponent(new FadingScreen[CatchTheThingScene]);
    }
  }

  private def setTimerUnder5() {
    if (!timeIsRunningOut) {
      timeIsRunningOut = true;
      appearancePulse(true);
      appearanceColor(Color.RED);
      appearancePulseTimeLoop(0.75f);
      appearanceRestartPulseTimer();
    }
  }

  private def setLabelForTimer() {
    this.position = Vector2D(10, 10);
    appearancePulse(false);
    updateAppearanceText(decimalFormat.format(playTime));
    var updatedAppearance = this.getAppearance().asInstanceOf[PulsingLabel];
    updatedAppearance.setFont(fontTimer)
  }

  private def endingFade(state: DeltaState) = {
    if (endDelay > 0) {
      endDelay -= state.getDelta;
    } else {
      this.getScene.counter.displayScoreScene();
    }
  }

  private def updateAppearanceText(text: String) {
    var updatedAppearance = this.getAppearance().asInstanceOf[PulsingLabel];
    updatedAppearance.setText(text);
    //    this.setAppearance(updatedAppearance);
  }

  private def appearancePulse(shouldPulse: Boolean) {
    var updatedAppearance = this.getAppearance().asInstanceOf[PulsingLabel];
    updatedAppearance.setPulse(shouldPulse);
    //    this.setAppearance(updatedAppearance);
  }

  private def appearanceColor(color: Color) = {
    var updatedAppearance = this.getAppearance().asInstanceOf[PulsingLabel];
    updatedAppearance.setColor(color);
    //    this.setAppearance(updatedAppearance);
  }

  private def appearancePulseTimeLoop(seconds: Float) = {
    var updatedAppearance = this.getAppearance().asInstanceOf[PulsingLabel];
    updatedAppearance.pulseTime = seconds;
    //    this.setAppearance(updatedAppearance);
  }

  private def appearanceRestartPulseTimer(): Unit = {
    var updatedAppearance = this.getAppearance().asInstanceOf[PulsingLabel];
    updatedAppearance.restartTimer();
  }

}