package fr.tmmods.tmapi.spigot.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

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

public class InventoryManager
{
    private Player player;
    private ItemStack[] items = new ItemStack[40];
    private ItemStack[] item = new ItemStack[40];

    public InventoryManager(Player player)
    {
        this.player = player;
    }

    public ItemStack[] getItems()
    {
        return items;
    }
    public ItemStack[] getItem()
    {
        return item;
    }

    public void saveItem(int slot)
    {
        ItemStack it = player.getInventory().getItem(slot);
        if(it != null)
        {
            if(Arrays.stream(item).findFirst().isPresent())item = new ItemStack[40];
            item[slot] = it;
        }
    }

    public void setItem(int slot)
    {
        ItemStack it = item[slot];
        item = new ItemStack[40];
        if(it != null)
        {
            player.getInventory().setItem(slot, it);
        }
    }

    public void saveInventory()
    {
        for(int slot = 0; slot < 40; slot++)
        {
            ItemStack it = player.getInventory().getItem(slot);
            if(it != null)
            {
                items[slot] = it;
            }
        }
    }

    public void setInventory()
    {
        for(int slot = 0; slot < 40; slot++)
        {
            ItemStack it = items[slot];
            if(it != null)
            {
                player.getInventory().setItem(slot, it);
            }
        }
        items = new ItemStack[40];
    }
}
