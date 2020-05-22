package server;

import java.util.ArrayList;

//Klasse von Frank Mauchle

public class Team {

    private int team_id = 0;
    private int gameID = 0;
    private ArrayList<Player> members = new ArrayList<>();
    private int points;
    private int totalPoints = 0;
    private int wins;

//Konstruktior ist zugleich Create_Team();
    public Team(int team_id, int gameID) {
	this.team_id = team_id;
	this.gameID = gameID;
	// if(members.size()==0) {
	// members.add(player1);
	// members.add(player2);
	this.points = 0;
	this.wins = 0;

	// }
    }

    public void nextStich() {

	this.points = 0;
    }

    public int getTeam_id() {
	return this.team_id;
    }

    public ArrayList<Player> getTeamMembers() {
	return this.members;
    }

    public int getTeamPoints() {
	return this.totalPoints;
    }

    public int getTeamWins() {
	return this.wins;
    }

    public void setTeamPoints(int points) {
	System.out.println("TEAMPOINTS: " + points);
	this.points = points;
	this.totalPoints += points;
    }

    public void setTeamWins(int wins) {
	this.wins = wins;
    }

//Falls Team neu zusammengestellt werden soll k�nnen TeamMembers entfernt werden
    public void removeTeamMember(int person_id) {
	for (int i = 0; i < members.size(); i++) {
	    if (members.get(i).getPlayer_id() == person_id) {
		members.remove(i);
	    }
	}
    }

//Falls bereits ein TeamMember entfernt wurde k�nnnen neue hinzugef�gt werden
    public void addTeamMember(Player p1) {
	if (members.size() < 2) {
	    members.add(p1);
	}
    }

    public String getTeamNames() {
	return members.get(0).getName() + " & " + members.get(1).getName();
    }

    public String toString() {
	String members = "";
	for (int i = 0; i < this.members.size(); i++) {
	    members += "\n" + this.members.get(i);
	}
	return "TEAM_ID: " + this.gameID + "_" + this.team_id + "\n" + "POINTS: " + this.points + "\n" + "WINS: "
		+ this.wins + "\n" + "TEAMMEMBERS: " + members;
    }
}
