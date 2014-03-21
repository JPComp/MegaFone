package br.AtomGamers.MFE;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MegaFone extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!(new File(getDataFolder(), "config.yml")).exists()) {
            try {
                saveResource("config_template.yml", false);
                File file2 = new File(getDataFolder(), "config_template.yml");
                file2.renameTo(new File(getDataFolder(), "config.yml"));
            } catch (Exception ex) {
                Bukkit.getConsoleSender().sendMessage("§b[MegaFone] §c" + ex.getMessage());
            }
        }
        reloadConfig();
        getCommand("megafone").setExecutor(new MCCommandSender());
        Bukkit.getConsoleSender().sendMessage("§b[MegaFone] §fPlugin inicializado. (Autor=AtomGamers)");
    }

    @Override
    public void onDisable() {
        saveConfig();
        Bukkit.getConsoleSender().sendMessage("§b[MegaFone] §fPlugin finalizado. (Autor=AtomGamers)");
    }

}
