package com.github.steevedroz.hyperbolicparaboloid;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
	try {
	    BorderPane root = new BorderPane();
	    Scene scene = new Scene(root, 800, 800);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    HyperbolicParaboloid hp = new HyperbolicParaboloid(500, 0);
	    hp.prefWidthProperty().bind(root.widthProperty());
	    hp.prefHeightProperty().bind(root.heightProperty());
	    root.setCenter(hp);
	    hp.autosize();
	    root.widthProperty().addListener((o, oldVal, newVal) -> {
		redrawColors(hp);
	    });
	    root.heightProperty().addListener((o, oldVal, newVal) -> {
		redrawColors(hp);
	    });
	    hp.setOnMouseClicked((event) -> {
		redrawColors(hp);
	    });
	    redrawColors(hp);
	    hp.draw();
	    AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long now) {
		    hp.setStep(hp.getStep() + 0.001);
		    hp.draw();
		}
	    };
	    timer.start();
	    primaryStage.setScene(scene);
	    primaryStage.show();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	launch(args);
    }

    private void redrawColors(HyperbolicParaboloid hp) {
	// hp.setBackgroundColor(new RadialGradient(0, 0, hp.getWidth() / 2,
	// hp.getHeight() / 2, hp.getSize() / 2, false,
	// CycleMethod.NO_CYCLE, new Stop(0, Color.YELLOW), new Stop(0.4, new
	// Color(1, 0.25, 0.25, 1)),
	// new Stop(1, Color.BLACK)));
	hp.setBackgroundColor(Color.BLACK);
	// hp.setForegroundColor(new RadialGradient(0, 0, hp.getWidth() / 2,
	// hp.getHeight() / 2, hp.getSize() / 2, false,
	// CycleMethod.NO_CYCLE, new Stop(0, Color.BLACK), new Stop(0.4,
	// Color.YELLOW),
	// new Stop(1, new Color(0, 0, 0, 0))));
	hp.setForegroundColor(new RadialGradient(0, 0, hp.getWidth() / 2, hp.getHeight() / 2, hp.getSize() / 2, false,
		CycleMethod.NO_CYCLE, new Stop(0, Color.CORNFLOWERBLUE), new Stop(1, Color.MAROON)));
    }
}
