# spring-webflux

Pre-requisite
1. Install mongo-db at local
2. start using mongosh <code>"mongodb://localhost:27017"</code>
2. create a db <code>use events-db</code>
3. create an inventory 'events'
4. use inventory events to insert a sample data 
<code>db.inventory.insertOne( {id: 46, message:"this is test", status:"dfafsdfd"})</code>

<h2>Build and depoloy app</h2>
start the app using <code>./mvnw spring-boot:run</code>

<h2>test the app</h2>
Check the thread dumps using api in api editor or webclient  <code>GET http://localhost:8080/events/threads/mongodb</code>

other endpints:<br>
<code>POST http://localhost:8080/events/create</code><br>
<code>GET http://localhost:8080/events/{id}</code><br>
