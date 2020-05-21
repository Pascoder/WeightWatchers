package client;

import java.util.Locale;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

//Leon

public class Menu_Game_View extends Menu_Basic_View {

    Menu gameMenu2;
    Menu gameMenu3;

    ToggleGroup tg1;

    // MenuItem menu1
    RadioMenuItem gameMenuItem21, gameMenuItem22;

    public Menu_Game_View() {
	super();
	gameMenu3 = new Menu();
	gameMenu2 = new Menu();

	gameMenuItem21 = new RadioMenuItem();
	gameMenuItem22 = new RadioMenuItem();

	gameMenu3.getItems().addAll(gameMenuItem21, gameMenuItem22);

	tg1 = new ToggleGroup();
	tg1.getToggles().addAll(gameMenuItem21, gameMenuItem22);

	if (sl.getConfiguration().isFrenchCards())
	    gameMenuItem22.setSelected(true);
	else
	    gameMenuItem21.setSelected(true);

	this.getMenus().addAll(gameMenu2, gameMenu3);

	this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }

    protected void setTexts() {
	setTextsBasic();

	this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
	this.gameMenu3.setText(t.getString("program.menu.file"));
	this.gameMenuItem21.setText(t.getString("gameMenu.CH"));
	this.gameMenuItem22.setText(t.getString("gameMenu.FR"));

    }

}
