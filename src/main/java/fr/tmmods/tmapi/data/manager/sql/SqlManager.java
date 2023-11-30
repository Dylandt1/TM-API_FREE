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
    // Create tables in database
    public static void createTables(String prefixTables, String profilesTable, String friendsTable, String teamsTable, String mailsTable)
    {
        //Send create profiles table
        update("CREATE TABLE IF NOT EXISTS "+prefixTables+profilesTable+" (" +
                "id "+SqlType.INT.sql()+" NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "uuid "+SqlType.VARCHAR.sql()+", " +
                "name "+SqlType.VARCHAR.sql()+", " +
                "displayName "+SqlType.VARCHAR.sql()+", " +
                "teamID "+SqlType.VARCHAR.sql()+", " +
                "rankInTeam "+SqlType.INT.sql()+", " +
                "fAllow "+SqlType.BOOLEAN.sql()+", " +
                "msgAllow "+SqlType.BOOLEAN.sql()+", " +
                "gpAllow "+SqlType.BOOLEAN.sql()+", " +
                "teamsAllow "+SqlType.BOOLEAN.sql()+", " +
                "opts "+SqlType.VARCHAR.sql()+")");

        //Send create friends list table
        update("CREATE TABLE IF NOT EXISTS "+prefixTables+friendsTable+" (" +
                "uuid "+SqlType.VARCHAR.sql()+", " +
                "friendUUID "+SqlType.VARCHAR.sql()+", " +
                "friendName "+SqlType.VARCHAR.sql()+", " +
                "friendDisplayName "+SqlType.VARCHAR.sql()+")");

        //Send create teams table
        update("CREATE TABLE IF NOT EXISTS "+prefixTables+teamsTable+" (" +
                "id "+SqlType.INT.sql()+" NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "teamId "+SqlType.VARCHAR.sql()+", " +
                "teamName "+SqlType.VARCHAR.sql()+", " +
                "teamPrefix "+SqlType.VARCHAR.sql()+", " +
                "teamRank "+SqlType.VARCHAR.sql()+", " +
                "trophy "+SqlType.INT.sql()+", " +
                "defaultRole "+SqlType.TINYINT.sql("1")+", " +
                "leaderId "+SqlType.VARCHAR.sql()+", " +
                "deputyId "+SqlType.VARCHAR.sql()+", " +
                "prefixLeader "+SqlType.VARCHAR.sql()+", " +
                "prefixDeputy "+SqlType.VARCHAR.sql()+", " +
                "prefixAssistants "+SqlType.VARCHAR.sql()+", " +
                "prefixMembers "+SqlType.VARCHAR.sql()+", " +
                "prefixRecruits "+SqlType.VARCHAR.sql()+")");

        //Send create mails table
        update("CREATE TABLE IF NOT EXISTS "+prefixTables+mailsTable+" (" +
                "of "+SqlType.VARCHAR.sql()+", " +
                "to "+SqlType.VARCHAR.sql()+", " +
                "cc "+SqlType.VARCHAR.sql()+", " +
                "msg "+SqlType.VARCHAR.sql()+")");
    }

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
