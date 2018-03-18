package io.github.patthomasrick.moby.commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

import java.util.List;

public class VoiceLeaveCommand extends Command {

    public VoiceLeaveCommand() {
        this.aliases = new String[]{"voiceleave", "leavvoice", "leave"};
        this.helpStr = "Leave all voice channels.";
        this.usageStr = "!voiceleave";
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        List<IVoiceChannel> voice_list = event.getClient().getConnectedVoiceChannels();
        for (IVoiceChannel c : voice_list) {
            System.out.println(c.getName());
            c.leave();
        }
        event.getMessage().reply("Left channels.");
    }
}
