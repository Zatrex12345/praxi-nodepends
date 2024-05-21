package lol.vifez.praxi.commands.admin.arena;

import lol.vifez.praxi.arena.Arena;
import lol.vifez.praxi.arena.impl.SharedArena;
import lol.vifez.praxi.arena.selection.Selection;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "arena create", permission = "praxi.admin.arena")
public class ArenaCreateCommand {

	public void execute(Player player, String arenaName) {
		if (Arena.getByName(arenaName) == null) {
			Selection selection = Selection.createOrGetSelection(player);

			if (selection.isFullObject()) {
				Arena arena = new SharedArena(arenaName, selection.getPoint1(), selection.getPoint2());
				Arena.getArenas().add(arena);

				player.sendMessage(CC.GOLD + "Created new arena \"" + arenaName + "\"");
			} else {
				player.sendMessage(CC.RED + "Your selection is incomplete.");
			}
		} else {
			player.sendMessage(CC.RED + "An arena with that name already exists.");
		}
	}

}
