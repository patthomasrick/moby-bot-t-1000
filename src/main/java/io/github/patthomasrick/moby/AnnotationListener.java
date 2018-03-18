package io.github.patthomasrick.moby;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;

public class AnnotationListener {
    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        System.out.println("logged in");

        // find log channel
        for (IChannel c : event.getClient().getChannels()) {
            if (c.getLongID() == Moby.DEV_CHANNEL_ID) {
                c.sendMessage("Logged in.");
            }
        }
    }
//
//    public void onMessageReceivedEvent(MessageReceivedEvent event) { // This method is NOT called because it doesn't have the @EventSubscriber annotation
//        bar(); // Never called!
//    }

}
