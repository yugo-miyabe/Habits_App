package jp.yuyuyu.habits

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform