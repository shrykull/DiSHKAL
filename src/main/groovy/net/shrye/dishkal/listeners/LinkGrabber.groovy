package net.shrye.dishkal.listeners

import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import net.shrye.dishkal.config.AuthProperties
import net.shrye.dishkal.config.MessageGrabberProperties
import net.shrye.dishkal.config.MessageGrabberRoute
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties([AuthProperties, MessageGrabberProperties])
class LinkGrabber extends ListenerAdapter {

    private final Logger log = LoggerFactory.getLogger(this.getClass())

    MessageGrabberProperties messageGrabberProperties

    //TODO: autowired injection would be nice
    LinkGrabber(MessageGrabberProperties messageGrabberProperties) {
        this.messageGrabberProperties = messageGrabberProperties
    }

    @Override
    void onMessageReceived(MessageReceivedEvent event) {
        if ((!messageGrabberProperties.enabled) || (event.getAuthor().isBot())) {
            return
        }

        messageGrabberProperties.grabbers.routes.flatten().stream()
            .filter { route -> ((MessageGrabberRoute)route).source == event.textChannel.name }
            .each { route ->
                ((MessageGrabberRoute)route).destinations.each { destination ->
                    def destinations = event.getJDA().getTextChannelsByName(destination, true)
                    if (destinations.empty) {
                        log.info("MessageGrabber Configuration error: destination channel '$destination' not found")
                        return
                    }
                    TextChannel targetChannel = destinations.first()
                    if (targetChannel.canTalk()) {
                        targetChannel.sendMessage("[${route.source}] ${event.author.name}> ${event.message.content}").complete()
                    }
                }
            }
    }
}
