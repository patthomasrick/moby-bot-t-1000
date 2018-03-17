package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.Moby;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;
import java.util.Random;

public class ChoiceCommand implements Command {

    private String[] aliases = {"choice", "choose"};
    private String helpStr = "Choose a random word after the command";
    private String usageStr = "!choice arg0 arg1 arg2 ... argn";

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        Random r = new Random();
        int randInt = r.nextInt(args.size());

        Moby.sendMessage(event.getChannel(), String.format("%s %s", event.getAuthor().mention(), args.get(randInt)));
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
