package lol.vifez.praxi.commands.admin.general;

import lol.vifez.praxi.Praxi;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = { "setspawn" }, permission = "praxi.setspawn")
public class SetSpawnCommand
{
    public void execute(Player player) {
        Praxi.get().getEssentials().setSpawn(player.getLocation());
        player.sendMessage(CC.translate("&bSpawn set successfully!"));
    }
}
