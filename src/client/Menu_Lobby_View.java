package client;

import java.util.Locale;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

//Leon

public class Menu_Lobby_View extends Menu_Basic_View {

    Menu lobbyMenu2;
    // Menu lobbyMenu3;

    // MenuItem menu2
    // MenuItem lobbyMenuItem21 = new MenuItem();

    public Menu_Lobby_View() {
	super();
	// lobbyMenu2 = new Menu();

	// lobbyMenu2.getItems().add(lobbyMenuItem21);

	// this.getMenus().add(lobbyMenu2);

	this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }

    protected void setTexts() {
	setTextsBasic();

	// this.lobbyMenu2.setText(t.getString("game.lblMenuBar"));

    }

}
