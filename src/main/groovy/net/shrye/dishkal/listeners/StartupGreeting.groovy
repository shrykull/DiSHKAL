package net.shrye.dishkal.listeners

import net.dv8tion.jda.core.events.Event
import net.dv8tion.jda.core.events.ReadyEvent
import net.dv8tion.jda.core.hooks.EventListener

class StartupGreeting implements EventListener {
    @Override
    void onEvent(Event event) {
        if (event instanceof ReadyEvent)
            println "Bot ready"
    }
}
