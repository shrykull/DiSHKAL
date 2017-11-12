package net.shrye.dishkal.config

class MessageGrabberRoute {
    private String source = "#general"
    private Set<String> destinations = new HashSet<>()

    String getSource() {
        return source
    }

    Set<String> getDestinations() {
        return destinations
    }

    void setSource(String source) {
        this.source = source
    }

    void setDestinations(Set<String> destinations) {
        this.destinations = destinations
    }
}
