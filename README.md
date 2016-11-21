#Overview

This is a simple example of rest api secured with OAuth2 provided by spring-security  

It uses Spring 4.3.2, Spring-Security 3.2.5 with Spring-Security-OAuth2 2.0.11. All the tokens, apps and user data is stored in DB.
It was designed to run on Tomcat(8) with SSL and use PostgreSQL(9.4) as DB.

#Deployment

###SSL:

First, you need to create server keys. Use keytool, Luke:
 keytool -genkey - alias YOUR_SERVER_CERT_ALIAS -keyalg RSA -keystore /Path/to/file/mykeystore.keystore

Remember passwords to keystore and certificate.
 
Second, you need to setup SSL on your Tomcat. Open ${CATALINA_HOME}/conf/server.xml in any text editor.
You have to do 2 things:
 - Comment the line with settings of AprLifecycleListener:
 ```<Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />```
 - Comment the default http connector. It look like:
 ```<Connector port="80" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />```
 - Add new connector like this:
 ```<Connector SSLEnabled="true" 
 			   keystoreFile="/Path/to/file/mykeystore.keystore"
 			   keystorePass="<YOUR_KEYSTORE_PASSWORD>"
 			   port="8443"
 			   scheme="https"
 			   secure="true"
 			   sslProtocol="TLS"/>
 ```
 			   
### Database:

You have to set connection to database by changing url, user and password in **springmvc.properties**.
By default project will try connect localhost at standart port 5432, database "postgres", username "postgres", password "postgres".
You can download postgreql server, install it and create that database, or you can set connection to any existing postgres database of your choice.

### EMails

This app uses emails to confirm user registration. You have to create mailbox on gmail.com, **open SMTP and POP3 protocols in settings** and set authorization data in **springmvc.properties**
You also have to manually set conformation url, by default it uses localhost and standart tomcat's SSL port: https://localhost:8443/springmvc/register/confirm/
 
 
#Usage

##Register user and get oauth access token

By default app contains one app **(client_id=LifeUniverseEtc, client_secret=42)** and one user **(root:toor)**.
All the communication runs in JSON, so you have to get a REST-Client. I use https://insomnia.rest/, you can pick whatever you want.

I will use my url (default), it may different for you.

First, you have to register new user. Send a POST request like this (insert your own data):

https://localhost:8443/springmvc/register/request/
```
{
	"login": "ivan_ivanov",
	"password": "ivan_ivanov",
	"email": "iivanov@mail.ru"
}
```
Check your email. You'll find there a letter that contains link that will confirm registration 
Something like this:
https://localhost:8443/springmvc/register/confirm/?login=ivan_ivanov&conformationCode=12345678-1234-1234-1234-123456789000

After this step your user is registered and confirmed. 

Lets get oauth access token by sending POST like this (JSON body is empty):
https://localhost:8443/springmvc/oauth/token?grant_type=password&client_id=LifeUniverseEtc&client_secret=42&username=ivan_ivanov&password=ivan_ivanov

You will get a json response. Field "access_token" is what you need.

##REST

Lets run through CRUD operations. We operate with user's notes. User can create, update, delete and view notes that belongs to him, and cannot access others'
You have to pass "access_token" you recieved above with every request. Without it you won't be able to access data.

- Create new note:
POST https://localhost:8443/springmvc/api/notes?access_token=98765432-9876-9876-9876-987654321012
```
{
	"text": "My first note",
	"important": true
}
```
- Update existing one:
PUT https://localhost:8443/springmvc/api/notes?access_token=98765432-9876-9876-9876-987654321012
```
{
	"id": 1,
	"comment": "I love oauth"
}
```
- Get all user's notes:
GET https://localhost:8443/springmvc/api/notes?access_token=98765432-9876-9876-9876-987654321012
(JSON body is empty)

- Delete note by id:
DELETE https://localhost:8443/springmvc/api/notes?access_token=98765432-9876-9876-9876-987654321012
```
{
	"id": 1
}
```