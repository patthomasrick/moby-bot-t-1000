package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.Moby;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class SayCommand extends Command {

    public SayCommand() {
        this.aliases = new String[]{"say", "echo"};
        this.helpStr = "Repeats all arguments.";
        this.usageStr = "!say [stuff]";
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        // concat args
        String msg = "";
        for (String s : args) {
            msg = String.format("%s %s", msg, s);
        }

        // remove original message
        if (!event.getMessage().isDeleted()) {
            event.getMessage().delete();
        }

        // send new message
        Moby.sendMessage(event.getChannel(), msg);
    }
}
