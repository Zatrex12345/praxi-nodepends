package lol.vifez.praxi.commands.user.party;

import lol.vifez.praxi.Locale;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = { "p", "p help", "party", "party help" })
public class PartyHelpCommand {

	public void execute(Player player) {
		Locale.PARTY_HELP.formatLines().forEach(player::sendMessage);
	}

}
