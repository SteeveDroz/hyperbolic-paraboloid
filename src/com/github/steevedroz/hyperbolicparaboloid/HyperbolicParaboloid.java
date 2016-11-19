package com.github.steevedroz.hyperbolicparaboloid;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class HyperbolicParaboloid extends Pane {
    private int vertices;
    private double step;
    private Paint backgroundColor;
    private Paint foregroundColor;

    public HyperbolicParaboloid(int vertices, double step) {
	this.vertices = vertices;
	this.step = step;
	this.backgroundColor = Color.WHITE;
	this.foregroundColor = Color.BLACK;
	widthProperty().addListener((o, oldVal, newVal) -> {
	    draw();
	});
	heightProperty().addListener((o, oldVal, newVal) -> {
	    draw();
	});
    }

    public void draw() {
	getChildren().clear();
	Rectangle rectangle = new Rectangle(0, 0, getWidth(), getHeight());
	rectangle.setFill(backgroundColor);
	getChildren().add(rectangle);
	for (int i = 0; i < vertices; i++) {
	    Point2D start = getCoordinates(i);
	    Point2D end = getCoordinates(i * step);
	    Line line = new Line(start.getX() + getCenter().getX(), start.getY() + getCenter().getY(),
		    end.getX() + getCenter().getX(), end.getY() + getCenter().getY());
	    line.setStroke(foregroundColor);
	    getChildren().add(line);
	}
    }

    public int getVertices() {
	return vertices;
    }

    public double getStep() {
	return step;
    }

    public Paint getBackgroundColor() {
	return backgroundColor;
    }

    public Paint getForegroundColor() {
	return foregroundColor;
    }

    public double getSize() {
	return Math.min(getWidth(), getHeight());
    }

    public void setVertices(int vertices) {
	this.vertices = vertices;
	draw();
    }

    public void setStep(double step) {
	this.step = step;
	draw();
    }

    public void setBackgroundColor(Paint backgroundColor) {
	this.backgroundColor = backgroundColor;
	draw();
    }

    public void setForegroundColor(Paint foregroundColor) {
	this.foregroundColor = foregroundColor;
	draw();
    }

    private Point2D getCoordinates(double location) {
	double angle = 2 * Math.PI * location / vertices;
	return new Point2D(Math.sin(angle) * getSize() / 2, -Math.cos(angle) * getSize() / 2);
    }

    private Point2D getCenter() {
	return new Point2D(getWidth() / 2, getHeight() / 2);
    }
}
