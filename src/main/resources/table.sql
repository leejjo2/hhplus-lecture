-- Member 테이블
CREATE TABLE member
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id   VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    member_name VARCHAR(255) NOT NULL
);


-- Lecture 테이블
CREATE TABLE lecture
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(255) NOT NULL,
    gangsa VARCHAR(255) NOT NULL
);

-- LectureItem 테이블
CREATE TABLE lecture_item
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    lecture_id BIGINT NOT NULL,
    date       DATE   NOT NULL,
    capacity   INT    NOT NULL,
    CONSTRAINT fk_lecture_for_item FOREIGN KEY (lecture_id) REFERENCES lecture (id)
);


-- LectureInventory 테이블
CREATE TABLE lecture_inventory
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    lecture_item_id BIGINT NOT NULL UNIQUE,
    available_seats INT    NOT NULL,
    CONSTRAINT fk_lecture_item FOREIGN KEY (lecture_item_id) REFERENCES lecture_item (id)
);

-- LectureRegistration 테이블
CREATE TABLE lecture_registration
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id         BIGINT    NOT NULL,
    lecture_item_id   BIGINT    NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES member (id),
    CONSTRAINT fk_lecture_item_for_registration FOREIGN KEY (lecture_item_id) REFERENCES lecture_item (id)
);
