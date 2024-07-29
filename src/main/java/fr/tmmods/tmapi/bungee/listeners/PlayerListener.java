package fr.tmmods.tmapi.bungee.listeners;

import fr.tmmods.tmapi.bungee.TMBungeeAPI;
import fr.tmmods.tmapi.bungee.players.CraftOfflinePlayer;
import fr.tmmods.tmapi.bungee.players.OfflinePlayer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

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
    public void onJoin(PostLoginEvent e)
    {
        CraftOfflinePlayer.craft(e.getPlayer().getUniqueId());
        OfflinePlayer p = new OfflinePlayer().getOfflinePlayer(e.getPlayer().getUniqueId());

        if(TMBungeeAPI.getInstance().getConfig().getBoolean("updates.adminMsg.use"))
        {
            if(!TMBungeeAPI.getInstance().isUpToDate())
            {
                if(p.hasPermission(TMBungeeAPI.getInstance().getConfig().getString("updates.adminMsg.permission")))
                {
                    p.sendMessage(new TextComponent("[TM-API]: New version is available : "+TMBungeeAPI.getInstance().getNewVersion()));
                }
            }
        }
    }
}
