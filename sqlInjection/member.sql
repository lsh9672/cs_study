create database simpleLogin;

use simpleLogin;

create table members(
	id INT AUTO_INCREMENT,
    user_id VARCHAR(255),
    pass_word VARCHAR(255),
    
    primary key(id)
) ENGINE = InnoDB;

Insert into members(user_id,pass_word) values("ssafy", "1234");

select * from members;

SELECT * FROM members WHERE user_id = '' OR 1='1 --' AND pass_word='1234'