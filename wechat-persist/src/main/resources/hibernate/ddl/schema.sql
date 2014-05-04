drop table if exists wechat_user_info;
create table wechat_user_info (id bigint not null auto_increment, subscribe integer, openId varchar(255), nickname varchar(255), sex integer, city varchar(255), country varchar(255), province varchar(255), language varchar(255), headImgUrl varchar(255), subscribeTime varchar(255), primary key (id));
