package lol.vifez.praxi.commands.admin.arena;

import lol.vifez.praxi.arena.Arena;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "arena save", permission = "praxi.admin.arena")
public class ArenaSaveCommand {

	public void execute(CommandSender sender) {
		for (Arena arena : Arena.getArenas()) {
			arena.save();
		}

		sender.sendMessage(ChatColor.GREEN + "Saved all arenas!");
	}

}
