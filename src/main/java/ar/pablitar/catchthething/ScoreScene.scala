package ar.pablitar.catchthething

import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.appearances.Rectangle
import java.awt.Color

class ScoreScene(score: Int) extends GameScene {
  
  val background = new GameComponent(new Rectangle(Color.BLACK, 800, 600),0,0)
  background.setZ(-50)
  
  this.addComponent(new ScoreDisplayer(score));
  this.addComponent(background);
  this.addComponent(new Restarter);
}