create sequence seq_role start with 1000;
create table roles
(
    id   bigint       not null primary key,
    name varchar(255) not null
);

grant select, insert, update, delete on roles to meamau;
grant select, usage on seq_role to meamau;

create sequence seq_privilege start with 1000;
create table privileges
(
    id   bigint       not null primary key,
    name varchar(255) not null
);

grant select, insert, update, delete on privileges to meamau;
grant select, usage on seq_privilege to meamau;

insert into privileges
values (1, 'TASK_CREATE');
insert into privileges
values (2, 'TASK_DELETE');
insert into privileges
values (3, 'TASK_UPDATE');
insert into privileges
values (4, 'TASK_INSPECTION');

create sequence seq_user start with 1000;
create table users
(
    id         bigint       not null primary key,
    username   varchar(255) not null,
    password   varchar      not null,
    first_name varchar(255),
    last_name  varchar(255),
    role_id    bigint
);

alter table users
    add constraint uk_user unique (username);
alter table users
    add constraint fk_users foreign key (role_id) references roles (id);

grant select, insert, update, delete on users to meamau;
grant select, usage on seq_user to meamau;

create sequence seq_task start with 1000;
create table tasks
(
    id                bigint       not null primary key,
    name              varchar(255) not null,
    short_description varchar(255),
    long_description  varchar(512),
    user_id           int
);

grant select, insert, update, delete on tasks to meamau;
grant select, usage on seq_task to meamau;

create table user_role_privilege
(
    role_id      bigint not null references roles (id),
    privilege_id bigint not null references privileges (id),
    primary key (role_id, privilege_id)
);

grant select, insert, update, delete on user_role_privilege to meamau;

insert into roles
values (1, 'ADMIN');

create sequence seq_file_attachment start with 1000;
create table file_attachment
(
    id          bigint        not null primary key,
    name        varchar(1000) not null,
    folder_path varchar(500)  not null,
    task_id     bigint        not null
);

alter table file_attachment
    add constraint fk_file_attachment_task_id foreign key (task_id) references tasks (id);
grant select, insert, update, delete on file_attachment to meamau;
grant update on seq_file_attachment to meamau;