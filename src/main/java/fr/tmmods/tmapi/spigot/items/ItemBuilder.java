package fr.tmmods.tmapi.spigot.items;

import fr.tmmods.tmapi.spigot.TMSpigotAPI;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Arrays;
import java.util.UUID;

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

public class ItemBuilder
{
    private TMSpigotAPI main;
    private ItemStack it;
    private ItemMeta itM;

    public ItemBuilder(Material material) {this(material, 1);}

    public ItemBuilder(ItemStack it)
    {
        this.it = it;
        this.itM = it.getItemMeta();
    }

    public ItemBuilder(Material material, int amount)
    {
        this.it = new ItemStack(material, amount);
        this.itM = it.getItemMeta();
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder(Material material, int amount, short meta)
    {
        this.it = new ItemStack(material, amount, meta);
        this.itM = it.getItemMeta();
    }

    public ItemBuilder(TMSpigotAPI main) {this.main = main;}

    public ItemBuilder clone() throws CloneNotSupportedException
    {
        ItemBuilder clone = (ItemBuilder) super.clone();
        return new ItemBuilder(it);
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setDurability(short durability)
    {
        it.setDurability(durability);
        return this;
    }

    public ItemBuilder setName(String name)
    {
        itM.setDisplayName(name);
        it.setItemMeta(itM);
        return this;
    }

    public ItemBuilder setLocalizedName(String name)
    {
        itM.setLocalizedName(name);
        it.setItemMeta(itM);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level)
    {
        itM.addEnchant(ench, level, true);
        it.setItemMeta(itM);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level)
    {
        it.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench)
    {
        it.removeEnchantment(ench);
        return this;
    }

    @Deprecated
    public ItemBuilder setSkullOwner(String owner)
    {
        try {
            SkullMeta itM = (SkullMeta) it.getItemMeta();
            assert itM != null;
            itM.setOwner(owner);
            it.setItemMeta(itM);
        } catch (ClassCastException e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public ItemBuilder setSkullOwningPlayer(OfflinePlayer owner)
    {
        try {
            SkullMeta itM = (SkullMeta) it.getItemMeta();
            assert itM != null;
            itM.setOwningPlayer(owner);
            it.setItemMeta(itM);
        } catch (ClassCastException e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public ItemBuilder setSkullOwnerProfile(String url) throws MalformedURLException {
        SkullMeta itM = (SkullMeta) it.getItemMeta();
        assert itM != null;
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        profile.getTextures().setSkin(URI.create(url).toURL());
        itM.setOwnerProfile(profile);
        it.setItemMeta(itM);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setInfinityDurability()
    {
        it.setDurability(Short.MAX_VALUE);
        return this;
    }

    public ItemBuilder setLore(String... lore)
    {
        ItemMeta itM = it.getItemMeta();
        assert itM != null;
        itM.setLore(Arrays.asList(lore));
        it.setItemMeta(itM);
        return this;
    }

    public ItemBuilder setFlags(ItemFlag... flags)
    {
        ItemMeta itM = it.getItemMeta();
        assert itM != null;
        itM.addItemFlags(flags);
        it.setItemMeta(itM);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setWoolColor(DyeColor color)
    {
        if (!it.getType().equals(Material.LEGACY_WOOL))
            return this;
        this.it.setDurability(color.getWoolData());
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color)
    {
        try {
            LeatherArmorMeta itM = (LeatherArmorMeta) it.getItemMeta();
            assert itM != null;
            itM.setColor(color);
            it.setItemMeta(itM);
        } catch (ClassCastException e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public ItemStack toItemStack() {return it;}
}
