create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );

create table message (
    id integer not null,
    filename varchar(255),
    tag varchar(255),
    text varchar(4096),
    primary key (id))
    engine=InnoDB;

create table user_role (
    user_id bigint not null,
    roles varchar(255))
    engine=InnoDB;

create table usr (
    id bigint not null,
    active bit not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id))
    engine=InnoDB;

alter table user_role
    add constraint userRole
    foreign key (user_id) references usr (id);