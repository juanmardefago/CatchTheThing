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
  var playTime = 2.0;
  var ended = false;
  val decimalFormat = new DecimalFormat("##.##");
  decimalFormat.setRoundingMode(RoundingMode.DOWN);
  var endDelay = 3.0;

  val font = new Font(Font.SERIF, Font.BOLD, 50);
  this.setAppearance(new PulsingLabel(font, Color.WHITE, "Press ENTER to play", true));
  this.position = Vector2D(180, 275);

  override def update(state: DeltaState) = {
    super.update(state);
    if (isCounting) {
      runGame(state);
    } else if (state.isKeyPressed(Key.ENTER) && !ended) {
      this.isCounting = true;
      this.position = Vector2D(10, 10);
      appearancePulse(false);
      updateAppearanceText(decimalFormat.format(playTime));
    } else if (ended){
      if(endDelay > 0){
        endDelay -= state.getDelta;
      } else {
        this.getScene.counter.displayScoreScene();
      }
    }
  }

  def runGame(state: DeltaState) {
    if (playTime >= 0 + state.getDelta()) {
      playTime -= state.getDelta();
      updateAppearanceText(decimalFormat.format(playTime));
    } else {
      isCounting = false;
      ended = true;
      this.getScene.addComponent(new FadingScreen[CatchTheThingScene]);
    }
  }

  private def updateAppearanceText(text: String) {
    var updatedAppearance = this.getAppearance().asInstanceOf[PulsingLabel];
    updatedAppearance.setText(text);
    this.setAppearance(updatedAppearance);
  }

  private def appearancePulse(shouldPulse: Boolean) {
    var updatedAppearance = this.getAppearance().asInstanceOf[PulsingLabel];
    updatedAppearance.setPulse(shouldPulse);
    this.setAppearance(updatedAppearance);
  }
}