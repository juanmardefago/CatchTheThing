package ar.pablitar.catchthething;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class FadeableLabel extends Label{
	
	public float fadeDelay = 1;
	public float fadeDelayTimer = 0;
	public float fadeTime = 1;
	public float fadeTimer = 0;
	public boolean fade = true;
	
	public FadeableLabel(Font font, Color color, String text) {
		super(font, color, text);
	}
	
	public FadeableLabel(Font font, Color color, String text, boolean shouldFade) {
		super(font, color, text);
		this.fade = shouldFade;
	}

	public FadeableLabel(Font font, Color color, String... textLines) {
		super(font, color, textLines);
	}

	public FadeableLabel(Font font, Color color, List<String> textLines) {
		super(font, color, textLines);
	}

	@Override
	public void update(double delta) {
		if(fade){
			doUpdate(delta);
		}
	}
	
	public void doUpdate(double delta) {
		if(fadeDelayTimer >= fadeDelay){
			if(fadeTimer < fadeTime){
				fadeTimer += delta;
			}
		} else {
			fadeDelayTimer += delta;
		}		
	}
	
	
	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
		Composite before = graphics.getComposite();
		graphics.setComposite(AlphaComposite.getInstance(
	            AlphaComposite.SRC_OVER, this.alpha()));
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
			        RenderingHints.VALUE_ANTIALIAS_ON);
		super.render(component, graphics);
		graphics.setComposite(before);
	}
	
	private float alpha(){
		float res = 1;
		if(fadeTimer < fadeTime){
			res = 1 - (fadeTimer/fadeTime);
		} else {
			res = 1;
		}
		return res;
	}
}
