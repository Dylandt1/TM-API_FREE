package fr.tmmods.tmapi.bungee.players;

import fr.tmmods.tmapi.bungee.TMBungeeAPI;
import fr.tmmods.tmapi.data.manager.Json.SerializationManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OfflinePlayer
{
    private String uuid;
    private String name;
    private String lastName;
    private boolean banned;
    private boolean whitelisted;
    private long firstPlayed;
    private long lastPlayed;
    private boolean playedBefore;
    private List<String> permissions;

    public OfflinePlayer() {}

    public OfflinePlayer(String uuid, String name, boolean banned, boolean whitelisted, long firstPlayed, long lastPlayed, boolean playedBefore, List<String> permissions)
    {
        this.uuid = uuid;
        this.name = name;
        this.lastName = name;
        this.banned = banned;
        this.whitelisted = whitelisted;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
        this.playedBefore = playedBefore;
        this.permissions = permissions;
    }

    public OfflinePlayer(String uuid, String name, boolean banned, boolean whitelisted, long firstPlayed, long lastPlayed, boolean playedBefore)
    {
        this.uuid = uuid;
        this.name = name;
        this.lastName = name;
        this.banned = banned;
        this.whitelisted = whitelisted;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
        this.playedBefore = playedBefore;
        this.permissions = new ArrayList<>();
    }

    /**
     * Getters :
     */

    public boolean isOnline() {
        return ProxyServer.getInstance().getPlayer(UUID.fromString(uuid)).isConnected();
    }

    public UUID getUniqueId() {
        return UUID.fromString(uuid);
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isBanned() {
        return banned;
    }

    public ProxiedPlayer getPlayer() {
        return ProxyServer.getInstance().getPlayer(uuid);
    }

    public boolean isWhitelisted() {
        return whitelisted;
    }

    public void setWhitelisted(boolean whitelisted) {
        this.whitelisted = whitelisted;
    }

    public long getFirstPlayed() {
        return firstPlayed;
    }

    public long getLastPlayed() {
        return lastPlayed;
    }

    public boolean hasPlayedBefore() {
        return playedBefore;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    public void sendMessage(TextComponent textComponent) {
        if(ProxyServer.getInstance().getPlayer(uuid).isConnected())
        {
            ProxyServer.getInstance().getPlayer(uuid).sendMessage(textComponent);
        }
    }

    public OfflinePlayer getOfflinePlayer(String playerName)
    {
        final SerializationManager ser = new SerializationManager();
        final File saveDirectory = new File(TMBungeeAPI.getInstance().getDataFolder(), "/players/");

        List<String> list = null;
        try {
            list = Files.walk(saveDirectory.toPath())
                    .map((q) -> q.getFileName().toString())
                    .filter((s) -> s.contains(playerName))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(list.size() == 1){
            final File file = new File(saveDirectory+list.get(0));
            final String json = fr.tmmods.tmapi.data.manager.Files.loadFile(file);
            return (OfflinePlayer) ser.deserialize(json, OfflinePlayer.class);
        }

        return null;
    }

    public OfflinePlayer getOfflinePlayer(UUID uniqueId)
    {
        final SerializationManager ser = new SerializationManager();
        final File saveDirectory = new File(TMBungeeAPI.getInstance().getDataFolder(), "/players/");

        List<String> list = null;
        try {
            list = Files.walk(saveDirectory.toPath())
                    .map((q) -> q.getFileName().toString())
                    .filter((s) -> s.contains(uniqueId.toString()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(list.size() == 1){
            final File file = new File(saveDirectory+list.get(0));
            final String json = fr.tmmods.tmapi.data.manager.Files.loadFile(file);
            return (OfflinePlayer) ser.deserialize(json, OfflinePlayer.class);
        }

        return null;
    }

    /**
     * Setters :
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public void setLastPlayed(long lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public void setPlayedBefore(boolean playedBefore) {
        this.playedBefore = playedBefore;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void addPermissions(List<String> permissions) {
        this.permissions.addAll(permissions);
    }

    public void addPermission(String permission) {
        this.permissions.add(permission);
    }

    /**
     * Save OfflinePlayer object :
     */

    public void save() {
        final SerializationManager ser = new SerializationManager();
        final File saveDirectory = new File(TMBungeeAPI.getInstance().getDataFolder(), "/players/");

        // Save FriendsManager on Json file
        final File file = new File(saveDirectory, name+":"+uuid.toString()+".json");
        final String json = ser.serialize(this);
        fr.tmmods.tmapi.data.manager.Files.save(file, json);
    }
}
