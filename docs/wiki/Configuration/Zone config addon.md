[English](#English)

## 区域配置扩展

扩展自[区域配置](https://github.com/XColorful/BattleRoyale/wiki/Zone-config)

### 特殊功能词条

#### 实体刷新区

> 与[网格分布传送](https://github.com/XColorful/BattleRoyale/wiki/Spawn-config#网格分布传送)使用相同算法
- distrubutionType："rectangleGrid"，"goldenSpiral"，"circleGrid"
- lootFactorContribution：实体刷新比例贡献
- fixedSimulation：固定模拟数量
> 最终模拟的数量为：_固定模拟数量_ + _实体刷新数量_ * _实体刷新比例贡献_，若模拟数量不足则循环选取
- allowOnBorder：是否允许点位在边界上
- globalShrinkRatio：全局缩放比例
- needShuffle：打乱可选的点位
- randomRange：往xyz方向分别随机偏移 _[-x,x)_，_[-y,y)_，_[-z,z)_
- findGround：是否选取该点的地面
- limitToBottom：是否限制点位最小高度为区域底部中心高度
- limitToTop：是否限制点位最大高度为区域顶部中心高度
- additionalOffset：确定传送点后，额外进行偏移
> 这个功能设计为让TNT在离地固定高度生成
- ignoreOutside：是否将不在区域范围内的点位舍弃
- relativeMovementRandomRange：让实体的移动向量随机偏移 _[-x,x)_，_[-y,y)_，_[-z,z)_
```json
{
	"zoneFuncType": "entity",
	"moveDelay": 0,
	"moveTime": 20,
	"tickFrequency": 20,
	"tickOffset": -1,
	"protocol": "cbra:0.4.4",
	"tag": {
		"distributionType": "circleGrid",
		"lootFactorContribution": 1.0,
		"fixedSimulation": 0,
		"allowOnBorder": false,
		"globalShrinkRatio": 1.0,
		"needShuffle": false,
		"randomRange": "0,0,0",
		"findGround": false,
		"limitToBottom": true,
		"limitToTop": false,
		"additionalOffset": "0,0,0",
		"ignoreOutside": false,
		"relativeMovementRandomRange": "0,0,0"
	},
	"lootId": 0,
	"nbt": "{}"
}
```

##### TNT轰炸区

_实体生成器配置_
```json
{
	"lootId": 0,
	"name": "TNT spawn",
	"color": "FF0000AA",
	"entry": {
		"lootType": "entity",
		"entity": "minecraft:tnt",
		"count": 16,
		"range": 1,
		"attempts": 4
	}
}
```
_区域功能词条_
> 自1.21.6起格式不断变化且功能受限，如int列表需要写成 _[I;0,-1,0]_ 并且模组作者目前已知不支持部分类型数据写入，因此不保证成功写入实体NBT
```json
{
	"zoneFuncType": "entity",
	"protocol": "cbra:0.4.4",
	"tag": {
		"distributionType": "circleGrid",
		"lootFactorContribution": 1.0,
		"fixedSimulation": 0,
		"allowOnBorder": true,
		"globalShrinkRatio": 1.0,
		"needShuffle": true,
		"randomRange": "5,0,5",
		"findGround": true,
		"limitToBottom": true,
		"limitToTop": false,
		"additionalOffset": "0,30,0",
		"ignoreOutside": true,
		"relativeMovementRandomRange": "15,0,15"
	},
	"lootId": 0,
	"nbt": "{Motion:[0d,-1d,0d]}"
}
```

# English

## Zone config addon

Extended from [Zone config](https://github.com/XColorful/BattleRoyale/wiki/Zone-config#English)

### Zone special function

#### Entity loot zone

> Uses the same algorithm as [Grid distributed teleport](https://github.com/XColorful/BattleRoyale/wiki/Spawn-config#Grid-distributed-teleport)
- distrubutionType: "rectangleGrid", "goldenSpiral", "circleGrid"
- lootFactorContribution: entity loot ratio contribution
- fixedSimulation: fixed number of simulation points
> The final simulated quantity is: _fixedSimulationCount_ + _entity loot count_ * _entity loot factor contribution_. If the simulated quantity is insufficient, selection will loop.
- allowOnBorder: Whether to allow points to be exactly on the boundary.
- globalShrinkRatio: global scaling ratio applied to the distribution area
- needShuffle: Shuffles the list of available spawn points before selection.
- randomRange: Randomly offsets points in the x, y, and z directions by _[-x,x)_, _[-y,y)_, _[-z,z)_
- findGround: Whether to select the ground at the point.
- limitToBottom: Whether to limit the minimum height of the point to the bottom center of the region.
- limitToTop: Limits the maximum height of the point to the center of the top of the area.
- additionalOffset: After determining the teleport point, applies an additional offset.
> This feature is designed to ensure TNT spawns at a fixed height above the ground.
- ignoreOutside: Whether to discards points outside the zone.
- relativeMovementRandomRange: Randomly offsets the entity's movement vector by _[-x,x)_, _[-y,y)_, _[-z,z)_
```json
{
	"zoneFuncType": "entity",
	"moveDelay": 0,
	"moveTime": 20,
	"tickFrequency": 20,
	"tickOffset": -1,
	"protocol": "cbra:0.4.4",
	"tag": {
		"distributionType": "circleGrid",
		"lootFactorContribution": 1.0,
		"fixedSimulation": 0,
		"allowOnBorder": false,
		"globalShrinkRatio": 1.0,
		"needShuffle": false,
		"randomRange": "0,0,0",
		"findGround": false,
		"limitToBottom": true,
		"limitToTop": false,
		"additionalOffset": "0,0,0",
		"ignoreOutside": false,
		"relativeMovementRandomRange": "0,0,0"
	},
	"lootId": 0,
	"nbt": "{}"
}
```

##### TNT bombing area

_entity spawner config_
```json
{
	"lootId": 0,
	"name": "TNT spawn",
	"color": "FF0000AA",
	"entry": {
		"lootType": "entity",
		"entity": "minecraft:tnt",
		"count": 16,
		"range": 1,
		"attempts": 4
	}
}
```
_zone function entry_
> Since 1.21.6, the format has been changing and the functions are limited. For example, the int list needs to be written as _[I;0,-1,0]_. And the mod author currently knows that some types of data are not supported. Therefore, the successful writing of entity NBT is not guaranteed.
```json
{
	"zoneFuncType": "entity",
	"protocol": "cbra:0.4.4",
	"tag": {
		"distributionType": "circleGrid",
		"lootFactorContribution": 1.0,
		"fixedSimulation": 0,
		"allowOnBorder": true,
		"globalShrinkRatio": 1.0,
		"needShuffle": true,
		"randomRange": "5,0,5",
		"findGround": true,
		"limitToBottom": true,
		"limitToTop": false,
		"additionalOffset": "0,30,0",
		"ignoreOutside": true,
		"relativeMovementRandomRange": "15,0,15"
	},
	"lootId": 0,
	"nbt": "{Motion:[0d,-1d,0d]}"
}
```