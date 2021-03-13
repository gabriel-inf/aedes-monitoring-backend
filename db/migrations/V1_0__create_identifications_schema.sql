CREATE TABLE IF NOT EXISTS `identifications` (
    `id` varchar(100) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `location_id` varchar(100),
    `lat` float,
    `lng` float,
    `time` timestamp
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE IF NOT EXISTS `wather` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20),
    `email` varchar(50),
    `date_of_birth` timestamp

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;