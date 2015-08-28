package com.annamukhina.practice.core.bundle;

import java.util.*;

/**
 * @author anna_mukhina
 */
public class Events {
    private final List<Concert> concerts;

    public Events() {
        concerts = new ArrayList<>();
        concerts.add(new Concert("27.08.2015", "The Depot", "Salt Lake City, UT"));
        concerts.add(new Concert("28.08.2015", "The Fillmore Auditorium", "Denver, CO"));
        concerts.add(new Concert("28.08.2015", "Route 66 Casino", "Albuquerque, NM"));
        concerts.add(new Concert("01.09.2015", "Rialto Theatre", "Tucson, AZ"));
    }

    public void addEvent(final Concert concert) {
        concerts.add(concert);
    }

    public void removeEvent(Concert concert) {
        concerts.remove(concert);
    }

    public List<Concert> getConcerts() {
        return concerts;
    }
}
