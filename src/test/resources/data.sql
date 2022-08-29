
delete from task;

insert into task
    (uid, execution_time, creation_date, modification_date)
values
    ('UID_01', 1000, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
    ('UID_02', 2000, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
    ('UID_03', 3000, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
    ('UID_04', 4000, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
