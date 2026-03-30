# 检查是否手持木剑
# Check wooden sword
execute unless data entity @s SelectedItem{id:"minecraft:wooden_sword"} run return 0

# --------Start--------

function cbraddon:server/give_login_item

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1