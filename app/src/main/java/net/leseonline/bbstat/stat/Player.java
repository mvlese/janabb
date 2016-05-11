package net.leseonline.bbstat.stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mvlese on 2/21/2016.
 */
public class Player {

    public Player() {
        id = 0;
        teamIds = new ArrayList<Long>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return id == player.id;
    }

    public void addTeamId(long teamId) {
        if (!teamIds.contains(teamId)) {
            teamIds.add(teamId);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Long> teamIds) {
        this.teamIds = teamIds;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private long id;
    private String firstName;
    private String lastName;
    private List<Long> teamIds;
}
