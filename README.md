<p>A voting system for deciding where to have lunch.</p>
<p>REST API using Hibernate/Spring/SpringMVC <strong>without frontend</strong>.</p>
<ul>
<li>2 types of users: admin and regular users</li>
<li>Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)</li>
<li>Menu changes each day (admins do the updates)</li>
<li>Users can vote on which restaurant they want to have lunch at</li>
<li>Only one vote counted per user</li>
<li>If user votes again the same day:
<ul>
<li>If it is before 11:00 we assume that he changed his mind.</li>
<li>If it is after 11:00 then it is too late, vote can't be changed</li>
</ul>
</li>
</ul>
<p>Each restaurant provides new menu each day.</p>

#### get All Users
`curl -s http://localhost:8080/restaurantVoting/rest/admin/users --user admin@gmail.com:admin`

#### get Users 100001
`curl -s http://localhost:8080/restaurantVoting/rest/admin/users/100001 --user admin@gmail.com:admin`

#### Dishes :(
#### get All Dishes for 100002 
##`curl -s http://localhost:8080/restaurantVoting/rest/dishes/100002 --user user@yandex.ru:password`

#### get Dish 100006
##`curl -s http://localhost:8080/restaurantVoting/rest/dishes/100002/100006  --user user@yandex.ru:password`

#### add Dish
##`curl -s http://localhost:8080/restaurantVoting/rest/dishes/100002/100006  --user user@yandex.ru:password`

#### Restaurants
#### get All restaurants
`curl -s http://localhost:8080/restaurantVoting/rest/restaurants --user admin@gmail.com:admin`

#### get Restaurant 100002
`curl -s http://localhost:8080/restaurantVoting/rest/restaurants/100002 --user admin@gmail.com:admin`

#### add Restaurant
`curl -s -X POST -d '{"name":"testName","address":"testAddress,"date":"2019-11-18"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurantVoting/rest/restaurants --user admin@gmail.com:admin`

#### Votes
##  add Vote
`curl -s -X POST -d '{"id":100002}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080//restaurantVoting/rest/voting/vote/100002 --user admin@gmail.com:admin`

## get current vote
`curl -s http://localhost:8080/restaurantVoting/rest/voting/vote --user admin@gmail.com:admin`

## get all voted restaurants
`curl -s http://localhost:8080/restaurantVoting/rest/voting/ --user admin@gmail.com:admin`

## get votes restaurant 100002
`curl -s http://localhost:8080/restaurantVoting/rest/voting/100014/100002 --user admin@gmail.com:admin`
