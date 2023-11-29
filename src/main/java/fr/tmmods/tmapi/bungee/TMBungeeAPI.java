package fr.tmmods.tmapi.bungee;

import fr.tmmods.tmapi.bungee.config.ConfigsManager;
import fr.tmmods.tmapi.bungee.data.manager.DBManager;
import fr.tmmods.tmapi.bungee.data.manager.RedisManager;
import fr.tmmods.tmapi.data.manager.UpdateChecker;
import fr.tmmods.tmapi.data.manager.sql.SqlManager;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

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

public class TMBungeeAPI extends Plugin
{
    private final int id = 0;
    private static TMBungeeAPI INSTANCE;
    public static String console = "[TM-API] -> ";
    private Configuration config;

    public SqlManager sqlProfilesManager;
    public SqlManager sqlFListManager;
    public static boolean redisEnable;
    public static boolean sqlEnable;

    @Override
    public void onLoad()
    {
        getLogger().info(console + "Loading in progress...");

        // UpdateChecker added by TM-API free software
        getLogger().info(console + "Checking for update...");
        new UpdateChecker(id).getVersion(version -> {
          if (this.getDescription().getVersion().equals(version)) {
              getLogger().info(console + "Up to date !");
          } else {
              getLogger().info(console + "New update is available !");
          }
        });

        //Config Files
        getLogger().info(console + "Loading config files...");
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
            getLogger().info(console + "Connecting to databases...");
            DBManager.initAllConnections();
            SqlManager.createTables();
        }
        if(redisEnable)
        {
            getLogger().info(console + "Connecting to redis servers...");
            RedisManager.initAllConnections();
        }

        getLogger().info(console + "Ready to use !");
    }

    public static TMBungeeAPI getInstance() {return INSTANCE;}
    public Configuration getConfig() {return config;}

    @Override
    public void onDisable()
    {
        getLogger().info(console + "Disabling in progress...");

        if(sqlEnable) {
            getLogger().info(console + "Disconnect from Mysql server...");
            DBManager.closeAllConnections();
        }

        if(redisEnable) {
            getLogger().info(console + "Disconnect from Redisson server...");
            RedisManager.closeAllConnections();
        }

        getLogger().info(console + "Disabled !");
    }
}
