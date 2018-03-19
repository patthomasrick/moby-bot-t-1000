package io.github.patthomasrick.moby.commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

/**
 * The Rubrik of commands. Takes on the form of another command to cause headaches.
 */
public class AliasedCommand extends Command {

    private Command parentCommand;
    private List<String> parentArgs;

    AliasedCommand(String aliasName, Command command, List<String> args) {
        this.aliases = new String[]{aliasName};
        this.helpStr = String.format("!%s", command.getAliases()[0]);
        this.usageStr = String.format("!%s", aliasName);

        this.isAliasedCommand = true;

        this.parentCommand = command;
        this.parentArgs = args;

        for (String s : this.parentArgs) {
            this.helpStr = String.format("%s %s", this.helpStr, s);
        }
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        parentCommand.runCommand(event, this.parentArgs);
    }

    @Override
    public String toString() {
        String argsString = "";
        for (String s : this.parentArgs) {
            argsString = String.format("%s %s", argsString, s);
        }

        return String.format("%s %s %s", this.aliases[0], this.parentCommand.getAliases()[0], argsString);
    }
}
