CREATE TABLE IF NOT EXISTS `member` (
    `member_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `oauth_provider` varchar(10) NOT NULL,
    `oauth_id` varchar(100) NOT NULL,
    `nickname` varchar(15) NOT NULL,
    `email` varchar(30) NOT NULL,
    `profile_image_url` varchar(200) NOT NULL DEFAULT 'profile_image_url',
    `company` varchar(10) NULL,
    `created_at` datetime NOT NULL,
    `modified_at` datetime NOT NULL,
    `is_deleted` boolean NOT NULL,
    `rating` int NULL,
    `bio` varchar(200) NULL,
    `refresh_token` varchar(200) NULL
);

CREATE TABLE IF NOT EXISTS `feed` (
    `feed_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(20) NOT NULL,
    `content` text NOT NULL,
    `writer_id` bigint NOT NULL,
    `view_count` int NOT NULL,
    `adopted_comment_id` bigint NULL,
    `created_at` datetime NOT NULL,
    `modified_at` datetime NOT NULL,
    `is_deleted` boolean NOT NULL,
    FOREIGN KEY (`writer_id`) REFERENCES `member`(`member_id`)
);

CREATE TABLE IF NOT EXISTS `tech_stack` (
    `stack_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(20) NOT NULL,
    `tagged_count` int NOT NULL,
    `image_url` varchar(200) NOT NULL,
    `created_at` datetime NOT NULL,
    `modified_at` datetime NOT NULL
);

CREATE TABLE IF NOT EXISTS `message` (
    `message_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `from` bigint NOT NULL,
    `to` bigint NOT NULL,
    `created_at` datetime NOT NULL,
    `modified_at` datetime NOT NULL,
    `type` varchar(10) NOT NULL,
    FOREIGN KEY (`from`) REFERENCES `member`(`member_id`),
    FOREIGN KEY (`to`) REFERENCES `member`(`member_id`)
);

CREATE TABLE IF NOT EXISTS `follow` (
    `follow_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `follower_id` bigint NOT NULL,
    `following_id` bigint NOT NULL,
    `created_at` datetime NOT NULL,
    `modified_at` datetime NOT NULL,
    FOREIGN KEY (`follower_id`) REFERENCES `member`(`member_id`),
    FOREIGN KEY (`following_id`) REFERENCES `member`(`member_id`)
);

CREATE TABLE IF NOT EXISTS `comment` (
    `comment_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(20) NOT NULL,
    `content` text NOT NULL,
    `writer_id` bigint NOT NULL,
    `created_at` datetime NOT NULL,
    `modified_at` datetime NOT NULL,
    `feed_id` bigint NOT NULL,
    FOREIGN KEY (`writer_id`) REFERENCES `member`(`member_id`),
    FOREIGN KEY (`feed_id`) REFERENCES `feed`(`feed_id`)
);

CREATE TABLE IF NOT EXISTS `notification` (
    `notification_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `created_at` datetime NOT NULL,
    `modified_at` datetime NOT NULL,
    `sender_id` bigint NOT NULL,
    `type` varchar(10) NOT NULL,
    `content` varchar(100) NOT NULL,
    `receiver_id` bigint NOT NULL,
    FOREIGN KEY (`sender_id`) REFERENCES `member`(`member_id`),
    FOREIGN KEY (`receiver_id`) REFERENCES `member`(`member_id`)
);

CREATE TABLE IF NOT EXISTS `scrap` (
    `scrap_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `member_id` bigint NOT NULL,
    `feed_id` bigint NOT NULL,
    `created_at` datetime NOT NULL,
    `modified_at` datetime NOT NULL,
    FOREIGN KEY (`member_id`) REFERENCES `member`(`member_id`),
    FOREIGN KEY (`feed_id`) REFERENCES `feed`(`feed_id`)
);

CREATE TABLE IF NOT EXISTS `comment_like` (
    `comment_like_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `comment_id` bigint NOT NULL,
    `member_id` bigint NOT NULL,
    FOREIGN KEY (`comment_id`) REFERENCES `comment`(`comment_id`),
    FOREIGN KEY (`member_id`) REFERENCES `member`(`member_id`)
);

CREATE TABLE IF NOT EXISTS `feed_like` (
    `feed_like_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `member_id` bigint NOT NULL,
    `feed_id` bigint NOT NULL,
    FOREIGN KEY (`member_id`) REFERENCES `member`(`member_id`),
    FOREIGN KEY (`feed_id`) REFERENCES `feed`(`feed_id`)
);

CREATE TABLE IF NOT EXISTS `member_stack` (
    `member_stack_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `member_id` bigint NOT NULL,
    `stack_id` bigint NOT NULL,
    FOREIGN KEY (`member_id`) REFERENCES `member`(`member_id`),
    FOREIGN KEY (`stack_id`) REFERENCES `tech_stack`(`stack_id`)
);

CREATE TABLE IF NOT EXISTS `feed_stack` (
    `feed_stack_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `stack_id2` bigint NOT NULL,
    `feed_id` bigint NOT NULL,
    FOREIGN KEY (`stack_id2`) REFERENCES `tech_stack`(`stack_id`),
    FOREIGN KEY (`feed_id`) REFERENCES `feed`(`feed_id`)
);

CREATE TABLE IF NOT EXISTS `tier` (
    `tier_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `min_rating` int NOT NULL,
    `max_rating` int NOT NULL,
    `name` varchar(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS `text_message` (
    `message_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `content` text NOT NULL,
    FOREIGN KEY (`message_id`) REFERENCES `message`(`message_id`)
);

CREATE TABLE IF NOT EXISTS `notification_box` (
    `notification_box_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `read_at` datetime NOT NULL,
    `modified_at` datetime NOT NULL,
    `member_id` bigint NOT NULL,
    FOREIGN KEY (`member_id`) REFERENCES `member`(`member_id`)
);

CREATE TABLE IF NOT EXISTS `follow_notification` (
    `notification_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FOREIGN KEY (`notification_id`) REFERENCES `notification`(`notification_id`)
);

CREATE TABLE IF NOT EXISTS `mention_notification` (
    `notification_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `feed_id` bigint NOT NULL,
    FOREIGN KEY (`notification_id`) REFERENCES `notification`(`notification_id`),
    FOREIGN KEY (`feed_id`) REFERENCES `feed`(`feed_id`)
);

CREATE TABLE IF NOT EXISTS `file_message` (
    `message_id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `file_url` varchar(200) NOT NULL,
    FOREIGN KEY (`message_id`) REFERENCES `message`(`message_id`)
);