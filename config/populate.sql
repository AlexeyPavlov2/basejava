insert into resume (uuid, full_name) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'Сергеева Мария');
insert into resume (uuid, full_name) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'Петрова Ольга');
insert into resume (uuid, full_name) VALUES ('f323c1b6-8fcf-4639-a0b5-433b0cd44a99', 'Иванов Сергей');

insert into contact (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'PHONE', '+7(911) 123-4567');
insert into contact (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'SKYPE', 'sergeeva');
insert into contact (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'MAIL', 'sergeeva@gmail.com');

insert into contact (resume_uuid, type, value) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'PHONE', '+7(912) 456-234');
insert into contact (resume_uuid, type, value) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'MAIL', 'petrova@gmail.com');
insert into contact (resume_uuid, type, value) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'SKYPE', 'petrova');

insert into contact (resume_uuid, type, value) VALUES ('f323c1b6-8fcf-4639-a0b5-433b0cd44a99', 'PHONE', '+7(912) 456-234');
insert into contact (resume_uuid, type, value) VALUES ('f323c1b6-8fcf-4639-a0b5-433b0cd44a99', 'MAIL', 'ivanov@gmail.com');
insert into contact (resume_uuid, type, value) VALUES ('f323c1b6-8fcf-4639-a0b5-433b0cd44a99', 'LINKEDIN', 'ivanov');



