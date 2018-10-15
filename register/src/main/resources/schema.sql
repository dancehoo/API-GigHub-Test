create table mt_member_classified
(
   id integer not null,
   class_description varchar(255) not null,
   min_salary integer not null,
   is_active varchar(1) not null default 'Y',
   created_date TIMESTAMP not null default CURRENT_TIMESTAMP,
   created_by varchar(255) not null default 'ADMIN',
   updated_date TIMESTAMP not null default CURRENT_TIMESTAMP,
   updated_by varchar(255) not null  default 'ADMIN',
   primary key(id)
);

create table tran_member
(
   email varchar(255) not null,
   password_hash varchar(32) not null,
   salary integer not null,
   classified_id integer not null,
   is_active varchar(1) not null default 'Y',
   created_date TIMESTAMP not null default CURRENT_TIMESTAMP,
   created_by varchar(255) not null,
   updated_date TIMESTAMP not null default CURRENT_TIMESTAMP,
   updated_by varchar(255) not null,
   primary key(email),
   foreign key (classified_id) references mt_member_classified(id)
);

create table log_register 
(
  email varchar(255),
  salary varchar(255),
  message varchar(255),
  response_status integer,
  created_date TIMESTAMP default CURRENT_TIMESTAMP,
  created_by varchar(255)
);