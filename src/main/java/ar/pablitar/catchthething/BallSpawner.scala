package ar.pablitar.catchthething

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState
import scala.util.Random
import ar.pablitar.vainilla.commons.math.Vector2D

class BallSpawner extends GameComponent[CatchTheThingScene] {
  
  var cooldown = generateCooldown
  var timer = 0.0
  
  //Hasta 60 veces por segundo
  override def update(state: DeltaState) = {
    if(this.getScene.timer.isCounting){
      doUpdate(state);
    }
  }
  
  def doUpdate(state :DeltaState){
    if(timer >= cooldown) {
      spawnBall
      timer = 0
      cooldown = generateCooldown
    } else {
      timer += state.getDelta
    }    
  }
  
  def spawnBall = {
    var ball = new Ball
    ball.setPositionAndRefreshSpeed(Vector2D(Random.nextInt(2) * 800, 0))
    this.getScene.addComponent(ball)
  }

  def generateCooldown = {
    (Random.nextDouble() * 0.4) + 0.3
  }
}



