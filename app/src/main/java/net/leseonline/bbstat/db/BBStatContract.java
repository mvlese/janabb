package net.leseonline.bbstat.db;

import android.provider.BaseColumns;

/**
 * Created by mlese on 5/10/2016.
 */
public class BBStatContract {

    public BBStatContract() {}

    /* Inner class that defines the table contents */
    public static abstract class TeamEntry implements BaseColumns {
        public static final String TABLE_NAME = "teams";
        public static final String COLUMN_NAME_LOCATION_ID = "teamid";
        public static final String COLUMN_NAME_TEAM_NAME = "name";
    }

    public static abstract class PlayerEntry implements BaseColumns {
        public static final String TABLE_NAME = "players";
        public static final String COLUMN_NAME_PLAYER_ID = "playerid";
        public static final String COLUMN_NAME_PLAYER_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_PLAYER_LAST_NAME = "last_name";
    }

    public static abstract class TeamPlayerEntry implements BaseColumns {
        public static final String TABLE_NAME = "teamsplayers";
        public static final String COLUMN_NAME_TEAM_PLAYER_ID = "teamplayerid";
        public static final String COLUMN_NAME_TEAM_ID = "teamid";
        public static final String COLUMN_NAME_PLAYER_ID = "playerid";
    }

    public static abstract class GameEntry implements BaseColumns {
        public static final String TABLE_NAME = "games";
        public static final String COLUMN_NAME_GAME_ID = "gameid";
        public static final String COLUMN_NAME_TEAM1_ID = "team1id";
        public static final String COLUMN_NAME_TEAM2_ID = "team2id";
        public static final String COLUMN_NAME_TEAM1_SCORE = "team1_score";
        public static final String COLUMN_NAME_TEAM2_SCORE = "team2_score";
        public static final String COLUMN_NAME_GAME_CREATED = "created";
    }

    public static abstract class BasketballStatEntry implements BaseColumns {
        public static final String TABLE_NAME = "stats";
        public static final String COLUMN_NAME_STAT_ID = "statid";
        public static final String COLUMN_NAME_PLAYER_ID = "playerid";
        public static final String COLUMN_NAME_TWO_POINTS = "twopoints";
        public static final String COLUMN_NAME_TWO_POINT_ATTEMPTS = "twopointattempts";
        public static final String COLUMN_NAME_THREE_POINTS = "threepoints";
        public static final String COLUMN_NAME_THREE_POINT_ATTEMPTS = "threepointattempts";
        public static final String COLUMN_NAME_FREE_THROWS = "freethrows";
        public static final String COLUMN_NAME_FREE_THOW_ATTEMPTS = "freethrowattempts";
        public static final String COLUMN_NAME_ASSISTS = "assists";
        public static final String COLUMN_NAME_FOULS = "fouls";
        public static final String COLUMN_NAME_REBOUNDS = "rebounds";
    }

    public static abstract class GameStatEntry implements BaseColumns {
        public static final String TABLE_NAME = "gamestats";
        public static final String COLUMN_NAME_GAME_STAT_ID = "gamestatid";
        public static final String COLUMN_NAME_GAME_ID = "gameid";
        public static final String COLUMN_NAME_STAT_ID = "statid";
    }
}
