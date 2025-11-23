CREATE TABLE users (
    user_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50) DEFAULT '',
    comment TEXT DEFAULT '',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (user_id, password, nickname, comment)
VALUES ('TaroYamada', '$2a$08$aPYauvtbV5GPQTdWQpemXu19S76SQgE83v1dtL.bQwLnVXOaWcuO2', 'たろー', '僕は元気です');
INSERT INTO users (user_id, password, nickname, comment)
VALUES ('TaroYamada2', '$2a$08$aPYauvtbV5GPQTdWQpemXu19S76SQgE83v1dtL.bQwLnVXOaWcuO2', '', '');

