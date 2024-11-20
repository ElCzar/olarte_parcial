# Docker database
For docker database deployment just run the following command:
<code>docker run --name mysql-contratos -e MYSQL_ROOT_PASSWORD=rootpassword -e MYSQL_DATABASE=contratos -p 3306:3306 -d mysql:8</code>
