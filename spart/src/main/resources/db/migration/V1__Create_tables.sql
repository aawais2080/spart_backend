CREATE TABLE IF NOT EXISTS sport_category (
    id   uuid NOT NULL,
    name varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS municipality (
    id   uuid NOT NULL,
    name varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS spartan (
   id            uuid NOT NULL,
   municipality_id uuid NOT NULL,
   username      varchar(255),
   name          varchar(255),
   email         varchar(255),
   date_of_birth date,
   PRIMARY KEY (id),
   FOREIGN KEY (municipality_id) REFERENCES municipality (id)
);

CREATE TABLE IF NOT EXISTS sport (
    id          uuid NOT NULL,
    sport_category_id uuid NOT NULL,
    name        varchar(255),
    description varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (sport_category_id) REFERENCES sport_category (id)
);

CREATE TABLE IF NOT EXISTS sport_type (
    id          uuid NOT NULL,
    sport_id    uuid NOT NULL,
    name        varchar(255),
    description varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (sport_id) REFERENCES sport (id)
);

CREATE TABLE IF NOT EXISTS location (
    id        uuid NOT NULL,
    municipality_id uuid NOT NULL,
    name        varchar(255),
    address     varchar(255),
    longitude   double precision NOT NULL,
    latitude    double precision NOT NULL,
    description varchar(255),

    PRIMARY KEY (id),
    FOREIGN KEY (municipality_id) REFERENCES municipality (id)
);

CREATE TABLE IF NOT EXISTS sport_club (
    id         uuid NOT NULL,
    location_id uuid NOT NULL,
    name        varchar(255),
    primaryColor varchar(255),
    secondaryColor varchar(255),
    phoneNumber varchar(255),
    email       varchar(255),
    description varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (location_id) REFERENCES location (id)
);

CREATE TABLE IF NOT EXISTS spartan_liked_sport (
    id         uuid NOT NULL,
    sport_id   uuid NOT NULL,
    spartan_id uuid NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (sport_id) REFERENCES sport (id),
    FOREIGN KEY (spartan_id) REFERENCES spartan (id)
);

CREATE TABLE IF NOT EXISTS sport_event (
    id           uuid NOT NULL,
    sport_id     uuid NOT NULL,
    location_id  uuid NOT NULL,
    name         varchar(255),
    description  varchar(255),
    date_time    timestamp,
    price        double precision,
    level        integer,
    maxParticipants integer,
    PRIMARY KEY (id),
    FOREIGN KEY (sport_id) REFERENCES sport (id),
    FOREIGN KEY (location_id) REFERENCES location (id)
);

CREATE TABLE IF NOT EXISTS spartan_in_event (
    id          uuid NOT NULL,
    sport_event_id uuid NOT NULL,
    spartan_id   uuid NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (spartan_id) REFERENCES spartan (id),
    FOREIGN KEY (sport_event_id) REFERENCES sport_event (id)
);

CREATE TABLE IF NOT EXISTS sport_event_comment (
    id          uuid NOT NULL,
    sport_event_id uuid NOT NULL,
    spartan_id   uuid NOT NULL,
    comment     varchar(255),
    date_time   timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (spartan_id) REFERENCES spartan (id),
    FOREIGN KEY (sport_event_id) REFERENCES sport_event (id)
);
