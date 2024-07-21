package fr.tmmods.tmapi.spigot.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

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

public class GuiManager implements Listener 
{
    protected JavaPlugin plugin;
    private Map<Class<? extends IGuiBuilder>, IGuiBuilder> mnBuilder = new HashMap<>();

    public GuiManager(JavaPlugin plugin) {this.plugin = plugin;}

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        if(event.getCurrentItem() == null || event.isCancelled()) return;

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        String invName = event.getView().getTitle();
        ItemStack current = event.getCurrentItem();
 
        mnBuilder.values().stream()
        .filter(menu -> invName.equalsIgnoreCase(menu.name()))
        .forEach(menu -> {
            menu.onClick(player, inv, current, event.getSlot());
            event.setCancelled(true);
        });
    }

    public void addMenu(IGuiBuilder m) {mnBuilder.put(m.getClass(), m);}
 
    public void open(Player player, Class<? extends IGuiBuilder> gClass)
    {
        if(!mnBuilder.containsKey(gClass)) return;
 
        IGuiBuilder menu = mnBuilder.get(gClass);
        Inventory inv = Bukkit.createInventory(null, menu.getSize(), menu.name());
        menu.contents(player, inv);
        
        new BukkitRunnable() 
        {    
            @Override
            public void run() {player.openInventory(inv);}
            
        }.runTaskLater(plugin, 1);
    }

    public Map<Class<? extends IGuiBuilder>, IGuiBuilder> getMnBuilder() {return mnBuilder;}
}
