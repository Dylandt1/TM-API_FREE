package fr.tmmods.tmapi.spigot.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

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

public class ConfigsManager
{
    private YamlConfiguration config;
    private File file;

    public ConfigsManager(JavaPlugin plugin, String fileName)
    {
        this.file = new File(plugin.getDataFolder(), fileName+".yml");
        if (!file.exists())
        {
            try {
                if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
                InputStream in = plugin.getResource(fileName+".yml");
                if (in != null)
                {
                    OutputStream out = new FileOutputStream(file);

                    byte[] buf = new byte[1024 * 4];
                    int len = in.read(buf);
                    while (len != -1) {
                        out.write(buf, 0, len);
                        len = in.read(buf);
                    }
                    out.close();
                    in.close();
                }
                else file.createNewFile();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        reload();
    }

    public void reload()
    {
        try {
            this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        }catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfig() {return this.config;}

    public void save()
    {
        try {
            config.save(this.file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
