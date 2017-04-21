# ZaphodHeads and Java font problem

This is a little demo application which shows a problem with fonts on a zaphodhead configuration.
Zaphodhead means a multi-monitor setup whith each monitor on a separate X id.
So you need a least two monitors to reproduce the problem.
 

## Background
We have a Java UI with two separate windows. From the main window a second one is opened on a different monitor.
X is configured to have a separate display id for each monitor (zaphodhead). This configuration worked quiet well for a couple
of years on SLES11 machines with different NVidia cards and Java versions.
With the same NVidia cards and the same Java version (but a newer X server version) it doesn't work on a CentOS or Fedora linux.
The fonts on the second window seem to be transparent.

Main Window is working
![main](https://raw.githubusercontent.com/f4n/ZaphodHeadsDemoUI/master/main.png)
Second Window on different Monitor is broken (button fonts)
![second](https://raw.githubusercontent.com/f4n/ZaphodHeadsDemoUI/master/secondScreen.png)


## What does the demo UI do
It first opens a window on the current screen. With a button "open" you can open a second window on a different screen (different than the current one)

## Reproducible with:
* Java: 1.8.0_74, 1.8.0_121, 1.8.0_131, 1.9ea
* GFX:
  * NVidia GTX 1050
  * NVidia Quadro M1000M, 375.39
  * Intel Iris Pro 6200, latest driver
  * NVidia FX580, 340.102
  * NVidia NVS420, 340.102
* X: 1.19.3, 1.17.2
* OS: 
  * Fedora 25
  * CentoOS 7
* WindowManager:
  * KDE
  * Gnome
  * OpenBox
  * MVM
  
## Working with:
* Java: 1.8.0_74, 1.8.0_121
* GFX:
  * NVidia FX580, 331.20
  * NVidia NVS420, 331.20
* X: 1.6.5
* OS: 
  * SLES11 sp2
* WindowManager:
  * KDE
  * MVM
  
  
## How to use


### Using the jar in this repository

```bash
java -cp DemoUI-0.0.1-SNAPSHOT.jar Main
```

### Or build & run

```bash
mvn package
java -cp target/DemoUI-0.0.1-SNAPSHOT.jar Main
```


## Configuration


### Configuration NVidia 

(generated with nvidia-settings, tested with both absolute FontPath and socket)

```bash
Section "ServerLayout"
    Identifier     "Layout0"
    Screen      0  "Screen0" 0 0
    Screen      1  "Screen1" LeftOf "Screen0"
    InputDevice    "Keyboard0" "CoreKeyboard"
    InputDevice    "Mouse0" "CorePointer"
    Option         "Xinerama" "0"
EndSection

Section "Files"
#    FontPath        "/usr/share/fonts/default/Type1"
     FontPath      "unix:7100"
EndSection

Section "InputDevice"

    # generated from default
    Identifier     "Mouse0"
    Driver         "mouse"
    Option         "Protocol" "auto"
    Option         "Device" "/dev/input/mice"
    Option         "Emulate3Buttons" "no"
    Option         "ZAxisMapping" "4 5"
EndSection

Section "InputDevice"

    # generated from default
    Identifier     "Keyboard0"
    Driver         "kbd"
EndSection

Section "Monitor"
    Identifier     "Monitor0"
    VendorName     "Unknown"
    ModelName      "Unknown"
EndSection

Section "Monitor"
    Identifier     "Monitor1"
    VendorName     "Unknown"
    ModelName      "Unknown"
EndSection

Section "Device"
    Identifier     "Device0"
    Driver         "nvidia"
    VendorName     "NVIDIA Corporation"
    BoardName      "Quadro M1000M"
    BusID          "PCI:1:0:0"
    Screen          0
EndSection

Section "Device"
    Identifier     "Device1"
    Driver         "nvidia"
    VendorName     "NVIDIA Corporation"
    BoardName      "Quadro M1000M"
    BusID          "PCI:1:0:0"
    Screen          1
EndSection

Section "Screen"
    Identifier     "Screen0"
    Device         "Device0"
    Monitor        "Monitor0"
    DefaultDepth    24
    Option         "Stereo" "0"
    Option         "nvidiaXineramaInfoOrder" "DFP-4"
    Option         "nvidiaXineramaInfo" "False"
    Option         "metamodes" "DP-4: nvidia-auto-select +0+0"
    Option         "SLI" "Off"
    Option         "MultiGPU" "Off"
    Option         "BaseMosaic" "off"
    SubSection     "Display"
        Depth       24
    EndSubSection
EndSection

Section "Screen"
    Identifier     "Screen1"
    Device         "Device1"
    Monitor        "Monitor1"
    DefaultDepth    24
    Option         "Stereo" "0"
    Option         "metamodes" "DP-3.1: nvidia-auto-select +0+0"
    Option         "SLI" "Off"
    Option         "MultiGPU" "Off"
    Option         "BaseMosaic" "off"
    SubSection     "Display"
        Depth       24
    EndSubSection
EndSection

```
### Configuration Intel 

(made by hand)

```bash
Section "ServerLayout"
    Identifier     "Main"
    Screen      0  "Screen0" 0 0
    Screen      1  "Screen1" RightOf "Screen0"
    Option         "Xinerama" "0"
EndSection

Section "Monitor"
    Identifier     "Monitor0"
EndSection

Section "Monitor"
    Identifier     "Monitor1"
EndSection

Section "Device"
    Identifier     "Card0"
    Driver         "intel"
    BusID          "PCI:0:2:0"
    Option         "AccelMethod"  "sna"
    Screen          0
    Option         "ZaphodHeads" "DP1"
EndSection

Section "Device"
    Identifier     "Card1"
    Driver         "intel"
    BusID          "PCI:0:2:0"
    Option         "AccelMethod"  "sna"
    Screen          1
    Option         "ZaphodHeads" "HDMI1"
EndSection

Section "Screen"
    Identifier     "Screen0"
    Device         "Card0"
    Monitor        "Monitor0"
    SubSection     "Display"
        Depth       24
    EndSubSection
EndSection

Section "Screen"
    Identifier     "Screen1"
    Device         "Card1"
    Monitor        "Monitor1"
    SubSection     "Display"
        Depth       24
    EndSubSection
EndSection

```
