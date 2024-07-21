package fr.tmmods.tmapi.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Ranks
{
    DEFAULT("§7", "", "", "§7Joueur"),
    STARTER("§3", "", "§f", "§3Starter"),
    VIP("§f[§6V§c.§6I§c.§6P§f]", " ", "§f", "§6V§c.§6I§c.§6P"),
    OBSIDIAN("§f[§5Obsidian§f]", " ", "§f", "§5Obsidian"),
    LEGEND("§f[§bLegend§f]", " ", "§f", "§bLegend"),
    MASTER_I("§f[§cMaster§f]", " | ", "§f", "§cMaster §fI"),
    MASTER_II("§f[§cMaster§f]", " || ", "§f", "§cMaster §fII"),
    MASTER_III("§f[§cMaster§f]", " ||| ", "§f", "§cMaster §fIII");

    private String prefix;
    private String suffix;
    private String nameColor;
    private String rankName;

    Ranks(String prefix, String suffix, String nameColor, String rankName)
    {
        this.prefix = prefix;
        this.suffix = suffix;
        this.nameColor = nameColor;
        this.rankName = rankName;
    }

    public static Ranks getRank(String rank) {return Ranks.valueOf(rank);}

    public String getPrefix() {return prefix;}
    public String getSuffix() {return suffix;}
    public String getNameColor() {return nameColor;}
    public String getRankName() {return rankName;}

    public static boolean exists(String rank)
    {
        String rankName = rank.toUpperCase();
        List<String> ranksNames = new ArrayList<>();
        for(Ranks r : Ranks.values())
        {
            ranksNames.add(r.name());
        }
        return ranksNames.contains(rankName);
    }

    public static List<Ranks> getAllRanks() {return Arrays.asList(values());}
}
