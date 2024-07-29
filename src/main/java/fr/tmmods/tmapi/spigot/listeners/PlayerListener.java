package fr.tmmods.tmapi.spigot.listeners;

import fr.tmmods.tmapi.spigot.TMSpigotAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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

public class PlayerListener implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        OfflinePlayer p = e.getPlayer();

        if(TMSpigotAPI.getInstance().getConfig().getBoolean("updates.adminMsg.use"))
        {
            if(!TMSpigotAPI.getInstance().isUpToDate())
            {
                if(p.getPlayer().hasPermission(TMSpigotAPI.getInstance().getConfig().getString("updates.adminMsg.permission")))
                {
                    p.getPlayer().sendMessage("[TM-API]: New version is available : "+TMSpigotAPI.getInstance().getNewVersion());
                }
            }
        }
    }
}
