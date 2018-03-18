package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.CommandListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;

import java.util.List;

public class RestartCommand extends Command {

    public RestartCommand() {
        this.aliases = new String[]{"restart"};
        this.helpStr = "Restarts the bot. Admins only.";
        this.usageStr = "!restart";
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        // make sure issuer is admin
        if (event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.ADMINISTRATOR)) {
            event.getMessage().reply("Restarting");
            System.exit(0);
        } else {
            event.getMessage().reply(CommandListener.REPLY_NOT_ADMIN);
        }
    }
}
