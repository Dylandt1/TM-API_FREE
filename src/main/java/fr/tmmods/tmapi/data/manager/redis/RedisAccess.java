package fr.tmmods.tmapi.data.manager.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

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

public class RedisAccess
{
    private RedisCredentials credentials;
    private RedissonClient redisCli;

    public RedisAccess(RedisCredentials credentials) {this.credentials = credentials;}

    public void init() {load();}

    public void stop() {if(redisCli != null) redisCli.shutdown();}

    public RedissonClient getRedisCli()
    {
        if(redisCli == null) {load();}
        return redisCli;
    }

    private void load()
    {
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec());
        config.setThreads(credentials.getThreads());
        config.setNettyThreads(credentials.getNettyThreads());
        ((SingleServerConfig)(config.useSingleServer()
                .setAddress(credentials.getUrl())
                .setPassword(credentials.getPassword()))
                .setClientName(credentials.getClientName()))
                .setDatabase(credentials.getDataBase());
        this.redisCli = Redisson.create(config);
    }
}
