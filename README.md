# VibeNPlayLib
controls vibration and Plays ringtone or alarm or notification tone

## Initialization
`VibeNPlay.with(context).sound(true).vibration(true).initialize();`

## Starting

`VibeNPlay.start();`

## Stopping

`VibeNPlay.stop();`

## Customizing

### Vibration

```java
VibeNPlay.setVibration(true); //true to on, false to off
```
```java
//firstbeat, rest, secondbeat - in mili seconds
//count - no of time to play this pattern
VibeNPlay.setVibrationPattern(firstbeat, rest, secondbeat, count);
```
### Sound

```java
VibeNPlay.setSound(true); //true to on, false to off
```
```java
//VibeNPlay.SOUND_RINGTONE,VibeNPlay.SOUND_ALARM, VibeNPlay.SOUND_NOTIFICATION,VibeNPlay.SOUND_ALL
VibeNPlay.setSoundType(VibeNPlay.SOUND_RINGTONE);
```
```java
VibeNPlay.setSoundPattern(true); //true for play in loop, false for play one time
```
 ```java
 VibeNPlay.reset(); // to reset all settings to their default value
 ```