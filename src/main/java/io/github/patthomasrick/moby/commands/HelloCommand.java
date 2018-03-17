package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.Moby;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class HelloCommand implements Command {

    private String[] aliases = {"hello", "hi"};
    private String helpStr = "Says hello back. Like ping-pong test.";
    private String usageStr = "!hello";

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        Moby.sendMessage(event.getChannel(), String.format("Hello %s!", event.getAuthor().mention()));
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
