package net.shrye.dishkal.config

import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.shrye.dishkal.listeners.LinkGrabber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties([AuthProperties])
class Bot {

    @Autowired
    AuthProperties auth

    @Bean
    JDA jda() {
        JDA jda = new JDABuilder(auth.accountType).setToken(auth.token).buildAsync()
        jda.addEventListener(new LinkGrabber())
        jda
    }
}
