# RATATTACK
krav:
Also, include a readme file that explains the structure and how to compile/run the project.

## Compiling and running the application
Check if the device has an Android version with API higher than or equal to 31.


## Structure
The project are structure as shown in the tree below.
<pre> 
.
├── android                 
├── core                                   
├── desktop                    
├── gradle Scripts
└── README.md
</pre>

### Android folder
In the android folder we find the files AndroidinterfaceClass and androidLauncher wich contains logic for connecting to the realtime firebase database. The androif folder also contains the assets folder, which contains images used in the design of the application. 

### Core folder
<pre> 
.
├── ...
├── core                    
│     └── ....
│           ├── backend
├── ...     ├── gamecontroller
│           ├── model            
            ├── view
            ├── GameSettings
            ├── Highscore
            └── RatAttack
</pre>
The core folder contains the folders backend, gamecontroller, model and view in addition to the files GameSettings and RatAttack. The backend folder contains logic for fetching and submitting scores to the database.

The files are structured according to the Model-View-Controller pattern. The model folder is structured as shown in the tree below. The logic for the buttons used to play the game is placed in the buttons folder. The components is also placed in a seperate folder. The strategy pattern logic is placed in the which is used to control the different types of bullets is placed in the ShootingStrategy folder. The different systems such as the collision- and the rendersystem is placed in the system folder.
<pre> 
.      ...
└── ... ├──model 
       ...  ├── buttons                 
            ├── components     
            ├── shootingStrategy            
            ├── system
            └── ...
</pre>
