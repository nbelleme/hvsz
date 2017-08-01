
# Real-Life-Game : Humans vs Zombies

## Set up

Create Game :
Post on :
http://localhost:8080/api/gameDTO </br>
Set up config : 
Post on (create default, can set up custom config with params) :
http://localhost:8080/api/gameDTO/1/config </br>
Start gameDTO :
Get on: http://localhost:8080/gameDTO/start



DB: use mapDb, in-memory DB backed up by a file
You have to reset the db (delete the zombie-gameDTO.db file) every time one of the model class changes
Careful with the usage: you get the gameDTO object, modify it, and set it again to save
