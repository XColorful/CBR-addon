# 检查是否手持木剑
# Check wooden sword
execute unless items entity @s weapon.mainhand minecraft:wooden_sword run return 0

# --------Start--------

function cbraddon:server/give_login_item

# 检查是否为创造模式
# Check if player is in creative mode
execute if entity @s[gamemode=!creative] run return 1

function cbraddon:server/give_op_item

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1