package fr.tmmods.tmapi.spigot.data.manager;

import fr.tmmods.tmapi.data.manager.sql.DBAccess;
import fr.tmmods.tmapi.data.manager.sql.DBCredentials;
import fr.tmmods.tmapi.spigot.TMSpigotAPI;

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

public enum DBManager
{
    // First connection credentials
    FBG_DATABASE(new DBCredentials(TMSpigotAPI.getInstance().config.getString("mysql.host"),
            TMSpigotAPI.getInstance().config.getInt("mysql.port"),
            TMSpigotAPI.getInstance().config.getString("mysql.dbName"),
            TMSpigotAPI.getInstance().config.getString("mysql.user"),
            TMSpigotAPI.getInstance().config.getString("mysql.password"),
            TMSpigotAPI.getInstance().config.getInt("mysql.maxPoolSize"),
            TMSpigotAPI.getInstance().config.getInt("mysql.maxLifeTime"),
            TMSpigotAPI.getInstance().config.getInt("mysql.poolTimeout"),
            TMSpigotAPI.getInstance().config.getInt("mysql.dataleak"),
            TMSpigotAPI.getInstance().config.getInt("mysql.timeout"),
            TMSpigotAPI.getInstance().config.getString("mysql.prefixTables"),
            TMSpigotAPI.getInstance().config.getString("mysql.profilesTable"),
            TMSpigotAPI.getInstance().config.getString("mysql.friendsTable"),
            TMSpigotAPI.getInstance().config.getString("mysql.teamsTable"),
            TMSpigotAPI.getInstance().config.getString("mysql.mailsTable")));

    private final DBAccess dbAccess;

    DBManager(DBCredentials credentials) {this.dbAccess = new DBAccess(credentials);}

    public DBAccess getDbAccess() {return dbAccess;}

    public static void initAllConnections() {for(DBManager dbM : values()) {dbM.dbAccess.init();}}

    public static void closeAllConnections() {for(DBManager dbM : values()) {dbM.dbAccess.stop();}}
}