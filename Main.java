import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = new Canvas(1920/4,1080/4);
		Scene scene = new Scene(new StackPane(canvas),canvas.getWidth(),canvas.getHeight(),true,SceneAntialiasing.BALANCED);
		canvas.setScaleX(4);
		canvas.setScaleY(4);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);

		Voronoi voronoi = new Voronoi(canvas.getGraphicsContext2D());

		scene.setOnMouseClicked(event -> new Thread(() -> {
			Platform.runLater(() -> {
				voronoi.addNode(event.getX(), event.getY(), Color.color(Math.random(), Math.random(), Math.random()).interpolate(Color.WHITE, 0.5));
				voronoi.draw();
			});
		}).start());

		scene.setOnKeyPressed(event -> new Thread(() -> {
			Platform.runLater(() -> {
				switch (event.getCode()) {
					case SPACE:
						voronoi.clear();
						voronoi.draw();
						break;
					case NUMPAD0:
						voronoi.distexp = 0;
						voronoi.draw();
						break;
					case NUMPAD1:
						voronoi.distexp = 1;
						voronoi.draw();
						break;
					case NUMPAD2:
						voronoi.distexp = 2;
						voronoi.draw();
						break;
					case NUMPAD3:
						voronoi.distexp = 3;
						voronoi.draw();
						break;
					case NUMPAD4:
						voronoi.distexp = 4;
						voronoi.draw();
						break;
					case NUMPAD5:
						voronoi.distexp = 5;
						voronoi.draw();
						break;
					case NUMPAD6:
						voronoi.distexp = 6;
						voronoi.draw();
						break;
					case NUMPAD7:
						voronoi.distexp = 7;
						voronoi.draw();
						break;
					case NUMPAD8:
						voronoi.distexp = 8;
						voronoi.draw();
						break;
					case NUMPAD9:
						voronoi.distexp = 9;
						voronoi.draw();
						break;
					case MULTIPLY:
						voronoi.distexp = 20;
						voronoi.draw();
						break;
					case ADD:
						voronoi.distexp += 0.25;
						voronoi.draw();
						break;
					case SUBTRACT:
						voronoi.distexp -= 0.25;
						voronoi.draw();
						break;
				}
			});
		}).start());

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
