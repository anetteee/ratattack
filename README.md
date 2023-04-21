# RATATTACK

Project in TDT4240 Software Architecture

![Logo](assets/ratattackbestlogo.png)

## Installation 
To run the application you will need Android Studio(or a similar IDE). 

### Cloning the project
clone the project to a folder of your chosing:

<pre><code id="git-clone-command">git clone https://gitlab.stud.idi.ntnu.no/ingval/ratattack.git</code></pre>
<button class="btn" data-clipboard-target="#git-clone-command"></button>

<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.8/clipboard.min.js"></script>
<script>
    var clipboard = new ClipboardJS('.btn');
</script>

Open Android studios and choose Open an existing Android Studio project. Navigate and choose the cloned projectc.

## Compiling and running

To run the application you can either connect an android device or use an android emulator. Make sure that the device or emulator has an Android version with API higher than or equal to 31.

### Running on android device

-  Connect your Android device to your computer via USB cable. Make sure that your device has developer mode enabled and USB debugging enabled.

- Open the project in Android Studio. Sync the project with gradle, build and run the project.

- Select your device in the "Select Deployment Target" dialog box and click "OK".

- Install the application and click "OK" to proceed.

- Once the application has been installed, it will automatically launch on your device. You can now use your application on your Android device.

### Running with emulator

- Open the AVD manager in the top drop down menu.
- Choose +Create Virtual Device


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
The android folder contains the files *AndroidinterfaceClass* and *androidLauncher* wich contains logic for connecting to the realtime firebase database. The same folder also contains the *assets* folder, with images used in the design of the application. 

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
            └── RatAttack
</pre>
The *core* folder contains the folders *backend*, *gamecontroller*, *model* and view in addition to the files *GameSettings* and *RatAttack*. The *backend* folder contains logic for fetching and submitting scores to the database.

The files are structured according to the Model-View-Controller pattern. The model folder is structured as shown in the tree below. The logic for the buttons used to play the game is placed in the buttons folder. The components are also placed in a seperate folder. The strategy pattern logic, which is used to control the different types of bullets, is placed in the *ShootingStrategy* folder. The different systems such as the collision- and the rendersystem is placed in the *system* folder.
<pre> 
.      ...
└── ... ├──model 
       ...  ├── buttons                 
            ├── components     
            ├── shootingStrategy            
            ├── system
            └── ...
</pre>

## Developed by:
- Ingebjørg Tørresen
- Ingvild Almåsbakk
- Eva Anette Johansen
- Tuva Djupvik
- Marthe Thorbjørnsen
- Rebecca Ljøen Strandkleiv
