package fr.tmmods.tmapi.spigot;

import fr.tmmods.tmapi.spigot.listeners.PlayerListener;
import fr.tmmods.tmapi.spigot.config.ConfigsManager;
import fr.tmmods.tmapi.data.manager.UpdateChecker;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

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
    private final int pluginId = 118339;
    private boolean upToDate;
    private String newVersion;

    private static TMSpigotAPI INSTANCE;

    private YamlConfiguration config;

    @Override
    public void onLoad()
    {
        getLogger().info(" ");
        getLogger().info("Loading in progress...");
        getLogger().info(" ");

        //Config Files
        getLogger().info("Loading config files...");
        getLogger().info(" ");
        this.config = new ConfigsManager(this, "config").getConfig();

        if(config.getBoolean("updates.checker"))
        {
            // UpdateChecker added by TM-API free software
            getLogger().info("# ----------{ UpdateChecker }---------- #");
            getLogger().info(" ");
            getLogger().info("Version : "+this.getDescription().getVersion());
            getLogger().info(" ");
            new UpdateChecker(pluginId).getVersion(version -> {
                if(this.getDescription().getVersion().equals(version)) {
                    this.upToDate = true;
                    getLogger().info("Up to date !");
                }else {
                    this.upToDate = false;
                    this.newVersion = version;
                    getLogger().info("New update is available : "+version);
                }
                getLogger().info(" ");
                getLogger().info("# ---------- --------------- ---------- #");
            });
        }
    }

    @Override
    public void onEnable()
    {
        INSTANCE = this;

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getLogger().info(" ");
        getLogger().info("Ready to use !");
    }

    public static TMSpigotAPI getInstance() {return INSTANCE;}
    public int getPluginId() {return pluginId;}
    public YamlConfiguration getConfig() {return config;}
    public boolean isUpToDate() {return upToDate;}
    public String getNewVersion() {return newVersion;}

    @Override
    public void onDisable()
    {
        getLogger().info("Disabling in progress...");
        getLogger().info(" ");
        getLogger().info("Disabled !");
    }
}
