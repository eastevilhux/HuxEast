package com.good.framework.entity

import java.io.Serializable
import kotlin.properties.Delegates

open class VMData : Serializable{
    var code by Delegates.notNull<Code>();

    companion object{
        private const val serialVersionUID= 5990939387657237756L;
    }

    enum class Code(code : Int){
        CODE_SUCCESS(88),
        CODE_DEFAULT(0),
        CODE_SHOW_MSG(1),
        CODE_SHOW_MSGVIEW(2);
    }

}