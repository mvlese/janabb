package net.leseonline.bbstat.stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mvlese on 2/21/2016.
 */
public class Team {


    public Team() {
        setName("");
        players = new ArrayList<Player>();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    private List<Player> players;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
