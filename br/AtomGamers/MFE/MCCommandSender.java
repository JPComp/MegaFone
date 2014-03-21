package br.AtomGamers.MFE;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MCCommandSender extends SystemManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String str, String[] args) {

        if (!(cs instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("§b[MegaFone] §cComandos bloqueados no Console!");
            return true;
        }

        Player sender = (Player) cs;

        if (cmd.getName().equalsIgnoreCase("megafone")) {
            if (sender.hasPermission("megafone.use")) {
                if (args.length == 0) {
                    sender.sendMessage("§b[MegaFone] §f/megafone (mensagem..)");
                } else if (args.length > 0) {
                    String msg = "";
                    for (String next : args) {
                        msg += " " + next;
                    }
                    getSystemManager(sender, msg);
                }
            } else {
                sender.sendMessage("§b[MegaFone] §cVocê não está autorizado(a).");
            }
        }

        return false;
    }

}
