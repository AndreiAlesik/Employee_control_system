create table public.userreg1
(
    id       bigint generated always as identity
        primary key,
    login    varchar(255) not null,
    password varchar(255) not null,
    role     varchar(255) not null
);