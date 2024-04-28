-- CREATE DATABASE tweet_app;
USE tweet_app;
SHOW tables;

CREATE TABLE users(
id INT NOT NULL AUTO_INCREMENT,
user_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20),
email VARCHAR(40) UNIQUE,
user_key VARCHAR(30),
PRIMARY KEY(id)
);

DESCRIBE users;
SHOW CREATE TABLE users;

ALTER TABLE users
MODIFY COLUMN email VARCHAR(40) NOT NULL UNIQUE;

ALTER TABLE users
MODIFY COLUMN user_key VARCHAR(30) NOT NULL;

CREATE TABLE tweets(
id INT AUTO_INCREMENT,
message TEXT NOT NULL,
date_tweet TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
user_id INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW CREATE TABLE tweets;
DESCRIBE tweets;

SELECT * FROM tweets;
SELECT * FROM users;

ALTER TABLE tweets
CHANGE COLUMN tweet_data tweet_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

SELECT t.message, t.tweet_date, u.user_name, u.last_name
FROM tweets t
INNER JOIN users u
ON t.user_id = u.id;

DELETE FROM tweets 
WHERE id = 16 AND user_id = 1;


