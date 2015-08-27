package com.annamukhina.practice.core.bundle;

import java.util.*;

/**
 * @author anna_mukhina
 */
public class Events {
    private final List<Event> events;

    public Events() {
        events = new ArrayList<>();
        events.add(new Event("27.08.2015", "The Depot", "Salt Lake City, UT"));
        events.add(new Event("28.08.2015", "The Fillmore Auditorium", "Denver, CO"));
        events.add(new Event("28.08.2015", "Route 66 Casino", "Albuquerque, NM"));
        events.add(new Event("01.09.2015", "Rialto Theatre", "Tucson, AZ"));
    }

    public void addEvent(final Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public List<Event> getEvents() {
        return events;
    }
}
