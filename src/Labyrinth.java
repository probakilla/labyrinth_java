import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Labyrinth extends Application {

	static Controller controller;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			controller = Controller.getInstance();
			controller.start(primaryStage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void stop() {
		System.exit(0);
	}

}
