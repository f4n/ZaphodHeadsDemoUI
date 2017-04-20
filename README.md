# ZaphodHeads and Java font problem

This is a little demo application which shows a problem with fonts on a zaphodhead configuration.
Zaphodhead means a multi-monitor setup whith each monitor on a separate X id.
So you need a least two monitors to reproduce the problem.
 

## Background
We have a Java UI with two separate windows. From the main window a second one is opened on a different monitor.
X is configured to have a separate display id for each monitor (zaphodhead). This configuration worked quiet well for a couple
of years on SLES11 machines with different NVidia cards and Java versions.
With the same NVidia cards and the same Java version (but a newer X server version) it doesn't work an a CentOS or Fedora linux.
The fonts on the second window seem to be transparent.

IMG


## What does the demo UI do
It first opens a window on the current screen. With a button "open" you can open a second window on a different screen (different than the current one)

## Reproducible with:
* Java: 1.8.0_74, 1.8.0_121, 1.9ea
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
  
## Working with:
* Java: 1.8.0_74, 1.8.0_121
* GFX:
  * NVidia FX580, 331.20
  * NVidia NVS420, 331.20
* X: 1.6.5
* OS: 
  * SLES11 sp2
  
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