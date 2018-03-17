package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.audio.AudioPlayer;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class YtPlayCommand implements Command {

    private String[] aliases = {"ytplay", "play", "yt"};
    private String helpStr = "Plays an audio stream from the internet.";
    private String usageStr = "!ytplay link";

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        AudioPlayer.loadAndPlay(event, args.get(0));
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
