create table users
(
    id          bigint generated always as identity
        primary key,
    name        varchar,
    email       varchar,
    country     varchar,
    is_deleted  boolean,
    passport_id integer
        constraint fkddyjnd93b8x7gdng15k7g429p
            references passports
);
create table public.photos
(
    id           serial
        primary key,
    create_date  timestamp,
    is_visible   boolean,
    link_photo   varchar(255),
    x_high       integer,
    y_width      integer,
    employee_id  integer
        constraint fkbxq293jvxh5d4t6qrndovypyt
            references public.users,
    date_created timestamp,
    height       double precision,
    photo_link   varchar(255),
    width        double precision
);

alter table public.photos
    owner to postgres;

