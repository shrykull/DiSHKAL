package net.shrye.dishkal.listeners

import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.shrye.dishkal.config.MessageGrabber
import net.shrye.dishkal.config.MessageGrabberProperties
import net.shrye.dishkal.config.MessageGrabberRoute
import spock.lang.Specification

class LinkGrabberTest extends Specification {

    def "messages from sources are correctly distributed among destinations"() {
        given:
        List<MessageGrabberRoute> routes = [new MessageGrabberRoute(source: '#s', destinations: ['#wtf', '#foo', '#wtf'])]
        List<MessageGrabber> grabbers = [ new MessageGrabber(routes: routes) ]

        MessageGrabberProperties messageGrabberProperties = Mock(MessageGrabberProperties) {
            getEnabled() >> true
            getGrabbers() >> grabbers
        }
        TextChannel sourceChannel = Mock(TextChannel) {
            getName() >> '#s'
        }
        TextChannel destinationChannel = Mock(TextChannel) {
            canTalk() >> true
            sendMessage(_) >> null
        }
        MessageReceivedEvent event = Mock(MessageReceivedEvent) {
            getJDA() >> Mock(JDA) {
                getTextChannelById(_) >> destinationChannel
            }
            getAuthor() >> Mock(User) {
                getName() >> 'TestUsername'
            }
            getTextChannel() >> sourceChannel
            getMessage() >> Mock(Message) { getContent() >> 'some message' }
        }

        LinkGrabber linkGrabber = new LinkGrabber(messageGrabberProperties)

        when:
        linkGrabber.onMessageReceived(event)

        then:
        2 * destinationChannel.sendMessage(_)
    }
}
