package net.dxzzz.getmaxplayer;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class MaxPlayerPlaceholderExpansion extends PlaceholderExpansion {

    private final String currentServerName;
    private final RedisManager redisManager;

    public MaxPlayerPlaceholderExpansion(String currentServerName, RedisManager redisManager) {
        this.currentServerName = currentServerName;
        this.redisManager = redisManager;
    }

    @Override
    public String getIdentifier() {
        return "maxplayer";
    }

    @Override
    public String getAuthor() {
        return "X_32mx";
    }

    @Override
    public String getVersion() {
        return "1.0-redis";
    }

    @Override
    public String onRequest(OfflinePlayer player, String serverName) {
        if (serverName == null || serverName.isEmpty()) return "";

        String value = redisManager.getMaxPlayers(serverName);
        return value != null ? value : "未知";
    }
    @Override
    public String onPlaceholderRequest(Player player, String serverName){
        if (serverName == null || serverName.isEmpty()) return "";

        String value = redisManager.getMaxPlayers(serverName);
        return value != null ? value : "未知";

    }
}
