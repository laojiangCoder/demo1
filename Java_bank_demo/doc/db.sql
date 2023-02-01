create table admin(
   adminId int primary key auto_increment,
   adminNo varchar(100),
   adminPass varchar(100),
   adminName varchar(100),
   adminPhone varchar(20),
   adminEmail varchar(500)
);

insert into admin(adminNo,adminPass,adminName,adminPhone,adminEmail) 
value('admin','pass','Administrator','13123456789','admin@qq.com')