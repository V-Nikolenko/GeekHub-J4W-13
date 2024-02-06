create table history
(
    record_id     serial primary key,
    user_id       integer                  not null references users ( user_id ),
    operation     varchar(16)              not null,
    algorithm     varchar(16)              not null,
    original_text text                     not null,
    encoded_text  text                     not null,
    date          timestamp with time zone not null
);
