package io.github.patthomasrick.moby;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.StatusType;

public class AnnotationListener {
    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        // find log channel
        for (IChannel c : event.getClient().getChannels()) {
            if (c.getLongID() == Moby.DEV_CHANNEL_ID) {
                c.sendMessage("Logged in.");
            }
        }

        // initialize presence
        event.getClient().changePresence(StatusType.ONLINE, ActivityType.WATCHING, "BrainPop.com");
    }
}
