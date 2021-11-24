<!-- USAGE EXAMPLES -->
## Usage
Start server on port 8080

###Set game:
curl --location --request POST 'http://localhost:8080/api-sportradar/api/v1/setGame' \
--header 'accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"homeTeamName": "Argentina",
"homeTeamScore": 3,
"awayTeamName": "Australia",
"awayTeamScore": 1
}'

###Get score board:
curl --location --request GET 'http://localhost:8080/api-sportradar/api/v1/getScoreBoard' \
--header 'accept: application/json'

###Delete score board:
curl --location --request DELETE 'http://localhost:8080/api-sportradar/api/v1/deleteScoreBoard' \
--header 'accept: application/json'
