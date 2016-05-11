package net.leseonline.bbstat.stat;

/**
 * Created by mvlese on 2/20/2016.
 */
public class BasketballStatistics {

    public BasketballStatistics(int id, int playerId) {
        this.id = id;
        this.playerId = playerId;
        twoPoints = new Statistic();
        twoPointAttempts = new Statistic();
        threePoints = new Statistic();
        threePointAttempts = new Statistic();
        freeThrows = new Statistic();
        freeThrowAttempts = new Statistic();
        assists = new Statistic();
        fouls = new Statistic();
        rebounds = new Statistic();
    }

    public int getId() { return id; }

    public int getPlayerId() { return playerId; }

    public Statistic getTwoPoints() {
        return twoPoints;
    }

    public Statistic getTwoPointAttempts() {
        return twoPointAttempts;
    }

    public Statistic getThreePoints() {
        return threePoints;
    }

    public Statistic getThreePointAttempts() {
        return threePointAttempts;
    }

    public Statistic getFreeThrows() {
        return freeThrows;
    }

    public Statistic getFreeThrowAttempts() {
        return freeThrowAttempts;
    }

    public Statistic getAssists() {
        return assists;
    }

    public Statistic getFouls() {
        return fouls;
    }

    public Statistic getRebounds() {
        return rebounds;
    }

    private int id;
    private int playerId;
    private Statistic twoPoints;
    private Statistic twoPointAttempts;
    private Statistic threePoints;
    private Statistic threePointAttempts;
    private Statistic freeThrows;
    private Statistic freeThrowAttempts;
    private Statistic assists;
    private Statistic fouls;
    private Statistic rebounds;

}
