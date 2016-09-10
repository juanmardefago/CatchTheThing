package ar.pablitar.catchthething;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class PulsingLabel extends Label {

	public float pulseTime = 1;
	public float pulseTimer = 0;
	private boolean firstPass = true;
	private boolean pulse;

	public PulsingLabel(Font font, Color color, String text) {
		super(font, color, text);
	}

	public PulsingLabel(Font font, Color color, String text, boolean shouldPulse) {
		super(font, color, text);
		pulse = shouldPulse;
	}

	@Override
	public void update(double delta) {
		if (pulse) {
			doUpdate(delta);
		}
	}

	public void doUpdate(double delta) {
		if (firstPass && pulseTimer < pulseTime) {
			pulseTimer += delta;
		} else if(!firstPass && pulseTimer > 0){
			pulseTimer -= delta;
		} else {
			firstPass = false;
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

	private float alpha() {
		float res = 1;
		if (pulseTimer < pulseTime) {
			res = 1 - (pulseTimer / pulseTime);
		} else {
			res = 1;
		}
		return res;
	}

}
