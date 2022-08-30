package com.example.imagesearchapp.model

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("links")
    @Embedded
    val userLinks: UserLinks,
    val username: String
)

// Embedded 키워드를 통해 룸에게 UserLinks가 중첩된 필드(데이터 클래스)라는 것을 알림
