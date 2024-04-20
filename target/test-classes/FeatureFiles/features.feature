
@tag
Feature: Rest Asssured Automation


@smoke @getallUser
Scenario Outline: Get all the list of user
Given I set all the paramter for the request
When  I send the request to get all the user of Page "<pageNo>"
Then   I validate response message "<responseMessage>"
     Examples:
                |  pageNo | responseMessage |
                | 2     | 1               | 
         
@getUser
Scenario Outline: Get one user 
Given I set all the paramter for the request
When  I send the request to get specific user "<userID>"
Then   I validate response message "<responseMessage>"
              Examples:
                |userID       | responseMessage |
                | 1           | George          | 
          
          
          
Scenario Outline: create one user 
Given I set all the paramter for the request
When  I send the request to post create a user
                |name     | job   |
                |rushikesh| tester|
Then   I validate response message "<responseMessage>"
             Examples:
               | responseMessage |
               |  rushikesh      | 
 
 
Scenario Outline: update one user 
Given I set all the paramter for the request
When  I send the request to put update a user
              |name     | job   | userID |
              |rushikesh| tester|  2     |
 Then   I validate response message "<responseMessage>"
         Examples:
            | responseMessage |
            |  rushikesh      | 
            
Scenario: Delete one user 
Given I set all the paramter for the request
When  I send the request to Delete a user
            | userID |
            | 3      |            
 

 