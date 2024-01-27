SELECT TOP (1000) [role_id]
      ,[authority]
  FROM [angularspringbootecommerce].[dbo].[user_roles]
select p1_0.id,p1_0.description,p1_0.img_url,p1_0.name,p1_0.price from products p1_0
  update [angularspringbootecommerce].[dbo].[user_roles]  set authority = 'USER' where role_id =1
  insert into products (p1_0.id,p1_0.description,p1_0.img_url,p1_0.name,p1_0.price) values (1,'Sam Product', 'http://localhost:9001', 'Sam display Product', 100)


  select u1_0.role_id,u1_0.authority from user_roles u1_0 where u1_0.authority=?