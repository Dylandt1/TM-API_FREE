package fr.tmmods.tmapi.bungee.players;

import fr.tmmods.tmapi.bungee.TMBungeeAPI;
import fr.tmmods.tmapi.data.manager.Files;
import fr.tmmods.tmapi.data.manager.Json.SerializationManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.File;
import java.util.UUID;

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

public class CraftOfflinePlayer
{
    public CraftOfflinePlayer() {}

    public static void craft(UUID uuid)
    {
        final SerializationManager ser = new SerializationManager();
        final File saveDirectory = new File(TMBungeeAPI.getInstance().getDataFolder(), "/players/");
        final File file = new File(saveDirectory, ProxyServer.getInstance().getPlayer(uuid)+":"+uuid.toString()+".json");
        if(!file.exists())
        {
            final ProxiedPlayer p = ProxyServer.getInstance().getPlayer(uuid);
            final String json = ser.serialize(new OfflinePlayer(uuid.toString(), p.getName(), false, false, 0L, 0L, true));
            Files.save(file, json);
        }
    }
}
