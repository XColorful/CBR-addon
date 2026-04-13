execute if score disableLoad cbraddon matches 1.. run return 0
# --------Option--------

# --------Start--------

battleroyale api functionManager registerEvent cbraddon:load/get_item_once false eventType RIGHT_CLICK_ITEM_EVENT HIGHEST true
battleroyale api functionManager registerEvent cbraddon:load/get_activate_item_once false eventType PLAYER_LOGGED_IN_EVENT HIGHEST true

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1