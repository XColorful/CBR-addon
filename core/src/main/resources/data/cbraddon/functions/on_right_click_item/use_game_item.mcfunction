# 检查物品标签
# Check item tag
execute unless data entity @s SelectedItem.tag.gameItem run return 0

# --------Start--------

# 传送至大厅
# Teleport to lobby
execute if data entity @s SelectedItem{id:"minecraft:compass"} run cbr game toLobby
execute if data entity @s SelectedItem{id:"minecraft:compass"} run return -1

# --------return--------

# 取消事件
# Cancel the event
return -1