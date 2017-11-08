package net.shrye.dishkal.listeners

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class LinkGrabber extends ListenerAdapter {

    @Override
    void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return
        }

        println "$event.channelType > $event.channel $event.author: $event.message.content"
    }
}
