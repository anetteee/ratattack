# RATATTACK - Project in TDT4240 Software Architecture

![Logo](assets/readme/readmelogo.png){:height="10px"}

## Installation 
To run the application you will need Android Studio (or a similar IDE). 

### Cloning the project
clone the project to a folder of your chosing:

<pre><code id="git-clone-command">git clone https://gitlab.stud.idi.ntnu.no/ingval/ratattack.git</code></pre>
<button class="btn" data-clipboard-target="#git-clone-command"></button>

<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.8/clipboard.min.js"></script>
<script>
    var clipboard = new ClipboardJS('.btn');
</script>

Open Android Studio and choose Open an existing Android Studio project. Navigate and choose the cloned project.

## Compiling and running

To run the application you can either connect an Android device or use an Android emulator. Make sure that the device or emulator has an Android version with API higher than or equal to 31.

### Running on Android device
#### Conneting trough USB-cable
-  Connect your Android device to your computer via USB cable. Make sure that your device has developer mode enabled and USB debugging enabled.

- Open the project in Android Studio. Sync the project with gradle, build and run the project.

- After clicking allowing USB debugging, your device will automaticallly pop up in "Running devices".

- Once the application has been installed, it will automatically launch on your device. You can now use your application on your Android device.

#### Connecting trough Wi-files
- Make sure that your computer and device are connected to the same Wi-Fi.
- Open the project in Android Studio. Sync the project with gradle, build and run the project. 


### Running with emulator

- Open the AVD manager in the top drop down menu.
- Choose +Create Virtual Device
- Select a hardware, for exampel Nexus 6 and press next.

![Logo](assets/readme/hardware.png){:height="10px"}

- Select a system image with API level 31 or higher and press next.

![Logo](assets/readme/systemimage.png){:height="10px"}

- Choose landscape and press finish.

![Logo](assets/readme/androidvirtualdevice.png){:height="10px"}


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
The Android folder contains the files *AndroidinterfaceClass* and *AndroidLauncher* wich contains logic for connecting to the realtime firebase database. The same folder also contains the *assets* folder, with images used in the design of the application. 

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
- Ingebjørg Semb Tørresen
- Ingvild Almåsbakk
- Eva Anette Johansen
- Tuva Langedal Djupvik
- Marthe Thorbjørnsen
- Rebecca Ljøen Strandkleiv
