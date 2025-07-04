package net.dxzzz.getmaxplayer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class GetMaxPlayer extends JavaPlugin {
    public final Logger logger= getLogger();
    private static GetMaxPlayer instance;
    private RedisManager redisManager;

    @Override
    public void onEnable() {
        logger.info("===========[GetMaxPlayer正在加载中]===========");
        logger.info("Author: X_32mx");
        logger.info("QQ: 2644489337");
        logger.info("This plugin is only for Dxzzz.net");
        instance = this;
        saveDefaultConfig();

        // 初始化 Redis
        this.redisManager = new RedisManager(
                getConfig().getString("redis.host"),
                getConfig().getInt("redis.port"),
                getConfig().getString("redis.password")
        );

        // 写入当前服务器的最大人数（如果还没有）
        String thisServer = getConfig().getString("server-name");
        int maxPlayers = Bukkit.getMaxPlayers();
        redisManager.writeMaxPlayersIfAbsent(thisServer, maxPlayers);

        // 注册 Placeholder
        new MaxPlayerPlaceholderExpansion(thisServer, redisManager).register();

        logger.info("已连接 Redis。");
        logger.info("==========[GetMaxPlayer已加载完毕]=========");
    }

    @Override
    public void onDisable() {
        redisManager.shutdown();
    }

    public static GetMaxPlayer getInstance() {
        return instance;
    }
}
