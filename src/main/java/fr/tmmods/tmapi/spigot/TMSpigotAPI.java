package fr.tmmods.tmapi.spigot;

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

    private static TMSpigotAPI INSTANCE;
    private final String console = "[TM-API] -> ";

    public YamlConfiguration config;

    @Override
    public void onLoad()
    {
        getLogger().info(" ");
        getLogger().info(console + "Loading in progress...");
        getLogger().info(" ");

        //Config Files
        getLogger().info(console + "Loading config files...");
        getLogger().info(" ");
        this.config = new ConfigsManager(this, "config").getConfig();

        if(config.getBoolean("updates.checker"))
        {
            // UpdateChecker added by TM-API free software
            getLogger().info(console + "# ----------{ UpdateChecker }---------- #");
            getLogger().info(" ");
            getLogger().info(console + "Version : "+this.getDescription().getVersion());
            getLogger().info(" ");
            new UpdateChecker(pluginId).getVersion(version -> {
                if(this.getDescription().getVersion().equals(version)) {
                    this.upToDate = true;
                    getLogger().info(console + "Up to date !");
                }else {
                    this.upToDate = false;
                    getLogger().info(console + "New update is available : "+version);
                }
                getLogger().info(" ");
                getLogger().info(console + "# ---------- --------------- ---------- #");
            });
        }
    }

    @Override
    public void onEnable()
    {
        INSTANCE = this;
        getLogger().info(console + "Ready to use !");
    }

    public static TMSpigotAPI getInstance() {return INSTANCE;}

    @Override
    public void onDisable()
    {
        getLogger().info(console + "Disabling in progress...");
        getLogger().info(" ");
        getLogger().info(console + "Disabled !");
    }
}
