# --------Start--------

# 删除Option记分板
# Remove option scoreboard
execute store result storage battleroyale:temp success byte 1 run scoreboard objectives remove cbraddon

# 检查是否新删除记分板
# Check if scoreboard is newly removed
execute if data storage battleroyale:temp {success: 0b} run return 0

# 提示已删除记分板
# Notify scoreboard removal
tellraw @p {"text": "CBR addon Options removed", "color": "green"}

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp success

# 正常执行
# Command.SINGLE_SUCCESS
return 1