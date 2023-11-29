package fr.tmmods.tmapi.spigot.data.manager;

import fr.tmmods.tmapi.data.manager.redis.RedisAccess;
import fr.tmmods.tmapi.data.manager.redis.RedisCredentials;
import fr.tmmods.tmapi.spigot.TMSpigotAPI;

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

public enum RedisManager
{
    FBG_REDIS(new RedisCredentials(TMSpigotAPI.getInstance().config.getString("redis.host"),
            TMSpigotAPI.getInstance().config.getInt("redis.port"),
            TMSpigotAPI.getInstance().config.getString("redis.password"),
            TMSpigotAPI.getInstance().config.getString("redis.clientName"),
            TMSpigotAPI.getInstance().config.getInt("redis.threads"),
            TMSpigotAPI.getInstance().config.getInt("redis.nettyThreads"),
            TMSpigotAPI.getInstance().config.getInt("redis.dataBase")));

    private final RedisAccess redisAccess;

    RedisManager(RedisCredentials credentials) {this.redisAccess = new RedisAccess(credentials);}

    public RedisAccess getRedisAccess() {return redisAccess;}

    public static void initAllConnections() {for(RedisManager rdm : values()) {rdm.redisAccess.init();}}

    public static void closeAllConnections() {for(RedisManager rdm : values()) {rdm.redisAccess.getRedisCli().shutdown();}}
}