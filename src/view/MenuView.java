package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuView {

	private Stage stage;
	private Scene scene;
	private final Pane pane;
	private Label menuHeader, instructions;
	private Button play, cancel, help;
	private VBox vbox;
	private OnPlayListener onplay;

	MenuView(OnPlayListener onplay) {
		pane = new Pane();
		play = new Button();
		cancel = new Button();
		help = new Button();
		vbox = new VBox(3);
		menuHeader = new Label();
		instructions = new Label();
		this.onplay = onplay;

	}

	public void start(Stage stage) {
		this.stage = stage;
		drawMenu();
	}

	private void drawMenu() {
		scene = new Scene(pane);
		scene.setFill(View.SCENE_COLOR);
		stage.setScene(scene);

		menuHeader.setText("Labyrinthe best game ever");
		menuHeader.setFont(new Font("Serif", 50));
		menuHeader.setMaxWidth(Double.MAX_VALUE);
		menuHeader.setAlignment(Pos.CENTER);
		menuHeader.setTextFill(Color.web("#F44336"));
		menuHeader.setPadding(new Insets(20, 20, 20, 20));

		play.setText("Jouer");
		play.setFont(new Font("Serif", 50));
		play.setMaxWidth(Double.MAX_VALUE);

		cancel.setText("Quitter");
		cancel.setFont(new Font("Serif", 50));
		cancel.setMaxWidth(Double.MAX_VALUE);

		help.setText("Aidez moi svp");
		help.setFont(new Font("Serif", 50));
		help.setMaxWidth(Double.MAX_VALUE);

		instructions.setText("Voici les commandes disponibles :\n"
				+ "    - Touches directionnelles : Déplacer le personnage.\n" + "    - Q ou ESCP : Quitter le jeu.");
		instructions.setFont(new Font("Serif", 20));
		instructions.setMaxWidth(Double.MAX_VALUE);
		instructions.setAlignment(Pos.CENTER);
		instructions.setPadding(new Insets(20, 20, 20, 20));

		vbox.getChildren().addAll(menuHeader, play, cancel, help);

		pane.getChildren().add(vbox);

		setListeners();

		stage.show();
	}

	private void setListeners() {
		play.setOnAction(event -> {
			onplay.play();
		});

		cancel.setOnAction(event -> {
			System.exit(1);
		});

		help.setOnAction(event -> {
			vbox.getChildren().add(instructions);
			vbox.getChildren().remove(help);
			stage.setHeight(500);
		});
	}

	interface OnPlayListener {
		void play();
	}

}
