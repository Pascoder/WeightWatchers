package server;

import java.util.ArrayList;

public class Team {

private int team_id = 0;
private ArrayList <Player> members = new ArrayList<>();
private int points;
private int wins;


//Konstruktior ist zugleich Create_Team();
public Team (int team_id,Player player1, Player player2) {
	this.team_id = team_id;
	if(members.size()==0) {
		members.add(player1);
		members.add(player2);
		this.points = 0;
		this.wins = 0;
	}
}

public int getTeam_id() {
	return this.team_id;
}
public ArrayList <Player> getTeamMembers(){
	return this.members;
}
public int getTeamPoints() {
	return this.points;
}
public int getTeamWins() {
	return this.wins;
}
public void setTeamPoints(int points) {
	this.points = points;
}
public void setTeamWins(int wins) {
	this.wins = wins;
}
//Falls Team neu zusammengestellt werden soll können TeamMembers entfernt werden
public void removeTeamMember(int person_id) {
for(int i = 0; i<members.size();i++) {
	if(members.get(i).getPlayer_id() == person_id) {
		members.remove(i);
	}
}
}

//Falls bereits ein TeamMember entfernt wurde könnnen neue hinzugefügt werden
public void addTeamMember(Player p1) {
	if(members.size()<2) {
		members.add(p1);
	}
}

public String toString() {
	String members = "";
	for(int i = 0; i< this.members.size();i++) {
		members+="\n"+this.members.get(i);
	}
	return "TEAM_ID: "+this.team_id+"\n"+"POINTS: "+this.points+"\n"+"WINS: "+this.wins+"\n"+"TEAMMEMBERS: "+members;
}
}
