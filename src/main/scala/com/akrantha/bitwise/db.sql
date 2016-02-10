//
select tenantname fro tenants
(select tenantid, aptid, deptid from AptTenants group by tenantid
   having count(*) >1 )c 
   on tenants.tenantid = c.tenentid
   
 
Write a SQL query to get a list of all buildings and the number of open requests 
(Requests in which status equals 'Open').

select building_name from buildings
   where building in (select blding, aptid from apartments, requests left inner join where 
   aptid = request.aptid
   ) and 
   
