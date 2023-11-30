package fr.tmmods.tmapi.data.manager;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

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

public class UpdateChecker
{
    /**
     * To use this update checker for your plugins, you can add the following code in onEnable @Override methods with your plugins ids on spigot.org.
     *
     * new UpdateChecker(pluginId).getVersion(version -> {
     *     if (this.getDescription().getVersion().equals(version)) {
     *         getLogger().info("There is not a new update available.");
     *     }else {
     *         getLogger().info("New update available !");
     *     }
     * });
     */

    private final int id;
    private final String apiURL;

    public UpdateChecker(int id)
    {
        this.id = id;
        this.apiURL = "https://api.spigotmc.org/legacy/update.php?resource="+id;
    }

    public void getVersion(final Consumer<String> consumer)
    {
        if(id == 0) return;

        try (InputStream is = new URL(apiURL).openStream(); Scanner scanner = new Scanner(is))
        {
            if (scanner.hasNext()) {consumer.accept(scanner.next());}
        } catch (IOException e) {
            new RuntimeException("Unable to check for updates: " + e.getMessage());
        }
    }
}
