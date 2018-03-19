package io.github.patthomasrick.moby.commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public interface CommandInterface {

    void runCommand(MessageReceivedEvent event, List<String> args);

    String[] getAliases();

    default String getHelpStr() {
        return "Command.";
    }

    default String getUsageStr() {
        return "![command] [args]";
    }

    boolean isAliasedCommand();
}
