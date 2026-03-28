

CREATE TABLE IF NOT EXISTS venues (
    venue_id SERIAL PRIMARY KEY NOT NULL ,
    venue_name VARCHAR(100) NOT NULL ,
    location VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
    event_id SERIAL PRIMARY KEY NOT NULL ,
    event_name VARCHAR(50) NOT NULL ,
    event_date DATE NOT NULL ,
    venue_id INT NOT NULL ,
    CONSTRAINT fk_venue_id FOREIGN KEY (venue_id) REFERENCES venues (venue_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS attendees (
    attendee_id SERIAL PRIMARY KEY NOT NULL ,
    attendee_name VARCHAR(100) NOT NULL ,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS event_attendee (
    event_id INT NOT NULL ,
    attendee_id INT NOT NULL ,
    CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES events (event_id) ON UPDATE CASCADE  ON DELETE  CASCADE ,
    CONSTRAINT fk_attendee_id FOREIGN KEY (attendee_id) REFERENCES attendees (attendee_id) ON UPDATE CASCADE ON DELETE CASCADE
);