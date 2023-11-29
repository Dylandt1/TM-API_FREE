package fr.tmmods.tmapi.data.manager.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
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

public class DBAccess
{
    private DBCredentials credentials;
    private HikariDataSource dataSource;

    // Init DB connection
    public DBAccess(DBCredentials credentials) {this.credentials = credentials;}

    public void init() {load();}

    public void stop() {if (dataSource != null) {dataSource.close();}}

    public HikariDataSource getDataSource() {return dataSource;}

    public Connection getConnection() throws SQLException
    {
        if (dataSource == null) {load();}
        return dataSource.getConnection();
    }

    public DBCredentials getCredentials() {return credentials;}

    private void load() {
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(credentials.toURI());
        config.setUsername(credentials.getUser());
        config.setPassword(credentials.getPassword());
        config.setMaximumPoolSize(credentials.getMaxPoolSize());
        config.setMaxLifetime(credentials.getMaxLifeTime());
        config.setIdleTimeout(credentials.getPoolTimeOut());
        config.setLeakDetectionThreshold(credentials.getDataLeak());
        config.setConnectionTimeout(credentials.getTimeOut());

        dataSource = new HikariDataSource(config);
    }
}

