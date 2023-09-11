CREATE TABLE QueuedPlayer
(
    Id   int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Sign varchar(255) NOT NULL,
    Team varchar(255) NOT NULL,
    Processed boolean NOT NULL DEFAULT FALSE
);