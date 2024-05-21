package lol.vifez.praxi.commands.admin.arena;

import lol.vifez.praxi.arena.Arena;
import lol.vifez.praxi.kit.Kit;
import lol.vifez.praxi.util.command.command.CPL;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "arena add kit", permission = "praxi.admin.arena")
public class ArenaAddKitCommand {

	public void execute(CommandSender sender, @CPL("arena") Arena arena, @CPL("kit") Kit kit) {
		if (arena == null) {
			sender.sendMessage(ChatColor.RED + "An arena with that name does not exist.");
			return;
		}

		if (kit == null) {
			sender.sendMessage(ChatColor.RED + "A kit with that name does not exist.");
			return;
		}

		arena.getKits().add(kit.getName());
		arena.save();

		sender.sendMessage(ChatColor.GOLD + "Added kit \"" + kit.getName() +
		                   "\" to arena \"" + arena.getName() + "\"");
	}

}
