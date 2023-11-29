package fr.tmmods.tmapi.data.manager.sql;

import fr.tmmods.tmapi.bungee.data.manager.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This file is part of TM-API, a Spigot/BungeeCord API.
 *
 * TM-API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TM-API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

public class SqlManager
{
    // Variables
    private static String prefixTables = DBManager.FBG_DATABASE.getDbAccess().getCredentials().getPrefixTables();
    private static String profilesTable = DBManager.FBG_DATABASE.getDbAccess().getCredentials().getProfilesTable();
    private static String friendsTable = DBManager.FBG_DATABASE.getDbAccess().getCredentials().getFriendsTable();
    private static String teamsTable = DBManager.FBG_DATABASE.getDbAccess().getCredentials().getTeamsTable();

    // Create tables in database
    public static void createTables()
    {
        //Send create profiles table
        update("CREATE TABLE IF NOT EXISTS "+prefixTables+profilesTable+" (" +
                "id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "uuid VARCHAR(255), " +
                "name VARCHAR(255), " +
                "displayName VARCHAR(255), " +
                "teamID VARCHAR(255), " +
                "rankInTeam INT(11), " +
                "fAllow INT(11), " +
                "msgAllow INT(11)," +
                "gpAllow INT(11)," +
                "teamsAllow INT(11)," +
                "opts VARCHAR(255))");

        //Send create friends list table
        update("CREATE TABLE IF NOT EXISTS "+prefixTables+friendsTable+" (" +
                "uuid VARCHAR(255), " +
                "friendUUID VARCHAR(255), " +
                "friendName VARCHAR(255))");

        //Send create teams table
        update("CREATE TABLE IF NOT EXISTS "+prefixTables+teamsTable+" (" +
                "id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "teamId VARCHAR(255), " +
                "teamName VARCHAR(255), " +
                "teamLtr VARCHAR(255), " +
                "leaderId VARCHAR(255), " +
                "deputyId VARCHAR(255), " +
                "teamRank VARCHAR(255), " +
                "trophy INT(11), " +
                "defaultRole int(11), " +
                "prefixLeader VARCHAR(255), " +
                "prefixDeputy VARCHAR(255), " +
                "prefixAssistants VARCHAR(255), " +
                "prefixMembers VARCHAR(255), " +
                "prefixRecruits VARCHAR(255))");
    }

    // Getters
    public static String getPrefixTables() {return prefixTables;}
    public static String getProfilesTable() {return profilesTable;}
    public static String getFriendsTable() {return friendsTable;}
    public static String getTeamsTable() {return teamsTable;}

    // Send query type executeUpdate
    public static void update(String query)
    {
        try
        {
            Connection connection = DBManager.FBG_DATABASE.getDbAccess().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Send query type executeQuery
    public static void query(String query)
    {
        try
        {
            Connection connection = DBManager.FBG_DATABASE.getDbAccess().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeQuery();
            ps.close();
            connection.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    // Send query type execute
    public static void execute(String query)
    {
        try {
            Connection connection = DBManager.FBG_DATABASE.getDbAccess().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
