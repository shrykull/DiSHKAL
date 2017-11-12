package net.shrye.dishkal.listeners

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import net.shrye.dishkal.config.AuthProperties
import net.shrye.dishkal.config.MessageGrabberProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties([AuthProperties, MessageGrabberProperties])
class LinkGrabber extends ListenerAdapter {

    MessageGrabberProperties messageGrabberProperties

    //TODO: autowired injection would be nice
    LinkGrabber(MessageGrabberProperties messageGrabberProperties) {
        this.messageGrabberProperties = messageGrabberProperties
    }
    @Override
    void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return
        }

        println "${messageGrabberProperties}"
    }
}
