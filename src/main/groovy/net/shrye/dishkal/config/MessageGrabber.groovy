package net.shrye.dishkal.config

class MessageGrabber {
    private String grabberName = "grabber"
    private String messageRegex = /.+(https?:\/\/).+/
    private List<MessageGrabberRoute> routes = new ArrayList<>()

    List<MessageGrabberRoute> getRoutes() {
        return routes
    }

    String getGrabberName() {
        return grabberName
    }

    String getMessageRegex() {
        return messageRegex
    }

    void setGrabberName(String grabberName) {
        this.grabberName = grabberName
    }

    void setMessageRegex(String messageRegex) {
        this.messageRegex = messageRegex
    }

    void setRoutes(List<MessageGrabberRoute> routes) {
        this.routes = routes
    }
}
