package fr.tmmods.tmapi.bungee;

import fr.tmmods.tmapi.bungee.config.ConfigsManager;
import fr.tmmods.tmapi.bungee.data.manager.DBManager;
import fr.tmmods.tmapi.bungee.data.manager.RedisManager;
import fr.tmmods.tmapi.data.manager.UpdateChecker;
import fr.tmmods.tmapi.data.manager.sql.SqlManager;

import fr.tmmods.tmapi.data.manager.sql.SqlType;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class TMBungeeAPI extends Plugin
{
    private final int id = 0;
    private static TMBungeeAPI INSTANCE;
    public static String console = "[TM-API] -> ";
    private Configuration config;

    public static boolean redisEnable;
    public static boolean sqlEnable;

    @Override
    public void onLoad()
    {
        getLogger().info(console + " ");
        getLogger().info(console + "Loading in progress...");
        getLogger().info(console + " ");

        // Check for update
        getLogger().info(console + "Checking for update...");
        getLogger().info(console + " ");
        getLogger().info(console + "Version : "+this.getDescription().getVersion());
        getLogger().info(console + " ");
        new UpdateChecker(id).getVersion(version -> {
          if (this.getDescription().getVersion().equals(version)) {
              getLogger().info(console + "Up to date !");
              getLogger().info(console + " ");
          } else {
              getLogger().info(console + "New update is available : "+version);
              getLogger().info(console + " ");
          }
        });

        //Config Files
        getLogger().info(console + "Loading config files...");
        getLogger().info(console + " ");
        this.config = ConfigsManager.getConfig("config", this);
        redisEnable = config.getBoolean("redis.use");
        sqlEnable = config.getBoolean("mysql.use");
    }

    @Override
    public void onEnable()
    {
        INSTANCE = this;

        if(sqlEnable)
        {
            String prefixTables = config.getString("mysql.prefixTables");
            String profilesTable = config.getString("mysql.profilesTable");
            String friendsTable = config.getString("mysql.friendsTable");
            String teamsTable = config.getString("mysql.teamsTable");
            String mailsTable = config.getString("mysql.mailsTable");

            getLogger().info(console + " ");
            getLogger().info(console + "Connecting to databases...");
            getLogger().info(console + " ");
            DBManager.initAllConnections();
            createTables();
        }

        if(redisEnable)
        {
            getLogger().info(console + " ");
            getLogger().info(console + "Connecting to redis servers...");
            getLogger().info(console + " ");
            RedisManager.initAllConnections();
        }

        getLogger().info(console + " ");
        getLogger().info(console + "Ready to use !");
    }

    public static TMBungeeAPI getInstance() {return INSTANCE;}
    public Configuration getConfig() {return config;}

    @Override
    public void onDisable()
    {
        getLogger().info(console + "Disabling in progress...");
        getLogger().info(console + " ");

        if(sqlEnable) {
            getLogger().info(console + "Disconnect from Mysql server...");
            getLogger().info(console + " ");
            DBManager.closeAllConnections();
        }

        if(redisEnable) {
            getLogger().info(console + "Disconnect from Redisson server...");
            getLogger().info(console + " ");
            RedisManager.closeAllConnections();
        }

        getLogger().info(console + "Disabled !");
    }

    private void createTables()
    {
        Map<String, List<String>> tables = new HashMap<>();

        String prefixTables = config.getString("mysql.prefixTables");
        String serversTable = config.getString("mysql.serversTable");
        String mailsTable = config.getString("mysql.mailsTable");

        List<String> listServersTable = Arrays.asList(
                "id "+ SqlType.INT.sql()+" NOT NULL AUTO_INCREMENT PRIMARY KEY",
                "name "+SqlType.VARCHAR.sql(),
                "status "+SqlType.TINYINT.sql(1),
                "ip "+SqlType.VARCHAR.sql(),
                "port "+SqlType.INT.sql()
        );

        List<String> listMailsTable = Arrays.asList(
                "sender "+SqlType.VARCHAR.sql(),
                "target "+SqlType.VARCHAR.sql(),
                "cc "+SqlType.VARCHAR.sql(),
                "message "+SqlType.VARCHAR.sql()
        );

        tables.put(serversTable, listServersTable);
        tables.put(mailsTable, listMailsTable);

        SqlManager.createTables(prefixTables, tables);
    }
}
