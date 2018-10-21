DELETE FROM contact;
DELETE FROM section;
DELETE FROM resume;

insert into resume (uuid, full_name) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'Сергеева Мария');
insert into resume (uuid, full_name) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'Петрова Ольга');
insert into resume (uuid, full_name) VALUES ('f323c1b6-8fcf-4639-a0b5-433b0cd44a99', 'Иванов Сергей');
insert into resume (uuid, full_name) VALUES ('s323c1b6-8fcf-4639-a0b5-433b0cd55b00', 'Сидоров Иван');

insert into contact (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'PHONE', '+7(911) 123-4567');
insert into contact (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'SKYPE', 'sergeeva');
insert into contact (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'MAIL', 'sergeeva@gmail.com');

insert into contact (resume_uuid, type, value) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'PHONE', '+7(912) 456-234');
insert into contact (resume_uuid, type, value) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'SKYPE', 'petrova');

insert into contact (resume_uuid, type, value) VALUES ('f323c1b6-8fcf-4639-a0b5-433b0cd44a99', 'PHONE', '+7(912) 234-458');
insert into contact (resume_uuid, type, value) VALUES ('f323c1b6-8fcf-4639-a0b5-433b0cd44a99', 'MAIL', 'ivanov@gmail.com');
insert into contact (resume_uuid, type, value) VALUES ('f323c1b6-8fcf-4639-a0b5-433b0cd44a99', 'GITHUB', 'http://github.com/ivanov');

insert into contact (resume_uuid, type, value) VALUES ('s323c1b6-8fcf-4639-a0b5-433b0cd55b00', 'PHONE', '+7(926) 990-308');
insert into contact (resume_uuid, type, value) VALUES ('s323c1b6-8fcf-4639-a0b5-433b0cd55b00', 'MAIL', 'sidorov@gmail.com');
insert into contact (resume_uuid, type, value) VALUES ('s323c1b6-8fcf-4639-a0b5-433b0cd55b00', 'LINKEDIN', 'sidorov');

insert into section (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'PERSONAL', 'Сергеева Мария Personal');
insert into section (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'OBJECTIVE', 'Junior Java Developer');
insert into section (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'ACHIEVEMENT', 'Сергеева Мария Achievement1\nСергеева Мария Achievement2');
insert into section (resume_uuid, type, value) VALUES ('58f157fd-6864-42cc-a015-8a8bde5a577c', 'QUALIFICATIONS', 'Сергеева Мария Qualification1\nСергеева Мария Qualification12');

insert into section (resume_uuid, type, value) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'PERSONAL', 'Петрова Ольга Personal');
insert into section (resume_uuid, type, value) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'OBJECTIVE', 'Java Developer');
insert into section (resume_uuid, type, value) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'ACHIEVEMENT', 'Петрова Ольга Achievement1\nПетрова Ольга Achievement2');
insert into section (resume_uuid, type, value) VALUES ('d90d97ce-6410-4f6f-8700-5e728f74376a', 'QUALIFICATIONS', 'Петрова Ольга Qualification1\nПетрова Ольга Qualification2');

