select * from users where username = 'agodil';

select * from contact where userid = 1;


select * from users,contact where users.id = contact.userid and users.id = 2;
