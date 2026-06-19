package xiao.cbraddon.event.custom;

import xiao.battleroyale.api.event.CustomEventType;
import xiao.battleroyale.api.event.EventPriority;
import xiao.battleroyale.api.event.ICustomEventHandler;
import xiao.battleroyale.api.event.ICustomEventRegister;
import xiao.cbraddon.CbrAddon;
import xiao.cbraddon.config.common.game.zone.tickable.event.EntityFuncHandler;

public class CustomEventHandler {

    public static void registerAll(ICustomEventRegister customEventRegister) {
        register(customEventRegister, EntityFuncHandler.get(), CustomEventType.ENTITY_EVENT, EventPriority.NORMAL, false);
    }

    private static void register(ICustomEventRegister customEventRegister, ICustomEventHandler eventHandler, CustomEventType customEventType, EventPriority priority, boolean receiveCanceled) {
        if (customEventRegister.register(eventHandler, customEventType, priority, receiveCanceled)) {
            CbrAddon.LOGGER.debug("{} registered to {}", eventHandler.getEventHandlerName(), customEventType);
        } else {
            CbrAddon.LOGGER.debug("Failed to register {} to {}", eventHandler.getEventHandlerName(), customEventType);
        }
    }
}
