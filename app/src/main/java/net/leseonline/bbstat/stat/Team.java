package net.leseonline.bbstat.stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mvlese on 2/21/2016.
 */
public class Team {


    public Team(long id, String name) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    private long id;
    private List<Player> players;
    private String name;
}
