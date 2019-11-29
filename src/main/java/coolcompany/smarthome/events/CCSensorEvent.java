package coolcompany.smarthome.events;

public class CCSensorEvent {
    private final String eventType;
    private final String objectId;

    /**
     * Default constructor
     * @param eventType - defines type of event.
     * @param objectId - id of the object which fired the event (door/lightswitch)
     */
    public CCSensorEvent(String eventType, String objectId) {
        this.eventType = eventType;
        this.objectId = objectId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getObjectId() {
        return objectId;
    }
}
