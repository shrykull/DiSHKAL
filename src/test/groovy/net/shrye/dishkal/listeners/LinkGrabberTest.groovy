package net.shrye.dishkal.listeners

import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.requests.RestAction
import net.shrye.dishkal.config.MessageGrabber
import net.shrye.dishkal.config.MessageGrabberProperties
import net.shrye.dishkal.config.MessageGrabberRoute
import spock.lang.Specification

class LinkGrabberTest extends Specification {

    List<MessageGrabberRoute> routes
    List<MessageGrabber> grabbers

    MessageGrabberProperties messageGrabberProperties
    TextChannel sourceChannel
    TextChannel destinationChannel
    MessageReceivedEvent event
    String message = 'some matching message'
    String regex = /.+message/

    LinkGrabber linkGrabber

    def setup() {
        routes = [new MessageGrabberRoute(source: 's', destinations: ['wtf', 'foo', 'wtf'])]
        grabbers = [new MessageGrabber(routes: routes, messageRegex: regex)]
        messageGrabberProperties = Mock(MessageGrabberProperties) {
            getEnabled() >> true
            getGrabbers() >> grabbers
        }
        sourceChannel = Mock(TextChannel) {
            getName() >> 's'
        }
        destinationChannel = Mock(TextChannel) {
            canTalk() >> true
        }
        event = Mock(MessageReceivedEvent) {
            getJDA() >> Mock(JDA) {
                getTextChannelsByName(_ as String, _) >> [destinationChannel]
            }
            getAuthor() >> Mock(User) {
                getName() >> 'TestUsername'
            }
            getTextChannel() >> sourceChannel
            getMessage() >> Mock(Message) {
                getContent() >> "${-> message}"
                getRawContent() >> "${-> message}"
            }
        }

        linkGrabber = new LinkGrabber(messageGrabberProperties)
    }

    def "messages from sources are correctly distributed among destinations"() {
        when:
        linkGrabber.onMessageReceived(event)

        then:
        2 * destinationChannel.sendMessage(_ as String) >> Mock(RestAction)
    }

    def "only matching messages are forwarded"() {
        given:
        message = 'something we don`t care about'

        when:
        linkGrabber.onMessageReceived(event)

        then:
        0 * destinationChannel.sendMessage(_)

    }
}
