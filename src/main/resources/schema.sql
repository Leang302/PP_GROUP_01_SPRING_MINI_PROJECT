create table if not exists achievements
(
    achievement_id uuid primary key,
    title          varchar(50) not null,
    description    varchar(255),
    badge          varchar(50),
    xp_required    integer     not null
);

create table if not exists app_user_achievements
(
    app_user_id    int not null references app_users (app_user_id) on update cascade on delete cascade,
    achievement_id int not null references achievements (achievement_id) on update cascade on delete cascade
);
create table if not exists app_users
(
    app_user_id   uuid primary key,
    username      varchar(255) not null,
    email         varchar(255) not null,
    password      varchar(255) not null,
    level         integer      not null,
    xp            integer      not null,
    profile_image varchar(255),
    is_verified   boolean      not null,
    create_at     timestamptz default now()
);
create table if not exists habits
(
    habit_id    uuid primary key,
    title       varchar(255) not null,
    description varchar(255),
    frequency   varchar(50)  not null,
    is_active   boolean      not null,
    app_user_id integer      not null references app_users (app_user_id) on update cascade on delete cascade ,
    create_at   timestamptz default now()
);
create table if not exists habit_logs
(
    habit_log_id    uuid primary key,
    log_date       date not null,
    status boolean not null ,
    xp_earned   integer  not null,
    habit_id integer not null references habits(habit_id)
);