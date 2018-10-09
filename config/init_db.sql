DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS resume;

CREATE TABLE resume (
  uuid      VARCHAR(36) PRIMARY KEY NOT NULL,
  full_name TEXT                 NOT NULL
);

CREATE TABLE contact (
  id          SERIAL,
  resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  value       TEXT     NOT NULL
);
CREATE UNIQUE INDEX contact_uuid_type_index
  ON contact (resume_uuid, type);

insert into resume (uuid, full_name) VALUES ('uuid1', 'Сергеева Мария');
insert into resume (uuid, full_name) VALUES ('uuid2', 'Петрова Ольга');
insert into resume (uuid, full_name) VALUES ('uuid3', 'Иванов Сергей');

insert into contact (resume_uuid, type, value) VALUES ('uuid1', 'PHONE', '+7(911) 123-4567');
insert into contact (resume_uuid, type, value) VALUES ('uuid1', 'SKYPE', 'sergeeva');
insert into contact (resume_uuid, type, value) VALUES ('uuid1', 'MAIL', 'sergeeva@gmail.com');

insert into contact (resume_uuid, type, value) VALUES ('uuid2', 'PHONE', '+7(912) 456-234');
insert into contact (resume_uuid, type, value) VALUES ('uuid2', 'MAIL', 'petrova@gmail.com');

