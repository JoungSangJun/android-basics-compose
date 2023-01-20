package com.example.eventtracking

/*
 * 2023년 1월 20일
 * Android-Developers
 * 단원 3: 컬렉션을 사용한 고차함수 및 확장, enum, data class 연습
 */


data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)

enum class Daypart {
    MORNING, AFTERNOON, EVENING
}

// Event data class 에 속성 확장
val Event.durationOfEvent: String
    get() {
        if (durationInMinutes < 60) {
            return "short"
        } else {
            return "long"
        }
    }

fun main() {

    val event1 = Event(
        title = "Wake up",
        description = "Time to get up",
        daypart = Daypart.MORNING,
        durationInMinutes = 0
    )
    val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
    val event3 =
        Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
    val event4 =
        Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(
        title = "Watch latest DevBytes video",
        daypart = Daypart.AFTERNOON,
        durationInMinutes = 10
    )
    val event6 = Event(
        title = "Check out latest Android Jetpack library",
        daypart = Daypart.EVENING,
        durationInMinutes = 45
    )

    // list에 Event형 변수 저장
    val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)

    // durationInMinutes 이 60보다 작은 값만 저장
    val shortTimeEvents: List<Event> = events.filter {
        it.durationInMinutes < 60
    }

    // daypart 를 Key 값으로 하는 Map 저장
    val groupedEvents = events.groupBy {
        it.daypart
    }
}