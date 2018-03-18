package io.github.patthomasrick.moby.commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

import java.util.List;

public class VoiceJoinCommand extends Command {

    public VoiceJoinCommand() {
        this.aliases = new String[]{"voicejoin", "joinvoice", "join"};
        this.helpStr = "Join your current voice channel for audio playback.";
        this.usageStr = "!voicejoin";
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        IVoiceChannel channel = event.getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel();
        channel.join();
        event.getMessage().reply("Joined channel " + channel.getName());
    }
}
