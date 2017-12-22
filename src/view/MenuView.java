package view;

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

/**
 * The class used for the menu before the start of the game.
 *
 * @author Java Group
 */
public class MenuView
{

    private Stage _stage;
    private Scene _scene;
    private final Pane _pane;
    private final Label _menuHeader, _instructions;
    private final Button _play, _cancel, _help;
    private final VBox _vbox;
    private final View.OnPlayListener _onplay;

    /**
     * Create a window for the menu before the game.
     *
     * @param onplay The {@link view.View View} where the menu is used.
     */
    public MenuView(View.OnPlayListener onplay)
    {
        _pane = new Pane();
        _play = new Button();
        _cancel = new Button();
        _help = new Button();
        _vbox = new VBox(3);
        _menuHeader = new Label();
        _instructions = new Label();
        _onplay = onplay;

    }

    /**
     * Method used to start the menu.
     *
     * @param stage The {@link javafx.stage.Stage Stage} where the menu will be
     * displayed.
     */
    public void start(Stage stage)
    {
        _stage = stage;
        drawMenu();
    }
    /**
     * Draw the menu when we launch the game.
     */
    private void drawMenu()
    {
        _scene = new Scene(_pane);
        _scene.setFill(View.SCENE_COLOR);
        _stage.setScene(_scene);

        _menuHeader.setText("Labyrinthe best game ever");
        _menuHeader.setFont(new Font("Serif", 50));
        _menuHeader.setMaxWidth(Double.MAX_VALUE);
        _menuHeader.setAlignment(Pos.CENTER);
        _menuHeader.setTextFill(Color.web("#F44336"));
        _menuHeader.setPadding(new Insets(20, 20, 20, 20));

        _play.setText("Jouer");
        _play.setFont(new Font("Serif", 50));
        _play.setMaxWidth(Double.MAX_VALUE);

        _cancel.setText("Quitter");
        _cancel.setFont(new Font("Serif", 50));
        _cancel.setMaxWidth(Double.MAX_VALUE);

        _help.setText("Aidez moi svp");
        _help.setFont(new Font("Serif", 50));
        _help.setMaxWidth(Double.MAX_VALUE);
        _instructions.setText("Voici les commandes disponibles :\n"
            + "    - Touches directionnelles : Déplacer le personnage.\n" + "    - Q ou ESCP : Quitter le jeu.\n"
            + "Le but du jeu est d'arriver le plus loin possible dans le jeu\net on recommence à zéros si on perd.\nPour finir le niveau en cours il faut arriver à la sortie sans se faire\ntoucher"
                + " trois fois par les ennemies, une fois le niveau fini on gagne\ndes points en fonctions du nombre de vie restante on gagne\négalement des points en ramassant divers bonbons dispersé\n"
                + "dans le labyrinthe. Le but du jeu étant d'avoir le score le plus élevé\nLes portes bleu sont des portes fermées qu'il faut ouvrir avec les\ninterrupteurs verts et "
                + "les interrupteurs bleus les refermes.");
        _instructions.setFont(new Font("Serif", 20));
        _instructions.setMaxWidth(Double.MAX_VALUE);
        _instructions.setAlignment(Pos.CENTER);
        _instructions.setPadding(new Insets(20, 20, 20, 20));
        _vbox.getChildren().addAll(_menuHeader, _play, _cancel, _help);

        _pane.getChildren().add(_vbox);

        setListeners();

        _stage.show();
    }
    /**
     * Set the menu listener before the game started.
     */
    private void setListeners()
    {
        _play.setOnAction(event ->
        {
            _onplay.play();
        });

        _cancel.setOnAction(event ->
        {
            System.exit(1);
        });

        _help.setOnAction(event ->
        {
            _vbox.getChildren().add(_instructions);
            _vbox.getChildren().remove(_help);
            _stage.setHeight(800);
        });
    }

}
