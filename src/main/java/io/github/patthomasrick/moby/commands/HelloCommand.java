package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.Moby;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class HelloCommand extends Command {

    public HelloCommand() {
        this.aliases = new String[]{"hello", "hi"};
        this.helpStr = "Says hello back. Like ping-pong test.";
        this.usageStr = "!hello";
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        Moby.sendMessage(event.getChannel(), String.format("Hello %s!", event.getAuthor().mention()));
    }
}
