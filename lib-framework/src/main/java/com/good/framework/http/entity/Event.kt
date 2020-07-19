package com.good.framework.http.entity

class Event{
    companion object{
        /**
         * 欢迎页活动
         */
        const val TYPE_APP_SPLASH = 1

        /**
         * 首页banner活动
         */
        const val TYPE_HOME_BANNER = 2

        /**
         * 活动进行中标识
         */
        const val STATE_ON = 1

        /**
         * 活动停止标识
         */
        const val STATE_STOP = 2

        /**
         * 活动等待开始标识
         */
        const val STATE_WAIT = 3

    }


    /**
     *
     */
    private val serialVersionUID = -4612263142313060376L

    var id = 0

    var title: String? = null

    var content: String? = null

    var startTime: Long? = null

    var endTime: Long? = null

    var image: String? = null

    var type: Int? = null

    var state: Int? = null

    var icon: String? = null

    var remark: String? = null

    var link: String? = null

}