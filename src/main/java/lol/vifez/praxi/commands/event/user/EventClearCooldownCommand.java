package lol.vifez.praxi.commands.event.user;

import lol.vifez.praxi.event.game.EventGame;
import lol.vifez.praxi.util.Cooldown;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandMeta(label = { "event clearcooldown", "event clearcd" }, permission = "praxi.admin.event")
public class EventClearCooldownCommand {

	public void execute(CommandSender sender) {
		EventGame.setCooldown(new Cooldown(0));
		sender.sendMessage(ChatColor.GREEN + "You cleared the event cooldown.");
	}

}
