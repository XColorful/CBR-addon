[English](#English)

# 进度条指令
_/cbraddon progressBar [enable/disable/...] [regex/color/overlay]_

## 开关进度条
_/cbraddon progressBar [enable/disable]_

### 启用进度条
_/cbraddon progressBar enable_

- 重新应用[临时数据](https://github.com/XColorful/BattleRoyale/wiki/Temp-data)（_cbraddon.json_）
- 启用进度条（已经创建的`游戏区域`不会被追踪）
- 写入[临时数据](https://github.com/XColorful/BattleRoyale/wiki/Temp-data)（_cbraddon.json_）

### 禁用进度条
_/cbraddon progressBar disable_

- 禁用进度条
- 清空当前进度条
- 写入[临时数据](https://github.com/XColorful/BattleRoyale/wiki/Temp-data)（_cbraddon.json_）

## 设置进度条
_/cbraddon progressBar [...] [regex/color/overlay]_

### 设置区域ID过滤
_/cbraddon progressBar zoneId_regex [regex]_

- regex：正则表达式，匹配[区域配置](https://github.com/XColorful/BattleRoyale/wiki/Zone-config#单个配置)`zoneId`
- 写入[临时数据](https://github.com/XColorful/BattleRoyale/wiki/Temp-data)（_cbraddon.json_）

### 设置进度条外观
_/cbraddon progressBar [moveDelay_color/moveTime_color] [color]_

_/cbraddon progressBar [moveDelay_overlay/moveTime_overlay] [overlay]_

设置[区域功能词条](https://github.com/XColorful/BattleRoyale/wiki/Zone-config#区域功能词条)`moveDelay`和`moveTime`的进度条外观：
- color：进度条颜色
- overlay：进度条样式
- 写入[临时数据](https://github.com/XColorful/BattleRoyale/wiki/Temp-data)（_cbraddon.json_）

# English
_/cbraddon progressBar [enable/disable/...] [regex/color/overlay]_

## Toggle progress bar
_/cbraddon progressBar [enable/disable]_

### Enable progress bar
_/cbraddon progressBar enable_

- Reapply [Temp data](https://github.com/XColorful/BattleRoyale/wiki/Temp-data#English) (_cbraddon.json_)
- Enable progress bar (`GameZone` already created will not be tracked)
- Write to [Temp data](https://github.com/XColorful/BattleRoyale/wiki/Temp-data#English) (_cbraddon.json_)

### Disable progress bar
_/cbraddon progressBar disable_

- Disable progress bar
- Clear current progress bars
- Write to [Temp data](https://github.com/XColorful/BattleRoyale/wiki/Temp-data#English) (_cbraddon.json_)

## Set progress bar
_/cbraddon progressBar [...] [regex/color/overlay]_

### Set zone ID filter
_/cbraddon progressBar zoneId_regex [regex]_

- regex: Regular expression matching [Zone config](https://github.com/XColorful/BattleRoyale/wiki/Zone-config#Single-zone-config) `zoneId`
- Write to [Temp data](https://github.com/XColorful/BattleRoyale/wiki/Temp-data#English) (_cbraddon.json_)

### Set progress bar overlook
_/cbraddon progressBar [moveDelay_color/moveTime_color] [color]_

_/cbraddon progressBar [moveDelay_overlay/moveTime_overlay] [overlay]_

Set progress bar appearance for `moveDelay`和`moveTime` in [Zone function entry](https://github.com/XColorful/BattleRoyale/wiki/Zone-config#Zone-function-entry):
- color: Progress bar color
- overlay: Progress bar style
- Write to [Temp data](https://github.com/XColorful/BattleRoyale/wiki/Temp-data#English) (_cbraddon.json_)