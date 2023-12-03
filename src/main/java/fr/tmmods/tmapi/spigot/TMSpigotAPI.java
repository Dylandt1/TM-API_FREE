package fr.tmmods.tmapi.spigot;

import fr.tmmods.tmapi.data.manager.sql.SqlType;
import fr.tmmods.tmapi.spigot.config.ConfigsManager;
import fr.tmmods.tmapi.spigot.data.manager.DBManager;
import fr.tmmods.tmapi.spigot.data.manager.RedisManager;
import fr.tmmods.tmapi.data.manager.UpdateChecker;
import fr.tmmods.tmapi.data.manager.sql.SqlManager;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

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

public class TMSpigotAPI extends JavaPlugin
{
    private final int id = 0;
    private static TMSpigotAPI INSTANCE;
    private final String console = "§f[§cTM§f-§bAPI§f] -> ";

    public Configuration config;
    public static boolean redisEnable;
    public static boolean sqlEnable;

    @Override
    public void onLoad()
    {
        getLogger().info(console + "§2Loading in progress...");

        // Check for update
        getLogger().info(console + "§6Checking for update...");
        getLogger().info(console + " ");
        getLogger().info(console + "§6Version §f: §2"+this.getDescription().getVersion());
        getLogger().info(console + " ");
        new UpdateChecker(id).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info(console + "§2Up to date §c!");
                getLogger().info(console + " ");
            } else {
                getLogger().info(console + "§6New update is available §f: §2"+version);
                getLogger().info(console + " ");
            }
        });

        //Config Files
        getLogger().info(console + "Loading config files...");
        getLogger().info(console + " ");
        this.config = new ConfigsManager(this, "config").getConfig();
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
            getLogger().info(console + " ");
            DBManager.initAllConnections();
            createTables();
        }

        if(redisEnable)
        {
            getLogger().info(console + "Connecting to redis servers...");
            RedisManager.initAllConnections();
        }

        getLogger().info(console + "§2Ready to use §c!");
    }

    public static TMSpigotAPI getInstance() {return INSTANCE;}

    @Override
    public void onDisable()
    {
        getLogger().info(console + "§6Disabling in progress...");
        getLogger().info(console + " ");

        if(sqlEnable) {
            getLogger().info(console + "§6Disconnect from Mysql server...");
            DBManager.closeAllConnections();
        }

        if(redisEnable) {
            getLogger().info(console + "§6Disconnect fom Redisson server...");
            RedisManager.closeAllConnections();
        }

        getLogger().info(console + " ");
        getLogger().info(console + "§cDisabled !");
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
