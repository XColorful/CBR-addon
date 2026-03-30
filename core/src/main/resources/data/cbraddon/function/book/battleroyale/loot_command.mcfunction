# 先清除已有的书
# Clear duplicate book
clear @s written_book[custom_data={title:"CBR Loot command"}]

# --------Start--------

# <Page 1>
# /cbr loot generate
# /cbr loot chunk <xyz>
# /cbr loot pos <xyz>
# /cbr loot stop

# <Page 2>
# /cbr loot <player> <id> reset
# /cbr loot <player> <id> generate
# ---- Github Wiki ----
# https://github.com/XColorful/BattleRoyale/wiki/Loot-command

give @s written_book[custom_data={title:"CBR Loot command"},custom_name={"translate":"battleroyale.command.cbr_loot","italic":false,"color":"light_purple"},written_book_content={title:"CBR Loot command",author:"CBR addon",pages:[["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_loot","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.command.cbr_loot_generate","color":"aqua"},{"text":"\n"},{"text":"/cbr loot generate","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr loot generate"}},{"text":"\n"},{"translate":"battleroyale.command.cbr_loot_chunk","color":"aqua"},{"text":"\n"},{"text":"/cbr loot chunk <xyz>","italic":true,"color":"gray"},{"text":"\n"},{"translate":"battleroyale.command.cbr_loot_pos","color":"aqua"},{"text":"\n"},{"text":"/cbr loot pos <xyz>","italic":true,"color":"gray"},{"text":"\n"},{"translate":"battleroyale.command.cbr_loot_stop","color":"aqua"},{"text":"\n"},{"text":"/cbr loot stop","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr loot stop"}}],["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_loot","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.command.cbr_loot_player_id_reset","color":"aqua"},{"text":"\n"},{"text":"/cbr loot <player> <id> reset","italic":true,"color":"gray"},{"text":"\n"},{"translate":"battleroyale.command.cbr_loot_player_id_generate","color":"aqua"},{"text":"\n"},{"text":"/cbr loot <player> <id> generate","italic":true,"color":"gray"},{"text":"\n"},{"text":"---- "},{"text":"Github Wiki","color":"blue","underlined":true,"click_event":{"action":"open_url","url":"https://github.com/XColorful/BattleRoyale/wiki/Loot-command"}},{"text":" ----"}]]}]

function cbraddon:sounds/book_sound

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1