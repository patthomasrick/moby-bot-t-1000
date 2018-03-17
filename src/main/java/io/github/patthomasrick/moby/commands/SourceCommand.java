package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.Moby;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class SourceCommand implements Command {

    private String[] aliases = {"source", "sauce"};
    private String helpStr = "Gives a link to this bot's source code.";
    private String usageStr = "!source";

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        Moby.sendMessage(event.getChannel(),
                String.format(
                        "%s Here is my source code: https://github.com/patthomasrick/moby-bot-t-1000",
                        event.getAuthor().mention()));
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }

    @Override
    public String getHelpStr() {
        return helpStr;
    }

    @Override
    public String getUsageStr() {
        return usageStr;
    }
}
