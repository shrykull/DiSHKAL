package net.shrye.dishkal.listeners

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import org.springframework.stereotype.Component

import static net.shrye.dishkal.util.Decider.decide

@Component
class DecideListener extends ListenerAdapter {

    @Override
    void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return
        }

        if (event.message.content.startsWith('!decide ')) {
            String strippedContent = event.message.strippedContent
            String user = event.author.name + event.author.discriminator
            String alternatives = strippedContent.subSequence(8, strippedContent.length())
            event.textChannel.sendMessage("Du solltest dich ${decide(alternatives, user)} entscheiden").queue()
        }
    }
}
