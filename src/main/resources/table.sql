create table category (
    id bigserial not null,
    name varchar(255),
    primary key (id));

  create table customer_order
  (total_price float(53) not null,
   id bigserial not null,
   contact_number varchar(255),
   customer_name varchar(255),
   delivery_address varchar(255),
   status varchar(255),
   primary key (id));

       create table delivery (
           id bigserial not null,
           order_id bigint unique,
           courier_name varchar(255),
           delivery_time varchar(255),
           status varchar(255),
           primary key (id));


 create table dish (price float(53) not null,
                    category_id bigint,
                    id bigserial not null,
                    description varchar(255),
                    name varchar(255),
                    primary key (id));


 create table order_dish (dish_id bigint not null,
                          order_id bigint not null);

create table report (average_delivery_time float(53) not null,
                     total_sales float(53) not null,
                     id bigserial not null,
                     date varchar(255),
                     most_popular_dish varchar(255),
                     primary key (id));

 alter table if exists delivery add constraint FKejhj32q2h5614nx7scc41chql foreign key (order_id) references customer_order ;
 alter table if exists dish add constraint FK3h7qfevodvyk24ss68mwu8ap6 foreign key (category_id) references category ;
 alter table if exists order_dish add constraint FKid9j9ujpwd84gwyoc7da4t8np foreign key (dish_id) references dish ;
 alter table if exists order_dish add constraint FKbgwk0gkfoorhtmhuxwc7rcg1r foreign key (order_id) references customer_order