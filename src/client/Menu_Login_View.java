package client;

import java.util.Locale;

import client.Translator_JC;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.ServiceLocator;

// Klasse von Leon Xhinovci

public class Menu_Login_View extends Menu_Basic_View {

	public Menu_Login_View() {
		super();

		setTextsBasic();

	}

	protected void setTexts() {
		setTextsBasic();
	}

}
