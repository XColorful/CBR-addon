execute unless score itemFunction cbraddon matches 1.. run return 0
# --------Option--------

# 检查物品标签
# Check item tag
execute unless data entity @s SelectedItem.tag.itemFunction run return 0

# --------Start--------

execute store result storage battleroyale:temp returnValue int 1 run function cbraddon:on_right_click_item/use_game_item
execute if data storage battleroyale:temp {returnValue: -1} run return -1

# 异常提示
# Notify exception
function cbraddon:on_right_click_item/use_item_function_fail_effect

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp returnValue

return 0