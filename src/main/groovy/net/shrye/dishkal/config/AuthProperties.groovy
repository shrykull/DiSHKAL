package net.shrye.dishkal.config

import net.dv8tion.jda.core.AccountType
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "auth")
class AuthProperties {
    String token
    AccountType accountType
}
