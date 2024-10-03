-- Member 데이터 삽입
INSERT INTO member (member_id, password, member_name)
VALUES ('user1', 'password123', 'Jeongjo Lee');
INSERT INTO member (member_id, password, member_name)
VALUES ('user2', 'password456', 'Jiwon Park');

-- Lecture 데이터 삽입
INSERT INTO lecture (name, gangsa)
VALUES ('Java Programming', 'Kim Hyunsoo');
INSERT INTO lecture (name, gangsa)
VALUES ('Spring Framework', 'Lee Jiwon');

-- LectureItem 데이터 삽입
INSERT INTO lecture_item (lecture_id, date, capacity)
VALUES (1, '2024-10-10', 30);
INSERT INTO lecture_item (lecture_id, date, capacity)
VALUES (1, '2024-10-17', 30);
INSERT INTO lecture_item (lecture_id, date, capacity)
VALUES (2, '2024-10-10', 30);

-- LectureInventory 데이터 삽입
INSERT INTO lecture_inventory (lecture_id, lecture_item_id, available_seats)
VALUES (1, 1, 30);
INSERT INTO lecture_inventory (lecture_id, lecture_item_id, available_seats)
VALUES (1, 2, 30);
INSERT INTO lecture_inventory (lecture_id, lecture_item_id, available_seats)
VALUES (2, 3, 30);

-- LectureRegistration 데이터 삽입
-- INSERT INTO lecture_registration (member_id, lecture_id, lecture_item_id, registration_date)
-- VALUES (1, 1, 1, '2024-10-01 10:00:00');
-- INSERT INTO lecture_registration (member_id, lecture_id, lecture_item_id, registration_date)
-- VALUES (2, 1, 2, '2024-10-02 10:00:00');
-- INSERT INTO lecture_registration (member_id, lecture_id, lecture_item_id, registration_date)
-- VALUES (1, 2, 3, '2024-10-03 10:00:00');

select *
from member;
select *
from lecture;
select *
from lecture_item;
select *
from lecture_inventory;
select *
from lecture_registration;

-- drop all objects;