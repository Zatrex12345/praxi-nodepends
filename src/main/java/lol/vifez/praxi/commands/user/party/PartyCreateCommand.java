package lol.vifez.praxi.commands.user.party;

import lol.vifez.praxi.Locale;
import lol.vifez.praxi.party.Party;
import lol.vifez.praxi.profile.Profile;
import lol.vifez.praxi.profile.ProfileState;
import lol.vifez.praxi.profile.hotbar.Hotbar;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = { "p create", "party create" })
public class PartyCreateCommand {

	public void execute(Player player) {
		if (player.hasMetadata("frozen")) {
			player.sendMessage(CC.RED + "You cannot create a party while frozen.");
			return;
		}

		Profile profile = Profile.getByUuid(player.getUniqueId());

		if (profile.getParty() != null) {
			player.sendMessage(CC.RED + "You already have a party.");
			return;
		}

		if (profile.getState() != ProfileState.LOBBY) {
			player.sendMessage(CC.RED + "You must be in the lobby to create a party.");
			return;
		}

		profile.setParty(new Party(player));

		Hotbar.giveHotbarItems(player);

		player.sendMessage(Locale.PARTY_CREATE.format());
	}

}
