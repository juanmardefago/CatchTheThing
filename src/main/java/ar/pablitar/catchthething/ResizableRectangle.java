package ar.pablitar.catchthething;

import java.awt.Color;
import java.awt.Graphics2D;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Shape;

public class ResizableRectangle extends Shape<Rectangle> {

	private double width, height;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public ResizableRectangle(Color color, double width, double height) {
		this.color = color;
		this.height = height;
		this.width = width;
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ResizableRectangle copy() {
		return new ResizableRectangle(this.color, this.height, this.width);
	}
	
	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public void resizeWidth(double width){
		this.width = width;
	}
	
	public void resizeHeight(double height){
		this.height = height;
	}
	
	public void changeColor(Color color){
		this.color = color;
	}
	
	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(double delta) {
	}

	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
		graphics.setColor(this.color);
		graphics.fillRect((int) (component.getX() + getOffsetX()), (int) (component.getY() + getOffsetY()), (int) this.width,
				(int) this.height);
	}

}
