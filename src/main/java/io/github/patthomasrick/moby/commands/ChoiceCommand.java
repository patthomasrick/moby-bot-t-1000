package io.github.patthomasrick.moby.commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;
import java.util.Random;

public class ChoiceCommand extends Command {

    public ChoiceCommand() {
        aliases = new String[]{"choice", "choose"};
        helpStr = "Choose a random word after the command.";
        usageStr = "!choice arg0 arg1 ... argn";
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        Random r = new Random();
        int randInt = r.nextInt(args.size());

        event.getMessage().reply(args.get(randInt));
    }
}
