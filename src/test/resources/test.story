Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: scenario description
Meta:
@test
Given User open '<page>' page
When User search '<searchedText>' to '<elementName>' on '<page>' page
Then User see '<elementName>' contains '<searchedText>' on '<page>' page
Examples:
|page       |searchedText|elementName     |
|Google     |Ukraine     |input           |
|Bing       |EPAM        |input           |
|Google     |            |input           |
