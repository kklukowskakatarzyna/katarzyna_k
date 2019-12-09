# Stibo cloud test automation interview task

This is template for coding task to be done prior interview

## TASK
- write automated test (using Selenium) to cover the following scenario
- use page object pattern
- use preferred language (we are using Java)
- deliver code as pull request to this repository (please rembemer that on github you need to fork repo first)

### SCENARIO
- go to https://www.sherwin-williams.com/ (it is using Angular on front end, not our product)
- click "Shop Our Products"
- click "For Homeowners" on overlaying popup
- search for "tuberose paint"
- click on searched product "SW 6578 | tuberose | interior/exterior"
- verify you are on the right page by checking url part: "https://www.sherwin-williams.com/homeowners/color/find-and-explore-colors/paint-colors-by-family/SW6578-tuberose"
- verify product name is displayed "SW 6578 Tuberose"
- click "DETAILS" tab
- verify "Hex Value: #d47c8c"
- click "View All Red Paint Colors" link
- search for "tuberose" in find a color field
- get HREF from tuberose rectangle (property)
- open new tab
- navigate to the link from HREF (step above) in the newly opened tab
- verify product name is displayed "SW 6578 Tuberose" (same step as before)
- close browser

GOOD LUCK!
