package com.ndanda.opentable.data

import android.arch.persistence.room.Entity

@Entity
class Link {
    var type: String? = null
    var url: String? = null
    var suggested_link_text: String? = null
}
