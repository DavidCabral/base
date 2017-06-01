CREATE TABLE usuario (
  id                BIGINT       NOT NULL AUTO_INCREMENT,
  authorities       VARCHAR(50),
  email             VARCHAR(120),
  lastPasswordReset DATETIME,
  password          VARCHAR(120) NOT NULL,
  username          VARCHAR(12)  NOT NULL,
  PRIMARY KEY (id)
)
