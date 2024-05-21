package lol.vifez.praxi.commands.admin.arena;

import lol.vifez.praxi.arena.Arena;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "arena delete", permission = "praxi.admin.arena")
public class ArenaDeleteCommand {

	public void execute(Player player, Arena arena) {
		if (arena != null) {
			arena.delete();

			player.sendMessage(CC.GOLD + "Deleted arena \"" + arena.getName() + "\"");
		} else {
			player.sendMessage(CC.RED + "An arena with that name does not exist.");
		}
	}

}
