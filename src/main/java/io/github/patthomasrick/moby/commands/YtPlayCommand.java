package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.audio.AudioPlayer;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

public class YtPlayCommand extends Command {

    public YtPlayCommand() {
        aliases = new String[]{"ytplay", "play", "yt"};
        helpStr = "Plays an audio stream from the internet.";
        usageStr = "!ytplay link";
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        AudioPlayer.loadAndPlay(event, args.get(0));
    }
}
