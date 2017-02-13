INSERT INTO ROLE (ROLE,DESCRIPTION) values
('ADMIN', 'Admin access'),
('ROLE1', 'Page 1 access'),
('ROLE2', 'Page 2 access'),
('ROLE3', 'Page 3 access');

INSERT INTO RESOURCE (URL_ACCESS, METHOD_ACCESS) values
('/app/pages/page1.html', 'GET'),
('/app/pages/page2.html', 'GET'),
('/app/pages/page3.html', 'GET'),
('/app/api/users/{username}', 'GET'),
('/app/api/users/{username}', 'PUT'),
('/app/api/users/', 'POST'),
('/app/api/users/{username}', 'DELETE');

INSERT INTO RESOURCE_ROLE (URL_ACCESS_ROLE, METHOD_ACCESS_ROLE, ROLE) values
('/app/pages/page1.html', 'GET', 'ROLE_APP_PAGE_1'),
('/app/pages/page2.html', 'GET', 'ROLE_APP_PAGE_2'),
('/app/pages/page3.html', 'GET', 'ROLE_APP_PAGE_3'),
('/app/pages/page1.html', 'GET', 'ROLE_APP_ADMIN'),
('/app/pages/page2.html', 'GET', 'ROLE_APP_ADMIN'),
('/app/pages/page3.html', 'GET', 'ROLE_APP_ADMIN'),
('/app/api/users/{username}', 'GET', 'ROLE_APP_PAGE_1'),
('/app/api/users/{username}', 'GET', 'ROLE_APP_PAGE_2'),
('/app/api/users/{username}', 'GET', 'ROLE_APP_PAGE_3'),
('/app/api/users/{username}', 'GET', 'ROLE_APP_ADMIN'),
('/app/api/users/{username}', 'PUT', 'ROLE_APP_ADMIN'),
('/app/api/users/', 'POST', 'ROLE_APP_ADMIN'),
('/app/api/users/{username}', 'DELETE', 'ROLE_APP_ADMIN');

INSERT INTO ACCOUNT (CODE, NAME, SURNAME, PASSWORD, ROLE) values
('USER1', 'Fulano', 'Fulanito', '123111', 'ROLE1'),
('USER2', 'Mangano', 'Manganito', '123222', 'ROLE2'),
('USER3', 'Pepino', 'Pepinito', '123333', 'ROLE3'),
('admin','Josep Maria', 'Carne', '123456', 'ADMIN');