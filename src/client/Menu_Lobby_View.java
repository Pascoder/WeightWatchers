package client;

import java.util.Locale;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;



	//Leon


public class Menu_Lobby_View extends Menu_Basic_View {
    
    
    Menu menu1;
    Menu menu2;

   
    
    // MenuItem menu1
    MenuItem menuItem11 = new MenuItem(t.getString("lobby.menuBarVersion"));
    
    public Menu_Lobby_View() {
	super();
	
	menu1 = new Menu(t.getString("lobby.menuBarInfo"));

	menu1.getItems().add(menuItem11);

	this.getMenus().add(menu1);

	this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }


}
