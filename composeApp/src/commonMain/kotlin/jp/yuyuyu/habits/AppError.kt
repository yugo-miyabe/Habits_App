package jp.yuyuyu.habits

sealed class AppError {
    data object DataBaseError : AppError()
    data object NetworkError : AppError()
}
