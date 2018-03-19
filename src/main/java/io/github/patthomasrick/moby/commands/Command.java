package io.github.patthomasrick.moby.commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class Command implements CommandInterface {

    String[] aliases;
    String helpStr;
    String usageStr;

    boolean isAliasedCommand = false;

    public Command() {
        aliases = new String[]{"command"};
        helpStr = "Command.";
        usageStr = "!command arg1 arg2 ... argn";
    }

    public void runCommand(MessageReceivedEvent event, List<String> args) {
        return;
    }

    @Override
    public String toString() {
        return this.getUsageStr();
    }

    @Override
    public String[] getAliases() {
        return this.aliases;
    }

    @Override
    public String getHelpStr() {
        return this.helpStr;
    }

    @Override
    public String getUsageStr() {
        return this.usageStr;
    }

    @Override
    public boolean isAliasedCommand() {
        return this.isAliasedCommand;
    }
}
