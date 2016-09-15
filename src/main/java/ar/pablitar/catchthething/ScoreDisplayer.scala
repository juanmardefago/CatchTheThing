package ar.pablitar.catchthething

import ar.pablitar.vainilla.commons.components.RichGameComponent
import java.awt.Font
import java.awt.Color
import ar.pablitar.vainilla.commons.math.Vector2D

class ScoreDisplayer(score: Int, playTime: Double) extends RichGameComponent[ScoreScene]{
    
    val textEnding = if(score < playTime * 50) "you should practice more..."
    else if(score < playTime * 100) "         pretty decent!"
    else if(score < playTime * 300) "           really good!"
    else "      wow... just wow..."
  
  
    val font = new Font("Helvetica", Font.BOLD, 45);
    this.setAppearance(new FadeableLabel(font, Color.WHITE, "   Your score was " + score + "\n" + textEnding, false));
    this.position = Vector2D(125,100)
    
}