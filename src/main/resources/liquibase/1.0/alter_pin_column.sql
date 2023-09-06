--liquibase formatted sql
--changeset Elnur Khalilov:Changed pin column size
alter table cards
    alter column pin type varchar(6) using pin::varchar(6);
--rollback drop table cards;
