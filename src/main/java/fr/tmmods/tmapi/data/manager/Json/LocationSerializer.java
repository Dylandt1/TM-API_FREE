package fr.tmmods.tmapi.data.manager.Json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.bukkit.Location;

import java.io.IOException;

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

public class LocationSerializer extends StdSerializer<Location>
{
    public LocationSerializer() {this(null);}
    public LocationSerializer(Class<Location> c) {super(c);}

    @Override
    public void serialize(Location location, JsonGenerator gen, SerializerProvider provider) throws IOException
    {
        location.serialize();
        gen.writeStartObject();
        gen.writeObjectField("world", location.getWorld());
        gen.writeObjectField("block", location.getBlock());
        gen.writeObjectField("direction", location.getDirection());
        gen.writeObjectField("chunk", location.getChunk());
        //Location :
        gen.writeNumberField("x", location.getX());
        gen.writeNumberField("y", location.getY());
        gen.writeNumberField("z", location.getZ());
        gen.writeNumberField("yaw", location.getYaw());
        gen.writeNumberField("pitch", location.getPitch());
        //Block :
        gen.writeNumberField("blockX", location.getBlockX());
        gen.writeNumberField("blockY", location.getBlockY());
        gen.writeNumberField("blockZ", location.getBlockZ());
        //End :
        gen.writeEndObject();
    }
}
