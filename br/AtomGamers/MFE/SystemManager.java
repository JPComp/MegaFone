package br.AtomGamers.MFE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class SystemManager {

    protected static List<Player> delay = new ArrayList<Player>();

    protected static FileConfiguration getConfig() {
        File file = new File(getFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    protected static File getFolder() {
        return Bukkit.getPluginManager().getPlugin("MegaFone").getDataFolder();
    }

    protected static ItemStack getNecessaryItem() {
        ItemStack item = getConfig().getItemStack("Item");
        return item;
    }

    protected static String getFormatedString(Player sender, String msg) {
        String format = getConfig().getString("Format");
        format = format.replaceAll("@player", sender.getName());
        format = format.replaceAll("@msg", msg);
        format = format.replaceAll("&", "§");
        return format;
    }

    protected static void getSystemManager(Player sender, String msg) {
        if (delay.contains(sender)) {
            sender.sendMessage("§b[MegaFone] §cAguarde par atilizar este comando novamente.");
        } else {
            if (!(sender.getInventory().contains(getNecessaryItem()))) {
                sender.sendMessage("§b[MegaFone] §fVocê não tem o Item para utilizar este comando!");
            } else {
                Bukkit.broadcastMessage(getFormatedString(sender, msg));
                sender.getInventory().remove(getNecessaryItem());
                addDelayTask(sender);
            }
        }
    }

    protected static void addDelayTask(final Player sender) {
        delay.add(sender);
        int Seconds = getConfig().getInt("SecondsForUseAgain");
        Plugin plugin = Bukkit.getPluginManager().getPlugin("MegaFone");
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                delay.remove(sender);
            }
        }, Seconds * 20L);
    }
}
