package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.audio.AudioPlayer;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class YtSkipCommand implements Command {

    private String[] aliases = {"ytskip", "stop", "skip"};
    private String helpStr = "Stops/skips the current audio stream.";
    private String usageStr = "!ytskip";

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        AudioPlayer.skipTrack(event.getChannel());
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
