package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import java.awt.Font
import java.awt.Color
import ar.pablitar.vainilla.commons.math.Vector2D

class ScoreDisplayer(score: Int) extends RichGameComponent[CatchTheThingScene]{
    
    val textEnding = if(score < 2000) "you should practice more..."
    else if(score < 6000) "         pretty decent!"
    else if(score < 9000) "           really good!"
    else "  wow... just wow..."
  
  
    val font = new Font("Helvetica", Font.BOLD, 45);
    this.setAppearance(new FadeableLabel(font, Color.WHITE, "   Your score was " + score + "\n" + textEnding, false));
    this.position = Vector2D(125,100)
    
}