execute unless score gameEffects cbraddon matches 1.. run return 0
# --------Option--------

# --------Start--------

# 发送结束游戏标题
# Send "Game Stop" title
title @s times 10 80 20
title @s title {"text":"Game Stop", "color":"aqua"}

function cbraddon:sounds/sad_sound

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1