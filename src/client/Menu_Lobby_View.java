package client;

import java.util.Locale;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;




public class Menu_Lobby_View extends Menu_Basic_View {
    
    
    Menu menu1;
    Menu menu2;


    // MenuItems menu2
    MenuItem menuItem11 = new MenuItem("item11");
    MenuItem menuItem12 = new MenuItem("item12");
    MenuItem menuItem13 = new MenuItem("item13");

    public Menu_Lobby_View() {
	super();
	
	menu1 = new Menu("Edit");
	menu2 = new Menu("Help");
	


	menu1.getItems().addAll(menuItem11, menuItem12, menuItem13);

	this.getMenus().addAll(menu1, menu2);

	this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }


}
