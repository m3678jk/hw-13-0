# hw-13-0


Write a program that will interact with the https://jsonplaceholder.typicode.com API. 
You can use the standard Java features (HttpURLConnection), or get familiar with third-party libraries (Apache Fluent API, Apache HTTPClient).

Task 1
The program must contain methods for implementing the following functionality:
creating a new object at https://jsonplaceholder.typicode.com/users. 
You may not see updates on the site. 
The create method works correctly if the JSON with the object returned the same JSON, 
but with an id field with a value of 1 greater than the largest id on the site.
updating the object at https://jsonplaceholder.typicode.com/users. 
The updates may not be visible on the site directly. 
We will assume that the method works correctly if the updated JSON comes in response to the request 
(it must be exactly the same as you sent).
deleting an object from https://jsonplaceholder.typicode.com/users.
Here we will consider the correct result - the status from the 2xx group in response to the request.

getting information about all users https://jsonplaceholder.typicode.com/users

getting information about a user with a specific id https://jsonplaceholder.typicode.com/users/{id}

getting information about a user with a specific username - https://jsonplaceholder.typicode.com/users?username={username}

Task 2
Add a method to the program that will display all comments on the last 
post of a certain user and write them to a file. https://jsonplaceholder.typicode.com/users/1/posts 
The post with the highest id will be considered the last one. https://jsonplaceholder.typicode.com/posts/10/comments 
The file should be named "user-X-post-Y-comments.json" where X is the user number and Y is the post number.


Task 3
Add a method to the program that will display all open tasks for user X. 
https://jsonplaceholder.typicode.com/users/1/todos.

All tasks with completed = false are considered open.
