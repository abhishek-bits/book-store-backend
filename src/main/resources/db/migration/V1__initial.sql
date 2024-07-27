DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `dob` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(15) NOT NULL,
    `email` VARCHAR(50) NOT NULL
);

CREATE TABLE `book` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `isbn` VARCHAR(50) NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `pages` INTEGER NOT NULL,
    `units` INTEGER NOT NULL,
    `published_at` VARCHAR(255) NOT NULL,
    `author_id` BIGINT,
    CONSTRAINT book_author_fk FOREIGN KEY(`author_id`) REFERENCES `author`(`id`)
);
