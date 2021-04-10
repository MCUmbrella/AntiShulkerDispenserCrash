# AntiShulkerDispenserCrash
### Block possible shulker box dispenser crashes<br><br>
**Introduction:**<br>
There is a bug that allows the dispenser at y=255 with up facing or y=0 with down facing to crash the server when dispensing a shulker box with item(s). [【See this video】](https://www.youtube.com/watch?v=XL17P87O6xA)<br>
This plugin was created to avoid this bug from crashing the server. It will print a warning message(optional) when a dispenser like above is activated and cancel the event. Other than that it doesn’t do anything.<br><br>
**Configuration:**<br>
In `config.properties` you will find these 2 keys:<br>
```properties
accurateDetection=true
warnConsole=true
```
- The `accurateDetection` key is for defining detection accuracy. Default value is `true`. When it is `true` the plugin will only block the event if the suspicious dispenser contains shulker box. Otherwise, all dispensers placed in suspicious locations will not work. More accurate detection will theoretically be slower, but the performance loss caused by 225 suspicious dispensers in the test environment for about every 4 tick to trigger an event at the same time is still negligible. Anyways, choosing accurate detection can minimize the impact on the original game experience.
- The `warnConsole` key controls whether to print warning messages to the console. Default value is `true`. Set its value to `false` if you dont want warning messages to spam your console output.