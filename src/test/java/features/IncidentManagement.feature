Feature: Incident Management

Background:
Given Set the endpoint
And Set the Auth

Scenario: Get all Incidents
When get Incidents
Then validate response code as 200

Scenario: Create incident
When Create incident with String body '{"short_description": "Washing machine","description": "sevice my washing machine"}'
Then validate response code as 201

Scenario: Update Incident
When Update incident with String body '{"short_description": "Mobile","description": "sevice my Mobile"}'
Then validate response code as 200

Scenario: Delete Incident
When Delete Incident
Then validate response code as 204

Scenario Outline: Create multiple incident
When Create new multiple incident using file '<filename>'
Then validate response code as 201

Examples: 
|filename|
|Incident1.json|
|Incident2.json|

