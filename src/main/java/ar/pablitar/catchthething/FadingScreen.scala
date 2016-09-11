package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.DeltaState
import java.awt.Graphics2D
import java.awt.Composite
import java.awt.AlphaComposite
import com.uqbar.vainilla.appearances.Rectangle
import java.awt.Color

class FadingScreen[T <: GameScene] extends RichGameComponent[T] {
  val fadeTime = 2.5;
  var fadeTimer = 0.0;
  
  this.setAppearance(new Rectangle(Color.BLACK, 800, 600));

  override def update(state: DeltaState) {
    super.update(state);
    if (fadeTimer < fadeTime - state.getDelta()) {
      fadeTimer += state.getDelta();
    } else {
      fadeTimer = fadeTime
    }
  }

  override def render(graphics: Graphics2D) {
    val before = graphics.getComposite();
    graphics.setComposite(AlphaComposite.getInstance(
      AlphaComposite.SRC_OVER, this.alpha()));
    super.render(graphics);
    graphics.setComposite(before);
  }
  
  def alpha() : Float = {
    var res:Float = 0;
		if(fadeTimer < fadeTime){
		  res = ((fadeTimer.toFloat)/(fadeTime.toFloat));
		} else {
			res = 1;
		}
		return res;
  }
}