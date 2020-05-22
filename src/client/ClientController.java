package client;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Optional;

import client.ServiceLocator_JC;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import server.Player;

//Klasse von Frank Mauchle
public class ClientController {

	private static ClientModel clientModel;
	private static ClientView clientView;
	private static ServiceLocator_JC sl;
	private static Translator_JC t;

	public ClientController(ClientModel clientModel, ClientView clientView) {
		ClientController.clientModel = clientModel;
		ClientController.clientView = clientView;
		sl = ServiceLocator_JC.getServiceLocator();
		t = ServiceLocator_JC.getServiceLocator().getTranslator();

		Login_View view = clientView.getLoginView();
		Lobby_View lobbyview = clientView.getLobbyView();
		Game_View gameview = clientView.getGameView();

		// LOGIN
			//Login Button ausgeblendet wenn TextFeld username, passwort leer
		view.getLoginButton().disableProperty().bind(
				view.getPasswordField().textProperty().isEmpty().or(view.getUsernameField().textProperty().isEmpty()));
			//Create(Register) Button ausgeblendet wenn TextFeld username, passwort leer
		view.getRegisterButton().disableProperty().bind(
				view.getUsernameField().textProperty().isEmpty().or(view.getPasswordField().textProperty().isEmpty()));
			//Login wird mit klick in ClientModel ausgelöst
		view.getLoginButton().setOnAction(e -> {
			clientModel.sayLogin(view.getUsernameField().getText(), view.getPasswordField().getText());
			view.getPasswordField().clear();
			view.getUsernameField().clear();
		});
			//sayRegister in ClientModel wird mit klick ausgelöst
		view.getRegisterButton().setOnAction(e -> {
			clientModel.sayRegister(view.getUsernameField().getText(), view.getPasswordField().getText());
			view.getPasswordField().clear();
			view.getUsernameField().clear();
		});
		

		// LOBBY
			//Wenn Chat leer ist kann der SendButton nicht geklickt werden
		lobbyview.getSendButton().disableProperty().bind(lobbyview.getTextChat().textProperty().isEmpty());
			//Wenn kein Spiel selektiert ist kann auch nicht der LeaveGameButton geklickt werden
		lobbyview.getLeaveGameButton().disableProperty().bind(lobbyview.selectedGameList.getSelectionModel().selectedItemProperty().isNull());
			//Wenn kein Game getippt wurde im TextFeld kann CreatGameButton nicht geklickt werden
		lobbyview.getCreateGameButton().disableProperty().bind(lobbyview.getTextField().textProperty().isEmpty());	
		//Spieler klickt Quit in Lobby und verlässt das komplette Spiel
		view.getControls().getQuitButton().setOnAction(c -> {
			clientView.getLobbyStage().hide();
			Platform.exit();
		});
			//Spiel erstellen mit klick auf CreateGame Button
		lobbyview.getCreateGameButton().setOnAction((e) -> {
			clientModel.sayCreateGame(lobbyview.getTextField().getText());
			lobbyview.getTextField().clear();
		});
			//Klick auf das rote krezu in Windows verlassen der Lobby
		clientView.getLobbyStage().setOnCloseRequest(c -> {
			clientModel.sayGoodBye("Lobby1");
		});
			//Beim klick auf Senden wird saynewChat im ClientModel aufgeruffen
		lobbyview.getSendButton().setOnAction(c -> {
			String message = lobbyview.getTextChat().getText();
			lobbyview.getTextChat().clear();

			clientModel.saynewChat(message);
		});
			//klick auf LeaveLobbyButton in Lobby
		lobbyview.getControls().getLeaveLobbyButton().setOnAction(c -> {
			clientModel.sayGoodBye("Lobby1");
			clientView.getLobbyStage().hide();
			clientView.switchView(1);
		});
			//klick auf Quit Button
		lobbyview.getControls().getQuitButton().setOnAction(c -> {
			clientModel.sayGoodBye("Lobby1");
			clientView.getLobbyStage().hide();
			Platform.exit();
		});
			//Bereits selektiertes Game wird aus SelectedGames gelöscht
		lobbyview.getLeaveGameButton().setOnAction(c -> {
			clientModel.sayGoodBye("Lobby2");
			ObservableList<String> selectedGame = FXCollections.observableArrayList();
			lobbyview.setSelectedGame(selectedGame);
		});
			//Game wird mit doppelklick in ListView geJoint und zu Selectedgames hinzugefügt
		clientView.getLobbyView().gamesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				try {
					if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
						String selectedItem = clientView.getLobbyView().gamesList.getSelectionModel().getSelectedItem()
								.toString();
						clientModel.sayJoinGame(selectedItem.substring(2));
						ObservableList<String> selectedGame = FXCollections.observableArrayList();
						selectedGame.add(selectedItem);
						lobbyview.setSelectedGame(selectedGame);
					}
				} catch (Exception b) {
					b.printStackTrace();
				}
			}
		});

		

		// GAME
		gameview.menuGame.gameMenuItem21.setOnAction(event -> sl.getConfiguration().setFrenchCards(false));
		gameview.menuGame.gameMenuItem22.setOnAction(event -> sl.getConfiguration().setFrenchCards(true));
		
			//Rotes Windows kreuz wird geklickt in Game
		clientView.getGameStage().setOnCloseRequest(c -> {
			clientModel.sayGoodBye("ExitGame");
		});
			//Leave Game Button wird in Game gedrückt
		gameview.getControls().getLeaveGameButton().setOnAction(c -> {
		    	clientModel.sayGoodBye("ExitGame"); 	
		    	clientView.switchView(2);
		});
			//Quit Button wird in Game gedrückt
		gameview.getControls().getQuitButton().setOnAction(c -> {
		    	clientModel.sayGoodBye("Lobby1");
		    	clientModel.sayGoodBye("ExitGame"); 	
			clientView.getGameStage().hide();
			Platform.exit();
		});
			// KARTEN PER KLICK SPIELEN
		gameview.getToggleButton(1).setOnAction((c) -> {

			String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame() + "";
			String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id() + "";
			ArrayList<String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
			String[] card = hand.get(0).split("\\$");
			clientModel.sayMove(gameID, playerID, card[0]);
		});

		gameview.getToggleButton(2).setOnAction((c) -> {
			String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame() + "";
			String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id() + "";
			ArrayList<String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
			String[] card = hand.get(1).split("\\$");
			clientModel.sayMove(gameID, playerID, card[0]);
		});

		gameview.getToggleButton(3).setOnAction((c) -> {
			String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame() + "";
			String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id() + "";
			ArrayList<String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
			String[] card = hand.get(2).split("\\$");
			clientModel.sayMove(gameID, playerID, card[0]);
		});

		gameview.getToggleButton(4).setOnAction((c) -> {
			String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame() + "";
			String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id() + "";
			ArrayList<String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
			String[] card = hand.get(3).split("\\$");
			clientModel.sayMove(gameID, playerID, card[0]);
		});

		gameview.getToggleButton(5).setOnAction((c) -> {
			String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame() + "";
			String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id() + "";
			ArrayList<String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
			String[] card = hand.get(4).split("\\$");
			clientModel.sayMove(gameID, playerID, card[0]);
		});

		gameview.getToggleButton(6).setOnAction((c) -> {
			String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame() + "";
			String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id() + "";
			ArrayList<String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
			String[] card = hand.get(5).split("\\$");
			clientModel.sayMove(gameID, playerID, card[0]);
		});

		gameview.getToggleButton(7).setOnAction((c) -> {
			String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame() + "";
			String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id() + "";
			ArrayList<String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
			String[] card = hand.get(6).split("\\$");
			clientModel.sayMove(gameID, playerID, card[0]);
		});

		gameview.getToggleButton(8).setOnAction((c) -> {
			String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame() + "";
			String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id() + "";
			ArrayList<String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
			String[] card = hand.get(7).split("\\$");
			clientModel.sayMove(gameID, playerID, card[0]);
		});

		gameview.getToggleButton(9).setOnAction((c) -> {
			String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame() + "";
			String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id() + "";
			ArrayList<String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
			String[] card = hand.get(8).split("\\$");
			clientModel.sayMove(gameID, playerID, card[0]);
		});
	}

//SwitchView (vom ClientModel aufgeruffen)
	public static void switchview(int view) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				clientView.switchView(view);
			}
		});

	}

//Login (Info Label)
	public static void updateLoginInfoLabel(String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				clientView.getLoginView().getRegisterLabel().setText(t.getString(msg));
			}
		});
	}

//Lobby (Spieler werden in LobbyView geladen die Online sind, durch Lobby Update das ClientModel empfaengt)
	public static void loadPlayersOnline(String[] players) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ObservableList<String> newplayers = FXCollections.observableArrayList(players);
				clientView.getLobbyView().setPlayerOn(newplayers);

			}
		});
	}

//Lobby (die bereits erstellten Games werdnen in ListView geladen)

	public static void loadGames(String[] games) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ObservableList<String> gameList = FXCollections.observableArrayList(games);
				clientView.getLobbyView().setGames(gameList);
			}
		});

	}

//Lobby (Empfangene Chat Nachricht durch ClientModel in LobbyView laden)
	public static void loadChat(String chat) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				String[] chatmsg = chat.split("\\|");
				clientView.getLobbyView().getChatList().clear();
				for (String s : chatmsg) {
					clientView.getLobbyView().getChatList().appendText(s + "\n");
				}
			}
		});
	}

//Game (laedt die Empfangenen Spieler mit Karten... in die GameView)
	public static void loadPlayersonGame(Player[] playersOnGame, String client, String trumpf, String teamscore) {
		Platform.runLater(new Runnable() {
			public void run() {
				// Anzeigen von OnMove und Game of sowie Karten dem aktuellen Client zuweisen
				ArrayList<String> cardList = new ArrayList<String>();
				for (Player p : playersOnGame) {
					if (p.getName().equals(client)) {
						cardList = p.getHandAsStrings();
						clientView.getGameView().setTitle(t.getString("game.windowTitle")+" "+ p.getName());
						if (p.getonMove() == true) {
							// Trumpf auswaehlen aber nur wenn 1. Runde
						    	clientView.getGameView().getOnTurn().setText(t.getString("game.lblAmZug"));
							clientView.getGameView().getOnTurn().setTextFill(Color.web("red"));
						} else {
						    clientView.getGameView().getOnTurn().setText(t.getString("game.lblNichtAmZug"));
							clientView.getGameView().getOnTurn().setTextFill(Color.web("black"));
						}
					}
				}

				// Anhand der cards aus der cardList
				JassImage img = new JassImage();
				String lang = "_CH";
				if (sl.getConfiguration().isFrenchCards()) {
					lang = "_FR";
				}
				String[] cards;

				clientView.getGameView().makeButtonsVisible(cardList.size());

				for (int i = 0; i < cardList.size(); i++) {
					cards = cardList.get(i).split("\\$");

					ImageView c = img.getCardImage(cards[0] + lang);
					c.setFitHeight(100);
					c.setFitWidth(80);

					clientView.getGameView().setGraphic(i + 1, c, Boolean.parseBoolean(cards[1]));
				}

				// Logik um die Reihenfolge am Tisch fÃ¼r alle richtig zu setzen
				if (playersOnGame[0].getName().equals(client)) {
					clientView.getGameView().p4_name.setText(playersOnGame[0].getName());
					clientView.getGameView().p1_name.setText(playersOnGame[1].getName());
					clientView.getGameView().p2_name.setText(playersOnGame[2].getName());
					clientView.getGameView().p3_name.setText(playersOnGame[3].getName());
					clientView.getGameView().p4name.setText(playersOnGame[0].getName());
					clientView.getGameView().p1name.setText(playersOnGame[1].getName());
					clientView.getGameView().p2name.setText(playersOnGame[2].getName());
					clientView.getGameView().p3name.setText(playersOnGame[3].getName());

					if (trumpf.equals("null")) {
						clientView.getGameView().setTrumpf(t.getString("game.lblTrumpfKein"));
					}
				}

				if (!trumpf.equals("null")) {
					clientView.getGameView().setTrumpf(t.getString("game.lblTrumpfJa"));
					clientView.getGameView().getTrumpf().setTextFill(Color.web("black"));
					clientView.getGameView().setTrumpfColor(trumpf);
				}

				if (playersOnGame[1].getName().equals(client)) {
					clientView.getGameView().p4_name.setText(playersOnGame[1].getName());
					clientView.getGameView().p1_name.setText(playersOnGame[2].getName());
					clientView.getGameView().p2_name.setText(playersOnGame[3].getName());
					clientView.getGameView().p3_name.setText(playersOnGame[0].getName());
					clientView.getGameView().p4name.setText(playersOnGame[1].getName());
					clientView.getGameView().p1name.setText(playersOnGame[2].getName());
					clientView.getGameView().p2name.setText(playersOnGame[3].getName());
					clientView.getGameView().p3name.setText(playersOnGame[0].getName());
					if (!trumpf.equals("null")) {
						clientView.getGameView().setTrumpf(t.getString("game.lblTrumpfJa"));
					}
				}
				if (playersOnGame[2].getName().equals(client)) {
					clientView.getGameView().p4_name.setText(playersOnGame[2].getName());
					clientView.getGameView().p1_name.setText(playersOnGame[3].getName());
					clientView.getGameView().p2_name.setText(playersOnGame[0].getName());
					clientView.getGameView().p3_name.setText(playersOnGame[1].getName());
					clientView.getGameView().p4name.setText(playersOnGame[1].getName());
					clientView.getGameView().p1name.setText(playersOnGame[2].getName());
					clientView.getGameView().p2name.setText(playersOnGame[3].getName());
					clientView.getGameView().p3name.setText(playersOnGame[0].getName());
					if (!trumpf.equals("null")) {
						clientView.getGameView().setTrumpf(t.getString("game.lblTrumpfJa"));
					}
				}
				if (playersOnGame[3].getName().equals(client)) {
					clientView.getGameView().p4_name.setText(playersOnGame[3].getName());
					clientView.getGameView().p1_name.setText(playersOnGame[0].getName());
					clientView.getGameView().p2_name.setText(playersOnGame[1].getName());
					clientView.getGameView().p3_name.setText(playersOnGame[2].getName());
					clientView.getGameView().p4name.setText(playersOnGame[1].getName());
					clientView.getGameView().p1name.setText(playersOnGame[2].getName());
					clientView.getGameView().p2name.setText(playersOnGame[3].getName());
					clientView.getGameView().p3name.setText(playersOnGame[0].getName());
					if (!trumpf.equals("null")) {
						clientView.getGameView().setTrumpf(t.getString("game.lblTrumpfJa"));
					}
				}

				// Team Punkte in der GameView setzen
				String[] teams = teamscore.split("\\$");
				int myteam = 8;
				for (int i = 0; i < teams.length; i++) {
					String spieler[] = teams[i].split("\\|");
					for (int c = 0; c < spieler.length; c++) {
						if (spieler[c].equals(client)) {
							myteam = i;
							clientView.getGameView().setPoints(spieler[2]);
						}
					}
				}
				// Wenn myTeam 0 dann ist gegner Team 1
				if (myteam == 0) {
					String spieler1[] = teams[1].split("\\|");
					clientView.getGameView().setOtherTeamPoints(spieler1[2]);
				} else {
					String spieler1[] = teams[0].split("\\|");
					clientView.getGameView().setOtherTeamPoints(spieler1[2]);
				}
			}

		});

	}

//Game (Karten die bereits gepspielt wurden auf dem Tisch in der GameView zeigen)
	public static void loadCardsOnTable(String cardsontable) {
		System.out.println("CLIENT CARDSONTABLE: " + cardsontable);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				JassImage img = new JassImage();
				String lang = "_CH";
				if (sl.getConfiguration().isFrenchCards()) {
					lang = "_FR";
				}
				String[] cards = cardsontable.split("\\$");

				for (int i = 0; i < cards.length; i++) {
					String[] card = cards[i].split("\\|");
					Image b = img.getCardImage(card[0] + lang).getImage();
					clientView.getGameView().placeCardtoTable(i + 1, b);
				}
			}
		});
	}

//Game (Tisch leeren in der GameView)
	public static void emptyTable() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				clientView.getGameView().removeCardsonTable();

			}
		});

	}

//Game (Gewinner anzeigen nach 1000 Punkten durch Message die in ClientModel empfangen wurde)
	public static void showWinnerTeam(String winnerteamid) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Spiel vorbei!");
				alert.setContentText("Winnerteam: " + winnerteamid);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					clientModel.sayExitGame();
					clientView.switchView(2);
					alert.close();
				} 
			}
		});
	}

//Game (Gewinner nach gespieltem Stapel anzeigen)
	public static void showStapelWinner(String winnerTeamID, String client) {
		
		
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Stapel fertig!");
				alert.setContentText("Stapelsieger: Gewonnen hat Team " + winnerTeamID + " ! " +" Weiterspielen? ");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					clientModel.sayNextStaple();

					alert.close();

				} else if (result.get() == ButtonType.CANCEL) {
					clientModel.sayExitGame();
					clientView.switchView(2);

					alert.close();
				}
			}

		});

	}

//Game (Falls Spieler spiel abbricht Alert)
	public static void showDialog() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Dialog<Object> dialog = new Dialog<>();

				dialog.setTitle("Spiel wird beendet");
				dialog.setContentText("Ein Spieler hat das Spiel verlassen. Spiel wird beendet in 5 Sekunden");
				dialog.show();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clientView.switchView(2);
				dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
				dialog.hide();

			}
		});

	}
	
	

	public static void changeLanguage() {
	    t = ServiceLocator_JC.getServiceLocator().getTranslator();
	   clientView.changeLanguage();
	    
		}

}
