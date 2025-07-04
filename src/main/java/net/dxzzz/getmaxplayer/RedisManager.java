package net.dxzzz.getmaxplayer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisManager {
    private final JedisPool jedisPool;

    public RedisManager(String host, int port, String password) {
        if (password != null && !password.isEmpty()) {
            jedisPool = new JedisPool(host, port);
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.auth(password);
            }
        } else {
            jedisPool = new JedisPool(host, port);
        }
    }

    public void writeMaxPlayersIfAbsent(String serverName, int maxPlayers) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "maxplayer:" + serverName;
            if (!jedis.exists(key)) {
                jedis.set(key, String.valueOf(maxPlayers));
            }
        }
    }

    public String getMaxPlayers(String serverName) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "maxplayer:" + serverName;
            return jedis.get(key);
        }
    }

    public void shutdown() {
        jedisPool.close();
    }
}
