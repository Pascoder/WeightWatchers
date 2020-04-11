package server;

import java.util.ArrayList;

public class Team {
	
private ArrayList <Player> members = new ArrayList<>();
private int points = 0;
private int wins = 0;

public void createTeam(Player player1, Player player2) {

	if(members.size()==0) {
		members.add(player1);
		members.add(player2);
	}
	
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
public String toString() {
	String members = "";
	for(int i = 0; i< this.members.size();i++) {
		members+="\n"+this.members.get(i);
	}
	return "Members: "+members+"\n"+" Points: "+this.points+"\n"+" Wins: "+"\n"+this.wins;
}
}
