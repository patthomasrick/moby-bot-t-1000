package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.Moby;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class SourceCommand extends Command {

    public SourceCommand() {
        aliases = new String[]{"source", "sauce"};
        helpStr = "Gives a link to this bot's source code.";
        usageStr = "!source";
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        Moby.sendMessage(event.getChannel(),
                String.format(
                        "%s Here is my source code: https://github.com/patthomasrick/moby-bot-t-1000",
                        event.getAuthor().mention()));
    }
}
