CREATE SCHEMA kanji;

CREATE TABLE words (
  Id int NOT NULL AUTO_INCREMENT,
  Level int NOT NULL,
  Lesson int NOT NULL,
  HWordQuestion varchar(60) DEFAULT NULL,
  HWord1 varchar(60) DEFAULT NULL,
  HWord2 varchar(60) DEFAULT NULL,
  HWord3 varchar(60) DEFAULT NULL,
  HWord4 varchar(60) DEFAULT NULL,
  HWordAnswer int DEFAULT NULL,
  KWordQuestion varchar(60) DEFAULT NULL,
  KWord1 varchar(60) DEFAULT NULL,
  KWord2 varchar(60) DEFAULT NULL,
  KWord3 varchar(60) DEFAULT NULL,
  KWord4 varchar(60) DEFAULT NULL,
  KWordAnswer int DEFAULT NULL,
  EnglishTrad varchar(100) DEFAULT NULL,
  PortugueseTrad varchar(100) DEFAULT NULL,
  PRIMARY KEY (Id)
);