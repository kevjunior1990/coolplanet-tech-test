
delete from task;

insert into task
    (uid, execution_time, creation_date, modification_date)
values
    ('UID_01', 1, 1000, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
    ('UID_02', 1, 2000, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
    ('UID_03', 1, 3000, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
    ('UID_04', 1, 4000, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
