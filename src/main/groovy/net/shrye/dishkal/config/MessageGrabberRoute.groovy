package net.shrye.dishkal.config

import org.springframework.boot.context.properties.ConfigurationProperties

//@ConfigurationProperties(prefix = 'routes')
class MessageGrabberRoute {
    private String source = "#general"
    private List<String> destinations = new ArrayList<>()

    String getSource() {
        return source
    }

    List<String> getDestinations() {
        return destinations
    }

    void setSource(String source) {
        this.source = source
    }

    void setDestinations(List<String> destinations) {
        this.destinations = destinations
    }
}
