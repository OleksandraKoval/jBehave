Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal


Scenario: scenario description
Meta:
@test
Given User open '<pageToOpen>' page
When User search '<searchedText>' to '<elementName>' on '<pageToOpen>' page
And User click on '<searchType>' button on '<page>' page
Then User check required '<page>' windows has window '<expectedWindow>'
Examples:
|pageToOpen  |searchedText|elementName     |searchType |expectedWindow|page          |
|Google      |Ukraine     |input           |searchType |window        |GoogleStrategy|
|Bing        |EPAM        |input           |searchType |window        |BingStrategy  |



Scenario: find values and compare
Meta:
@test
Given User open 'Google' page
When User search 'Ukraine' to 'input' on 'Google' page
And User remember value for 'result' on 'Google'

Given User open 'Bing' page
When User search 'Ukraine' to 'input' on 'Bing' page
And User remember value for 'result' on 'Bing'

Then User compare remembered values

Scenario: scenario description
Meta:
@test
Given User open '<page>' page
When User search '<searchedText>' to '<elementName>' on '<page>' page
Then User see '<elementName>' contains '<searchedText>' on '<page>' page
Examples:
|page       |searchedText|elementName     |
|Bing       |EPAM        |input           |
|Google     |            |input           |
|Google     |Ukraine     |input           |