# --------Start--------

# 添加Option记分板
# Add option scoreboard
execute store result storage battleroyale:temp success byte 1 run scoreboard objectives add cbraddon dummy "CBR addon Options"

# 检查是否新创建记分板
# Check if scoreboard is newly created
execute if data storage battleroyale:temp {success: 0b} run return 0

# 默认启用全部功能
# Enable all features by default
function cbraddon:option/enable_all

# 设置侧边栏显示
# Set sidebar display
scoreboard objectives setdisplay sidebar cbraddon

# 提示已创建记分板
# Notify scoreboard creation
tellraw @p {"text": "CBR addon Options created", "color": "green"}

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp success

# 正常执行
# Command.SINGLE_SUCCESS
return 1