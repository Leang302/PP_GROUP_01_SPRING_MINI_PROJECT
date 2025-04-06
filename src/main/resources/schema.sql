CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists achievements
(
    achievement_id uuid primary key DEFAULT uuid_generate_v4(),
    title          varchar(50) not null,
    description    varchar(255),
    badge          varchar(50),
    xp_required    integer     not null
);
create table if not exists app_users
(
    app_user_id   uuid primary key DEFAULT uuid_generate_v4(),
    username      varchar(255) not null unique ,
    email         varchar(255) not null unique ,
    password      varchar(255) not null,
    level         integer      default 1,
    xp            integer      default 0,
    profile_image varchar(255),
    is_verified   boolean      default false,
    created_at     timestamptz default now()
);
create table if not exists app_user_achievements
(
    app_user_id    uuid not null references app_users (app_user_id) on update cascade on delete cascade,
    achievement_id uuid not null references achievements (achievement_id) on update cascade on delete cascade,
    primary key (app_user_id,achievement_id)
);

create table if not exists habits
(
    habit_id    uuid primary key DEFAULT uuid_generate_v4(),
    title       varchar(255) not null,
    description varchar(255),
    frequency   varchar(50)  not null,
    is_active   boolean      not null,
    app_user_id uuid      not null references app_users (app_user_id) on update cascade on delete cascade ,
    created_at   timestamptz default now()
);
create table if not exists habit_logs
(
    habit_log_id    uuid primary key DEFAULT uuid_generate_v4(),
    log_date       date not null DEFAULT now(),
    status varchar(100) not null ,
    xp_earned   integer  not null,
    habit_id uuid not null references habits(habit_id)
);



