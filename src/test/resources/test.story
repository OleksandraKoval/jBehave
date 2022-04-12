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
|pageToOpen  |searchedText|elementName     |searchType |expectedWindow|page           |
|GOOGLE      |Ukraine     |input           |searchType |window        |GOOGLE_STRATEGY|
|BING        |EPAM        |input           |searchType |window        |BING_STRATEGY  |


Scenario: find values and compare
Meta:
@test
Given User open 'GOOGLE' page
When User search 'Ukraine' to 'input' on 'Google' page
And User remember value for 'result' on 'Google'

And User open 'BING' page
When User search 'Ukraine' to 'input' on 'Bing' page
And User remember value for 'result' on 'Bing'

Then User compare remembered 'REMEMBERED_VALUE_FOR_GOOGLE' and 'REMEMBERED_VALUE_FOR_BING' values

Scenario: scenario description
Meta:
@test
Given User open '<page>' page
When User search '<searchedText>' to '<elementName>' on '<page>' page
Then User see '<elementName>' contains '<searchedText>' on '<page>' page
Examples:
|page       |searchedText|elementName     |
|BING       |EPAM        |input           |
|GOOGLE     |            |input           |
|GOOGLE     |Ukraine     |input           |