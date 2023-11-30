package fr.tmmods.tmapi.data.manager.sql;

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

public class DBCredentials
{
    private String host;
    private int port;
    private String dbName;
    private String user;
    private String password;
    private int maxPoolSize;
    private int maxLifeTime;
    private int poolTimeOut;
    private int dataLeak;
    private int timeOut;
    private String prefixTables;
    private String profilesTable;
    private String friendsTable;
    private String teamsTable;
    private String mailsTable;

    public DBCredentials(String host, int port, String dbName, String user, String password, int maxPoolSize, int maxLifeTime, int poolTimeOut, int dataLeak, int timeOut, String prefixTables, String profilesTable, String friendsTable, String teamsTable, String mailsTable)
    {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.maxPoolSize = maxPoolSize;
        this.maxLifeTime = maxLifeTime;
        this.poolTimeOut = poolTimeOut;
        this.dataLeak = dataLeak;
        this.timeOut = timeOut;
        this.prefixTables = prefixTables;
        this.profilesTable = profilesTable;
        this.friendsTable = friendsTable;
        this.teamsTable = teamsTable;
        this.mailsTable = mailsTable;
    }

    public String getHost() {return host;}
    public int getPort() {return port;}
    public String getDbName() {return dbName;}
    public String getUser() {return user;}
    public String getPassword() {return password;}
    public int getMaxPoolSize() {return maxPoolSize;}
    public int getMaxLifeTime() {return maxLifeTime;}
    public int getPoolTimeOut() {return poolTimeOut;}
    public int getDataLeak() {return dataLeak;}
    public int getTimeOut() {return timeOut;}
    public String getPrefixTables() {return prefixTables;}
    public String getProfilesTable() {return profilesTable;}
    public String getFriendsTable() {return friendsTable;}
    public String getTeamsTable() {return teamsTable;}
    public String getMailsTable() {return mailsTable;}

    public String toURI() {return "jdbc:mysql://" + host + ":" + port + "/" + dbName;}
}
