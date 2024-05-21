package lol.vifez.praxi.commands.event.user;

import lol.vifez.praxi.event.game.EventGame;
import lol.vifez.praxi.profile.Profile;
import lol.vifez.praxi.profile.ProfileState;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "event leave")
public class EventLeaveCommand {

	public void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());

		if (profile.getState() == ProfileState.EVENT) {
			EventGame.getActiveGame().getGameLogic().onLeave(player);
		} else {
			player.sendMessage(CC.RED + "You are not in an event.");
		}
	}

}
