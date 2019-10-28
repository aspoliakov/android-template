package ru.stowawaydev.template.data.eventbus

import io.reactivex.Observable

/**
 * template header (replace it)
 */

class TemplateEventBus : RxEventBus<TemplateEventBus.Event>() {

    fun publishTemplateUpdated(templateId: String) {
        publishEvent(Event(EventType.TEMPLATE_UPDATED, templateId))
    }

    fun listenEventsByType(eventType: EventType): Observable<Event> {
        return listenEvents().filter { it.eventType == eventType }
    }

    enum class EventType {
        TEMPLATE_ADDED,
        TEMPLATE_UPDATED,
        TEMPLATE_DELETED,
        TEMPLATE_LIST_ADDED,
        TEMPLATE_LIST_UPDATED,
        TEMPLATE_LIST_DELETED
    }

    data class Event(val eventType: EventType, val templateId: String)
}
