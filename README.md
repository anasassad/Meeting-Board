# Meeting-Board
## Getting Started

   - **Base URL:** At present this app can only run locally without any restrictions *(It just need to fulfill the requirements below)* .The backend app is hosted 
      at default `http://localhost:8080/` .
   - **Authentication:** This version of the app need to be auhtenticated via the login form provided by Spring Security <sub>(Built-in functionality)</sub> . Take a        look at the source code to see and customize the credentials for your OWN .
 
## Requirements

   - To run this project on your machine you should need this following pre-sets :
        
    + Java 8
    ++ Maven 3.x
    +++ IDE (Spring Tools Suite 4)
    ++++ Windows or Mac Operation System
    
## Endpoints

### GET `/`
   - **General:**
      - Return a list of comments grouped by each type either *`PLUS - DELTA - STAR`* to be listed via the `Thymleaf Template`
      - Return a simple argument called `time` to show the actual time
   - **Sample:** `http://localhost:8080/` or `http://127.0.0.1:8080/`

### POST `/comment`
   - **General:**
      - Receive the submitted form with the three parameters for each comments at once .Returns by result a couple of comments created for the same day .
      - *Parameters:* 
      
          ```
         > deltaComment
         > starComment
         > plusComment
          ```
   - **Sample:** `http://localhost:8080/comment` or `http://127.0.0.1:8080/comment`
    
