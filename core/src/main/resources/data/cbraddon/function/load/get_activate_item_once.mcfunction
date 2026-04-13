# 先清除已有的物品
# Clear duplicate item
clear @s minecraft:wooden_sword[custom_data={activateItem:1b}]

# --------Start--------

# [创造模式] [右键] - CBR addon
# 在 创造模式 下 右键 以启用 CBR addon
# [Creative Mode] [Right Button] - CBR addon
# Click Right Button in Creative Mode to enable CBR addon
give @s minecraft:wooden_sword[custom_data={activateItem:1b},custom_name='[{"text":"[","color":"gray","italic":false},{"translate":"gameMode.creative","color":"light_purple","bold":true},{"text":"] ","color":"gray","bold":false},{"text":"[","color":"gray"},{"translate":"key.mouse.right","color":"gold","bold":true},{"text":"] ","color":"gray","bold":false},{"text":"- ","color":"white"},{"text":"CBR addon","color":"aqua"}]',lore=['[{"text":"在 ","color":"gray","italic":false},{"translate":"gameMode.creative"},{"text":" 下 "},{"translate":"key.mouse.right"},{"text":" 以启用 CBR addon"}]','[{"text":"Click ","color":"gray","italic":false},{"translate":"key.mouse.right"},{"text":" in "},{"translate":"gameMode.creative"},{"text":" to enable CBR addon"}]']]

# 取消自身监听
# Unregister this
battleroyale api functionManager unregisterEvent cbraddon:load/get_activate_item_once false eventType PLAYER_LOGGED_IN_EVENT

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1