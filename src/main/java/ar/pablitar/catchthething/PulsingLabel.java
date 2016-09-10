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

public class PulsingLabel extends Label {

	public float pulseTime = 2;
	public float pulseTimer = 0;
	private boolean firstPass = true;
	private boolean pulse;

	public PulsingLabel(Font font, Color color, String text) {
		super(font, color, text);
	}

	public PulsingLabel(Font font, Color color, String text, boolean shouldPulse) {
		super(font, color, text);
		this.pulse = shouldPulse;
	}

	public PulsingLabel(Font font, Color color, String... textLines) {
		super(font, color, textLines);
	}

	public PulsingLabel(Font font, Color color, List<String> textLines) {
		super(font, color, textLines);
	}

	@Override
	public void update(double delta) {
		if (pulse) {
			doUpdate(delta);
		}
	}

	// Lo multiplico por 0.75 para que no desaparezca completamente el texto,
	// sino directamente desaparece y vuelve

	private void doUpdate(double delta) {
		if (firstPass && pulseTimer < (pulseTime * 0.75) - delta) {
			pulseTimer += delta * 0.75;
		} else if (!firstPass && pulseTimer > 0 + delta) {
			pulseTimer -= delta * 0.75;
		} else {
			firstPass = !firstPass;
		}
	}

	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
		if (pulse) {
			Composite before = graphics.getComposite();
			graphics.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, this.alpha()));
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
					RenderingHints.VALUE_ANTIALIAS_ON);
			super.render(component, graphics);
			graphics.setComposite(before);
		} else {
			super.render(component, graphics);			
		}
	}

	private float alpha() {
		float res = 1;
		if (pulseTimer <= pulseTime) {
			res = 1 - (pulseTimer / pulseTime);
		} else {
			res = 1;
		}
		return res;
	}

	public void setPulse(boolean shouldPulse) {
		this.pulse = shouldPulse;
	}
}
