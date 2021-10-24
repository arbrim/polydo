create table users
(
    id       bigserial not null
        constraint users_pkey
            primary key,
    name     varchar(255),
    password varchar(255),
    surname  varchar(255),
    username varchar(255)
);

create table userdetails
(
    id       serial  not null
        constraint userdetails_pkey
            primary key,
    active   boolean not null,
    password varchar(255),
    roles    varchar(255),
    username varchar(255)
);

create table task_list
(
    id          bigserial not null
        constraint task_list_pkey
            primary key,
    description varchar(255),
    name        varchar(255)
);

create table task
(
    id            bigserial not null
        constraint task_pkey
            primary key,
    completed     boolean,
    description   varchar(255),
    expected_date timestamp,
    title         varchar(255),
    task_list_id  bigint
        constraint fknoop17o52c1kg73bskj5o4rdx
            references task_list
);

create table subtask
(
    id            bigserial not null
        constraint subtask_pkey
            primary key,
    completed     boolean,
    description   varchar(255),
    expected_date timestamp,
    title         varchar(255),
    task_id       bigint
        constraint fkqogp98iwmph591yu58frt48ud
            references task
);
