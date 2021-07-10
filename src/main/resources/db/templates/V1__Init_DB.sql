create table cart (
    id bigint not null,
    count integer not null,
    non_user varchar(255),
    id_product bigint not null,
    id_user bigint,
    primary key (id)
);

create table categories (
    id bigint not null,
    description varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
);

create sequence hibernate_sequence start 1 increment 1;

create table producers (
    id bigint not null,
    country varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
);

create table product (
    id bigint not null,
    description varchar(255) not null,
    filename varchar(255),
    model varchar(255) not null,
    price integer not null,
    id_category bigint not null,
    id_producer bigint not null,
    primary key (id)
);

create table user_roles (
    user_id bigint not null,
    roles varchar(255) not null
);

create table users (
    id bigint not null,
    active bit not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

alter table if exists cart add constraint cart_product_fk foreign key (id_product) references product (id);

alter table if exists cart add constraint cart_user_fk foreign key (id_user) references users (id);

alter table if exists product add constraint product_category_fk foreign key (id_category) references categories (id);

alter table if exists product add constraint product_producer_fk foreign key (id_producer) references producers (id);

alter table if exists user_roles add constraint roles_user_fk foreign key (user_id) references users (id);


