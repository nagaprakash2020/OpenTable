package com.ndanda.opentable.data

import android.arch.persistence.room.Entity

@Entity
class Multimedia {
    var type: String? = null
    var src: String? = null
    var width: Int = 0
    var height: Int = 0
}
