<!-- USAGE EXAMPLES -->
## Usage
Start server on port 8080

### Set game:
curl --location --request POST 'http://localhost:8080/api-sportradar/api/v1/games' \
--header 'accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"homeTeam": {
"name": "Brazil",
"score": 1
},
"awayTeam": {
"name": "Argentina",
"score": 2
}
}'

### Get score board:
curl --location --request GET 'http://localhost:8080/api-sportradar/api/v1/games' \
--header 'accept: application/json'

### Delete score board:
curl --location --request DELETE 'http://localhost:8080/api-sportradar/api/v1/games' \
--header 'accept: application/json'
