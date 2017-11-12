package net.shrye.dishkal.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties('messageGrabbing')
class MessageGrabberProperties {
    boolean enabled = false
    List<MessageGrabber> grabbers = new ArrayList<>()

    void setMessageGrabbers(List<MessageGrabber> messageGrabbers) {
        this.grabbers = messageGrabbers
    }
}
