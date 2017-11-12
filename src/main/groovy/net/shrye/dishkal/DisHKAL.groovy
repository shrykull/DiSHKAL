package net.shrye.dishkal

import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.shrye.dishkal.config.AuthProperties
import net.shrye.dishkal.config.MessageGrabberProperties
import net.shrye.dishkal.listeners.LinkGrabber
import net.shrye.dishkal.listeners.StartupGreeting
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableConfigurationProperties([AuthProperties, MessageGrabberProperties])
class DisHKAL {

    @Autowired
    AuthProperties auth

    @Autowired
    MessageGrabberProperties messageGrabber

    @Bean
    JDA jda() {
        assert auth.token.length() > 10
        assert messageGrabber.grabbers[0].grabberName != "grabber"
        assert messageGrabber.grabbers.routes.destinations.size() > 1
        JDA jda = new JDABuilder(auth.accountType).setToken(auth.token).buildAsync()
        jda.addEventListener(new StartupGreeting())
        jda.addEventListener(new LinkGrabber(messageGrabber))
        jda
    }

    static void main(String... args) {
        SpringApplication.run(DisHKAL)
    }
}
