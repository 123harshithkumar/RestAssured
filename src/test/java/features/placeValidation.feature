#To resuse the scenario, we can go for "" for the words/parts which change so below
#scenario can be used for deletePlace and other apis aswell
#multiple data set if we want to add multiple places
#To handle different resources[in when block],enum class is used use same Resource name as ENUM Class[resource>APIResources.java]
#handle different http methods aswell by parameterizing that in when line

Feature: Validating place APIs
@AddPlace
Scenario: Verify place being added successfully
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" API with http "Post" method
    Then Api call is successull with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id create maps to "<name>" using "GetPlaceAPI"
    
Examples:
|name       |language         |address            |
|Centre     |English          |third cross        |
#|Poovala    |Tulu             |Near Kondala Bridge|


#in below scenario when then and code are reusable from above
#if we run deleteplace alone, need to have extra method stepdefinitions>Hooks, bcz when all are run together,place_id will be received from above api,
#to make delete api independent add extra method by using existing methods-->addplace--retrieve place id
@DeletePlace
Scenario: Verify delete place api functionality
   Given delete place api payload
   When user calls "DeletePlaceAPI" API with http "Post" method
    Then Api call is successull with status code 200
    And "status" in response body is "OK"
   
    
    