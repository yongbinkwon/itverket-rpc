CREATE TABLE QueuedPlayer
(
    Id   int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Sign varchar(255) NOT NULL,
    Team varchar(255) NOT NULL,
    Processed boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE MatchStatistic
(
    Id   int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Winner varchar(255),
    Loser varchar(255),
    MatchDate timestamp NOT NULL
);

CREATE ROLE cuties WITH LOGIN PASSWORD 'password123';
GRANT SELECT ON TABLE MatchStatistic TO cuties;