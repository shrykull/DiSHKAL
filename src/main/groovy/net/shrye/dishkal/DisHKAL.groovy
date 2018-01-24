package net.shrye.dishkal

import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.shrye.dishkal.config.AuthProperties
import net.shrye.dishkal.config.MessageGrabberProperties
import net.shrye.dishkal.listeners.DecideListener
import net.shrye.dishkal.listeners.LinkGrabber
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
        JDA jda = new JDABuilder(auth.accountType).setToken(auth.token).buildAsync()
        jda.addEventListener(new DecideListener())
        jda.addEventListener(new LinkGrabber(messageGrabber))
        jda
    }

    static void main(String... args) {
        SpringApplication.run(DisHKAL)
    }
}
