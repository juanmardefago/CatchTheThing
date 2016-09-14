package ar.pablitar.catchthething

import tinysound.TinySound
import tinysound.Music

// Pequeño Façade para utilizar la libreria de TinySound en este proyecto

class SoundManager {
  TinySound.init()
  
  var comboSound = TinySound.loadSound("/music/combo.wav");

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
      currentSong.get.play(true)
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
      currentSong = None
    }    
  }
  
  def isPlayingMusic() : Boolean = {
    return currentSong != None && currentSong.get.playing()
  }
  
  def playComboSound() : Unit = {
    comboSound.play()
  }
}