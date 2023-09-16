-- testdb.post definition
DROP TABLE  if exists post;
CREATE TABLE `post` (
  `created_date_time` datetime(6) DEFAULT NULL,
  `post_id` binary(16) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO post (created_date_time, post_id, author, contents, title) VALUES('2023-06-24 11:37:41.676', 0x578E00259FB5432FBB2DCA5E98BFD5F2, '', 'contents1', 'title1');
INSERT INTO post (created_date_time, post_id, author, contents, title) VALUES('2023-06-24 11:37:41.676', 0x9FBA27989B024E12973AEB731F5A15DE, '', 'contents3', 'title3');
INSERT INTO post (created_date_time, post_id, author, contents, title) VALUES('2023-06-24 11:37:41.676', 0xD59900D3A80A4AB98DD54137FAAA874C, '', 'contents2', 'title2');

