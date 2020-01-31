# Sample Program to test Multipart Form Posting with the mpRestClient.  

This is basically a copy of the guide-microprofile-rest-client  Project located here: https://github.com/OpenLiberty/guide-microprofile-rest-client
It's been stripped down to a simple formdata resource

## Run the server
`mvn package liberty:run`
* Requires maven 3.6.2 or higher (3.5 which comes on fedora 31 and doesn't work, get the new stuff).

## Test the end points

This Request will fail:
`curl -vvv http://localhost:9080/formdata/test/request -F "name=foo" -F "value=bar" -F "file=@README.md" `
(You need a test file to send along send the readme or something)


The FormdataResource will accept the request, print out the contents of the values sent in and call the mpRest interface
to call   http://localhost:9080/formdata/test/respond  with the same form post.

This fails because mpRestClient doesn't seem to want to honor or correctly handle the `@Consumes(MediaType.MULTIPART_FORM_DATA)`
definition.  (Content-Type: multipart/form-data;)

Instead, it executs the request as a     `MediaType.APPLICATION_FORM_URLENCODED` (Content-Type: application/x-www-form-urlencoded) request.  

You can see this behavior by making the following request:

`curl -vvv http://localhost:9080/formdata/test/requestwrong -F "name=foo" -F "value=bar" -F "file=@README.md" `

This request is identically configured to the original request, except the end point that rest client calls is configured to consume `MediaType.APPLICATION_FORM_URLENCODED`
Which will succeed.




