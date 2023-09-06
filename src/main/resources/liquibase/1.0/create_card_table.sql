create table cards(
    id serial primary key,
    pan varchar(16) not null,
    pin varchar(4) not null,
    expired_at date,
    created_at date default now()
)