create table portfolio (id bigint not null auto_increment, owner_id bigint not null, primary key (id)) engine=MyISAM;
create table portfolio_product (id bigint not null auto_increment, amount double precision not null, product_id varchar(255), portfolio_id bigint, primary key (id)) engine=MyISAM;
alter table portfolio_product add constraint FKbe0x90e4jbaalu8nxt4md1qxf foreign key (portfolio_id) references portfolio (id);

INSERT into portfolio (id, owner_id) VALUES (1, 1);
INSERT into portfolio_product (id, amount, product_id, portfolio_id) VALUES (1, 100, 'BTC-USD', 1);
INSERT into portfolio_product (id, amount, product_id, portfolio_id) VALUES (2, 200, 'BTC-EUR', 1);

INSERT into portfolio (id, owner_id) VALUES (2, 2);
INSERT into portfolio_product (id, amount, product_id, portfolio_id) VALUES (3, 300, 'BTC-USD', 2);
INSERT into portfolio_product (id, amount, product_id, portfolio_id) VALUES (4, 10, 'BTC-EUR', 2);

INSERT into portfolio (id, owner_id) VALUES (3, 3);
INSERT into portfolio_product (id, amount, product_id, portfolio_id) VALUES (5, 50, 'BTC-USD', 3);
INSERT into portfolio_product (id, amount, product_id, portfolio_id) VALUES (6, 500, 'BTC-EUR', 3);

INSERT into portfolio (id, owner_id) VALUES (4, 4);
INSERT into portfolio_product (id, amount, product_id, portfolio_id) VALUES (7, 3, 'BTC-USD', 4);
INSERT into portfolio_product (id, amount, product_id, portfolio_id) VALUES (8, 1000, 'BTC-EUR', 4);