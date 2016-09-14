package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.math.Vector2D
import java.awt.Color
import java.awt.Font
import com.uqbar.vainilla.events.constants.Key

class Restarter extends RichGameComponent[ScoreScene] {

  val font = new Font(Font.SERIF, Font.BOLD, 45);
  this.setAppearance(new PulsingLabel(font, Color.WHITE, "Press ENTER to play again!", true));
  this.position = Vector2D(125, 300)

  override def update(state: DeltaState) {
    super.update(state);
    if (state.isKeyPressed(Key.ENTER)) {
      restartGame()
    }
  }

  private def restartGame(): Unit = {
    this.getScene.soundManager.shutdown();
    this.getGame.setCurrentScene(new CatchTheThingScene())
  }
}