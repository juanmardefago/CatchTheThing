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
  var playTime = 60.0;
  var ended = false;
  val decimalFormat = new DecimalFormat("##.##");
  decimalFormat.setRoundingMode(RoundingMode.DOWN);

  val font = new Font(Font.SERIF, Font.BOLD, 50);
  this.setAppearance(new FadeableLabel(font, Color.WHITE, "", false));
  this.position = Vector2D(180, 275);

  override def update(state: DeltaState) = {
    if (isCounting) {
      runGame(state);
    } else if (state.isKeyPressed(Key.ENTER) && !ended) {
      this.isCounting = true;
      this.position = Vector2D(10, 10);
      updateAppearanceText(decimalFormat.format(playTime));
    } else if (!ended){
      updateAppearanceText("Press ENTER to play");
    }
  }

  def runGame(state : DeltaState){
      if (playTime > 0) {
        playTime -= state.getDelta();
        updateAppearanceText(decimalFormat.format(playTime));
      } else {
        isCounting = false;
        ended = true;
        playTime = 0.0;
      }    
  }
  
  def updateAppearanceText(text: String) {
    var updatedAppearance = this.getAppearance().asInstanceOf[FadeableLabel];
    updatedAppearance.setText(text);
    this.setAppearance(updatedAppearance);
  }

}