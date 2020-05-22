package client;

import java.util.Locale;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

// Klasse von Leon Xhinovci

public class Menu_Lobby_View extends Menu_Basic_View {

	Menu lobbyMenu2;

	public Menu_Lobby_View() {
		super();

		this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

	}

	protected void setTexts() {
		setTextsBasic();

	}

}
