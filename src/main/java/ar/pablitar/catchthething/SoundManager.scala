package ar.pablitar.catchthething

import tinysound.TinySound
import tinysound.Music
import tinysound.Sound
import scala.util.Random

// Pequeño Façade para utilizar la libreria de TinySound en este proyecto particular

class SoundManager {
  TinySound.init()
  
  var comboSound1 = TinySound.loadSound("/music/combo.wav");
  var comboSound2 = TinySound.loadSound("/music/combo2.wav");
  
  var combos : Array[Sound] = new Array[Sound](2)
  
  combos(0) = comboSound1;
  combos(1) = comboSound2;
  
  var currentSong: Option[Music] = None;

  def shutdown(): Unit = {
    TinySound.shutdown();
  }

  def init(): Unit = {
    TinySound.init()
  }

  def playIntroMusicOnLoop(): Unit = {
    stopAndUnloadMusic()
    currentSong = Some(TinySound.loadMusic(getClass().getResource("/music/dream.wav")))
    playMusic()
  }

  def playGameplayMusicOnLoop(): Unit = {
    stopAndUnloadMusic()
    currentSong = Some(TinySound.loadMusic(getClass().getResource("/music/happy.wav")))
    playMusic()
  }
  
  def playMusic() : Unit =  {
    if(currentSong != None) {
      currentSong.get.play(true);
    }
  }
  
  def stopMusic() : Unit = {
    if(currentSong != None){
      currentSong.get.stop();
    }
  }
  
  def stopAndUnloadMusic() : Unit = {
    if(currentSong != None){
      currentSong.get.stop();
      currentSong.get.unload();
      currentSong = None;
    }    
  }
  
  def isPlayingMusic() : Boolean = {
    return currentSong != None && currentSong.get.playing();
  }
  
  def playComboSound() : Unit = {
    combos(Random.nextInt(2)).play();
  }
}