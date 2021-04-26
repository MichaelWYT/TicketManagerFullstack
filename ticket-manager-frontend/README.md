# Michael Tang

JIRA board: https://id.atlassian.com/invite/p/jira-software?id=0XWZrphLRQex4GkIRJ4ROw

Google drive folder with video: https://drive.google.com/drive/folders/1dvSyrG38l4L-f-sEdzKXECNkuFCGGu4c?usp=sharing

Please let me know ASAP if you cannot access the google drive: email to michaeltangwy@gmail.com and I'll send the link again... Though the link above should work.

# Ticket-Manager

A simple full stack ticket managing system to track technical issues for IT Technicians in their queue.

> To navigate around you can click on each heading to go to the respective sections. Likewise, you can also click on those headings as well, to return to the contents page.

# [Contents](#contents)

* [Planning](#planning)
* [Risk Assessment](#risk-assessemnt)
* [Development](#development)
* [Technologies](#technologies)
* [Appendix](#appendix)
    - [Risk Matrix](#risk-matrix)

# [Planning](#contents)
Each sprint I plan to be 1 week each for the next 4 weeks of development. As I can then review each tickets time estimations, subtasks for stories,  and generate a estimated velocity for future sprints. 

This means I can gauge on what features can be worked on while, getting a better accuracy for estimated times to be worked on each ticket in the backlog.

At the start of the project (15 -19)th March 2021 designated as week 1, I created stories to create features for the application to reach an MVP status. Additional stories were added as well to expand on the application once MVP has been reached.

Initial:

![intial planning of JIRA Board](docs/planning-init.png)

# [Risk Assessemnt](#contents)

When planning I have made an inital risk assessment for the duration of the project. Where risks can change, the document will also change to showcase the change in nature of risks on the project.

Initial:

![intial risk assessment for the project](docs/risk-assessment-init.png)

# [Development](#contents)

This section will showcase developement of the application over time:

Intial development was made to try and create basic functionality to get tickets from the database from the backend. However, due my frontend set up knowledge being rusty with react, tickets from sprint 1 were not completed. 

However, backend development was done quickly minus the test cases in sprint 1. In sprint 2 I am targeting more development on the backend funtionality. I plan to complete MVP functionality for the backend, using postman during development to make sure requests are made correctly.

I was able to utilise react libraries to produce a simple layout but more learning had to be done. Find in appendix more front end landing development...

![Phase 1 Landing page](docs/initial-view.png)

Throughout the weeks I was working on front end development while refreshing my knowledge of both backend and frontend. Frontend more especailly. It took longer to get some MVP functinality on the front end and as such I was not able to comeplete any testing on it. Back end testing was very rusty as well, but barely got 80% coverage.

Moving onto the final week to deploy my application, at first learning how to do it was fine. I was able to produce a simple terraform script to build the base infrastructure. However, diving into the other components proved to be a bit challenging and I was not able to have them work all together. With the time left I had to cut my losses and pull my source code manually using git and manually run it in dev mode.

## Things I learned

Although I had a strong start with planning and knowing what I needed to do, I overestimated my ability to learn react to a good degree. As such I was able to make a decent website, but with not frontend testing. Backend testing was done by it can be improved significantly. Though I did learn how to effectivly utilise spring to than I did before.

Additionally, learning to deploy to cloud in a week was a challenge and yet again overestimated my own ability to apply what I have learned to my specific use case.
As you would see in the video I have only produced half of what was required or less. But with more time and learning I should be able to do it.

# [Technologies](#contents)

Below are the technologies used during the project for development and testing:
- Springboot
- Spring framework
- MySQL (Dev Database)
- H2 (Testing Database)
- jacoco (Code coverage) (planned to be used)
- JUNIT (Unit Testing)
- Mockito (Testing)

- Postman (REST API Testing)

Frontend
- React (create-react-app starter) 
+ a lot of js libraries

Cloud Development
- Terraform

# [Appendix](#contents)

### Sprint 2 plan

![sprint 2](docs/sprint-2.png)

### Landing page v1

![landing page v1](docs/landing-page-v1.png)

### Final landing page

![Final landing page](docs/final-landing.png)

### Testing form

![TestingForm](docs/testing-form.png)

### Adding ticket form + modal

![Adding ticket](docs/adding-ticket-final.png)

### MVP view of tickets page

![MVP](docs/MVP.png)

## [Risk Matrix](#contents):

![Risk Matrix table as image](docs/risk-matrix.png)

# Author
Michael Tang
