execute unless score itemFunction cbraddon matches 1.. run return 0
# --------Option--------

# 检查物品标签
# Check item tag
execute unless data entity @s SelectedItem.tag.itemFunction run return 0

# --------Start--------

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp returnValue

return 0