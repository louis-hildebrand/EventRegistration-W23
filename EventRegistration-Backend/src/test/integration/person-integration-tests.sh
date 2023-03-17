# ------------------------------------------------------------------------------
# POST /person (valid)
# ------------------------------------------------------------------------------

echo "POST /person (valid):"
echo

response=$(curl http://localhost:8080/person \
	--request POST \
	--data '{"name": "Alice", "password": "password123"}' \
	--header 'Content-Type: application/json' \
	--include \
	--silent)

echo "$response"
echo
echo

echo "$response" | grep 'HTTP/1.1 201' > /dev/null
if [[ "$?" -ne 0 ]]
then
	echo "POST /person: Wrong HTTP status."
	exit 1
fi

echo "$response" | grep '\"name\":\"Alice\"' > /dev/null
if [[ "$?" -ne 0 ]]
then
	echo "POST /person: Missing or incorrect name in response."
	exit 1
fi

# Add more assertions as necessary...

# Extract the ID from the response (very hacky!)
#
# Breakdown of the sed command:
#   - s: substitute
#   - ^.*\"id\":([0-9]+).*$:
#       - ^:        start of line
#       - .*:       anything
#       - \"id\":   the string '"id":'
#       - ([0-9]+): one or more digits. The parentheses say that we want to capture those digits
#       - .*:       anything again
#       - $:        end of line
#   - \1: replace the entire line with the captured digits
#
# The result of the sed command is piped into tail -n 1 because the response has multiple lines
# but only the last one will have the ID.
#
person_id=$(echo "$response" | sed --regexp-extended 's/^.*\"id\":([0-9]+).*$/\1/' | tail -n 1)


# ------------------------------------------------------------------------------
# GET /person/{id} (valid)
# ------------------------------------------------------------------------------

echo "GET /person/$person_id (valid):"
echo

# GET is the default method
response=$(curl "http://localhost:8080/person/$person_id" --include --silent)

echo "$response"
echo
echo

echo "$response" | grep 'HTTP/1.1 200' > /dev/null
if [[ "$?" -ne 0 ]]
then
	echo "GET /person/$person_id: Wrong HTTP status."
	exit 1
fi

echo "$response" | grep '\"name\":\"Alice\"' > /dev/null
if [[ "$?" -ne 0 ]]
then
	echo "GET /person/$person_id: Missing or incorrect name in response."
	exit 1
fi

# Add more assertions as necessary...


# ------------------------------------------------------------------------------
# POST /person (invalid)
# ------------------------------------------------------------------------------

echo "POST /person (invalid):"
echo

response=$(curl http://localhost:8080/person \
	--request POST \
	--data '{"name": "C3PO", "password": "password123"}' \
	--header 'Content-Type: application/json' \
	--include \
	--silent)

echo "$response"
echo
echo

echo "$response" | grep 'HTTP/1.1 400' > /dev/null
if [[ "$?" -ne 0 ]]
then
	echo "POST /person: Wrong HTTP status."
	exit 1
fi

echo "$response" | grep 'Droids are not allowed. We don'\''t serve their kind.' > /dev/null
if [[ "$?" -ne 0 ]]
then
	echo "POST /person: Missing or incorrect error message."
	exit 1
fi

# Add more assertions as necessary...
