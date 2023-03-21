package com.twaun95.data.model.video

import com.twaun95.domain.model.VideoEntity

data class KakaoVideo(
    val title: String,
    val url: String,
    val datetime: String,
    val play_time: Int,
    val thumbnail: String,
    val author: String
) {
    companion object {
        fun toVideoEntity(kakaoVideo: KakaoVideo) : VideoEntity{
            return VideoEntity(
                title = kakaoVideo.title,
                url = kakaoVideo.url,
                datetime = kakaoVideo.datetime,
                play_time = kakaoVideo.play_time,
                thumbnail = kakaoVideo.thumbnail,
                author = kakaoVideo.author
            )
        }
    }
}
