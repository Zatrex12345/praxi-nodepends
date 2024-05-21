package lol.vifez.praxi.commands.event.map;

import lol.vifez.praxi.event.game.map.EventGameMap;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.command.command.CPL;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "event map delete", permission = "praxi.admin.event")
public class EventMapDeleteCommand {

	public void execute(Player player, @CPL("map") EventGameMap gameMap) {
		if (gameMap == null) {
			player.sendMessage(CC.RED + "An event map with that name already exists.");
			return;
		}

		gameMap.delete();

		EventGameMap.getMaps().remove(gameMap);

		player.sendMessage(CC.GREEN + "You successfully deleted the event map \"" + gameMap.getMapName() + "\".");
	}

}
