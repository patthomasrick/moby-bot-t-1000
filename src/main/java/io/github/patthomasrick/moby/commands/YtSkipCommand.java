package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.audio.AudioPlayer;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class YtSkipCommand extends Command {

    public YtSkipCommand() {
        aliases = new String[]{"ytskip", "stop", "skip"};
        helpStr = "Stops/skips the current audio stream.";
        usageStr = "!ytskip";
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        AudioPlayer.skipTrack(event.getChannel());
    }
}
