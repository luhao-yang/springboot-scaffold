
DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  register_at DATETIME NOT NULL
);

INSERT INTO user (name, email, password, register_at) VALUES
  ('Amy', 'amy@example.com', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', PARSEDATETIME('2021-01-02 16:01:43', 'yyyy-MM-dd HH:mm:ss')),
  ('Bill', 'bill@dummy.com', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', PARSEDATETIME('2021-01-11 19:21:34', 'yyyy-MM-dd HH:mm:ss')),
  ('Catherine', 'cat@demo.com', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', PARSEDATETIME('2021-01-23 11:31:09', 'yyyy-MM-dd HH:mm:ss'));